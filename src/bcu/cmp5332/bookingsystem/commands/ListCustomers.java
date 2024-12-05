package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command {

    @Override
    public void execute(FlightBookingSystem fbs) {
        long activeCustomers = fbs.getCustomers().stream()
            .filter(customer -> !customer.isDeleted()) // Filter out deleted customers
            .peek(customer -> System.out.println(customer.getDetailsShort())) // Print details of active customers
            .count(); // Count active customers

        System.out.println("Total active customers: " + activeCustomers);
    }
}