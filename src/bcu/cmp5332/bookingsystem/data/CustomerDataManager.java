package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;
import java.util.Scanner;

public class CustomerDataManager implements DataManager {

    private static final String RESOURCE = "./resources/data/customers.txt";
    private static final String SEPARATOR = "::"; // Define SEPARATOR as a constant

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int lineIndex = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR); // Use SEPARATOR here
                try {
                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    String phone = properties[2];
                    Customer customer = new Customer(id, name, phone);
                    fbs.addCustomer(customer);
                } catch (NumberFormatException e) {
                    throw new FlightBookingSystemException("Invalid customer ID at line " + lineIndex, e);
                }
                lineIndex++;
            }
        }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCE))) {
            for (Customer customer : fbs.getCustomers()) {
                writer.write(customer.getId() + SEPARATOR + customer.getName() + SEPARATOR + customer.getPhone());
                writer.newLine();
            }
        }
    }
}