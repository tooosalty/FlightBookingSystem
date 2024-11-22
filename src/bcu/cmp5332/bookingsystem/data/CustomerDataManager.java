package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	 try (Scanner sc = new Scanner(new File(RESOURCE))) {
             int line_idx = 1;
             while (sc.hasNextLine()) {
                 String line = sc.nextLine();
                 String[] properties = line.split(SEPARATOR, -1);
                 try {
                     int id = Integer.parseInt(properties[0]);
                     String Customer = properties[1];
                     String origin = properties[2];
                     String destination = properties[3];
                     LocalDate departureDate = LocalDate.parse(properties[4]);
                     Customer flight = new Flight(id, Customer, origin, destination, departureDate);
                     fbs.addCustomer(flight);
                 } catch (NumberFormatException ex) {
                     throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                         + "\nError: " + ex);
                 }
                 line_idx++;
             }
    	 }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    }
}
