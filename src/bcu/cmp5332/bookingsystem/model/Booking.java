package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
	
	 // Getter for customer
	 public Customer getCustomer() {
        return customer;
    }

    // Setter for customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getter for flight
    public Flight getFlight() {
        return flight;
    }

    // Setter for flight
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    // Getter for bookingDate
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    // Setter for bookingDate
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer=" + customer.getName() +
                ", flight=" + flight.getDetailsShort() +
                ", bookingDate=" + bookingDate +
                '}';
    }
}