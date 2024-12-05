package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;

    public AddCustomer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {

        int newCustomerId = flightBookingSystem.getCustomer().size() + 1;
        Customer newCustomer = new Customer(newCustomerId, name, phone);
        flightBookingSystem.addCustomer(newCustomer);
        System.out.printf("Customer added successfully: ID = %d, Name = %s, Phone = %s%n", newCustomerId, name, phone);
            }
}
