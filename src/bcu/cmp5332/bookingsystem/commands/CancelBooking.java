package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
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
public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
    Customer customer = fbs.getCustomerById(customerId);
    Flight flight = fbs.getFlightById(flightId);

    Booking booking = customer.getBookings().stream()
        .filter(b -> b.getFlight().equals(flight))
        .findFirst()
        .orElseThrow(() -> new FlightBookingSystemException("No booking found for the specified flight."));

    LocalDate cancellationDate = fbs.getSystemDate();
    double cancellationFee = booking.calculateCancellationFee(cancellationDate);

    customer.cancelBookingForFlight(flight);
    flight.removePassenger(customer);

    System.out.println("Booking canceled for customer ID " + customerId + " on flight ID " + flightId);
    System.out.println("Cancellation fee: $" + cancellationFee);
}
}