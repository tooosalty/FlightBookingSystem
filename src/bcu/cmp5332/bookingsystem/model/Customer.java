package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings;

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bookings = new ArrayList<>();
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Phone
    public String getPhone() {
        return phone;
    }

    // Setter for Phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for Bookings
    public List<Booking> getBookings() {
        return bookings;
    }

    // Method to add a booking
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        for (Booking b : bookings) {
            if (b.getFlight().equals(booking.getFlight())) {
                throw new FlightBookingSystemException("Customer already has a booking for this flight.");
            }
        }
        bookings.add(booking);
    }

    // Method to cancel a booking for a specific flight
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
        boolean removed = bookings.removeIf(b -> b.getFlight().equals(flight));
        if (!removed) {
            throw new FlightBookingSystemException("No booking found for the given flight.");
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", bookings=" + bookings.size() +
                '}';
    }
}
