package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;
import java.time.LocalDate;

public class BookingDataManager implements DataManager {

    private static final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);

                if (parts.length < 3) {
                    throw new FlightBookingSystemException("Invalid data format in bookings.txt");
                }

                int customerId = Integer.parseInt(parts[0]);
                int flightId = Integer.parseInt(parts[1]);
                LocalDate bookingDate = LocalDate.parse(parts[2]);

                Customer customer = fbs.getCustomerById(customerId);
                Flight flight = fbs.getFlightById(flightId);

                Booking booking = new Booking(customer, flight, bookingDate);
                customer.addBooking(booking);
                flight.addPassenger(customer);
            }
        }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCE))) {
            for (Customer customer : fbs.getCustomers()) {
                for (Booking booking : customer.getBookings()) {
                    writer.write(booking.getCustomer().getId() + SEPARATOR +
                                 booking.getFlight().getId() + SEPARATOR +
                                 booking.getBookingDate());
                    writer.newLine();
                }
            }
        }
    }
}