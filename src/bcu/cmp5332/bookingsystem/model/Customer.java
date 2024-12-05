package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings;

	private boolean isDeleted = false;
	public boolean isDeleted() {
    return isDeleted;
}

public void setDeleted(boolean deleted) {
    this.isDeleted = deleted;
}

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bookings = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    // Add Booking
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        if (bookings.stream().anyMatch(b -> b.getFlight().getId() == booking.getFlight().getId())) {
            throw new FlightBookingSystemException("Booking for this flight already exists.");
        }
        bookings.add(booking);
    }

    // Cancel Booking
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
        Booking booking = bookings.stream()
                                  .filter(b -> b.getFlight().getId() == flight.getId())
                                  .findFirst()
                                  .orElseThrow(() -> new FlightBookingSystemException("No booking found for flight ID " + flight.getId()));
        bookings.remove(booking);
    }

    // Detailed Customer Information
    public String getDetailsShort() {
        return "ID: " + id + " - " + name + " - " + phone;
    }

    public String getDetailsLong() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer #").append(id).append("\n")
          .append("Name: ").append(name).append("\n")
          .append("Phone: ").append(phone).append("\n")
          .append("--------------------------\n")
          .append("Bookings:\n");
        for (Booking booking : bookings) {
            sb.append("* ").append(booking.getDetails()).append("\n");
        }
        sb.append(bookings.size()).append(" booking(s)");
        return sb.toString();
    }
}