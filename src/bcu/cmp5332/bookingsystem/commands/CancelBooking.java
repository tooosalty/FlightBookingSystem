package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;

    public CancelBooking(int customerId, int flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerById(customerId);
        Flight flight = flightBookingSystem.getFlightById(flightId);
        double flightPrice = flight.getPrice(); // Assuming Flight class has a getPrice method.

        customer.cancelBookingForFlight(flight);
        flight.removePassenger(customer);

        double cancellationFee = flightBookingSystem.calculateCancellationFee(flightPrice);
        System.out.printf("Booking cancelled. A cancellation fee of £%.2f was charged.%n", cancellationFee);
    }
}