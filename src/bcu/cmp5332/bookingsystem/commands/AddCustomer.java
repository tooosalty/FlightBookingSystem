package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;

    public AddCustomer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        int newId = fbs.getCustomers().stream().mapToInt(Customer::getId).max().orElse(0) + 1;
        Customer customer = new Customer(newId, name, phone);
        fbs.addCustomer(customer);
        System.out.println("Customer added: " + customer.getDetailsShort());
    }
}