package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {

	private static final double BASE_CANCELLATION_FEE = 20.0;

    private final Customer customer;
    private final Flight flight;
    private final LocalDate bookingDate;

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }

	public double calculateCancellationFee(LocalDate cancellationDate) {
		long daysToDeparture = cancellationDate.until(flight.getDepartureDate()).getDays();
		if (daysToDeparture > 14) {
			return BASE_CANCELLATION_FEE * 0.5; // 50% fee for cancellations >14 days before departure
		} else if (daysToDeparture > 7) {
			return BASE_CANCELLATION_FEE; // Full fee for cancellations 7-14 days before departure
		} else {
			return BASE_CANCELLATION_FEE * 1.5; // 150% fee for cancellations within 7 days
		}
	}

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    // Method to calculate the cancellation fee
    public double calculateCancellationFee() {
        return CANCELLATION_FEE;
    }

    // Booking details
    public String getDetails() {
        return "Booking date: " + bookingDate + " for Flight #" + flight.getId() + " - " +
               flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination();
    }
}