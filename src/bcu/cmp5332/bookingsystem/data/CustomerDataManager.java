package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;
import java.util.Scanner;

public class CustomerDataManager implements DataManager {

    private static final String RESOURCE = "./resources/data/customers.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int lineIdx = 1;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);

                try {
                    if (properties.length < 3) {
                        throw new FlightBookingSystemException("Malformed data on line " + lineIdx);
                    }

                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    String phone = properties[2];

                    Customer customer = new Customer(id, name, phone);
                    fbs.addCustomer(customer);

                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Invalid number format on line " + lineIdx + ": " + ex.getMessage());
                }

                lineIdx++;
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