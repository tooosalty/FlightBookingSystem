
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;

public class AddBooking implements Command {

    private final int customerId;
    private final int flightId;

    public AddBooking(int customerId, int flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerById(customerId);
        Flight flight = flightBookingSystem.getFlightById(flightId);

        if (flight.getPassengers().size() >= flight.getCapacity()) {
            throw new FlightBookingSystemException("Flight is already fully booked.");
        }

        LocalDate bookingDate = flightBookingSystem.getSystemDate();
        double flightPrice = flight.getPrice(); // Assuming Flight class has a getPrice method.
        double rebookingFee = flightBookingSystem.calculateRebookingFee(flightPrice);

        Booking booking = new Booking(customer, flight, bookingDate);
        customer.addBooking(booking);
        flight.addPassenger(customer);

        System.out.printf("Booking successfully created. A rebooking fee of £%.2f was applied.%n", rebookingFee);
    }
}
