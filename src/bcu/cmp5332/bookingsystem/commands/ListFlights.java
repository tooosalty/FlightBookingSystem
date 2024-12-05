package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListFlights implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        long activeFlights = flightBookingSystem.getFlights().stream()
            .filter(flight -> !flight.isDeleted()) 
            .peek(flight -> System.out.println(flight.getDetailsShort())) 
            .count();

        System.out.println(activeFlights + " active flight(s)");
    }
}
