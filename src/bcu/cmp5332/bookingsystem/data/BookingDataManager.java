package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_FILE));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SEPARATOR);
            int customerId = Integer.parseInt(parts[0]);
            int flightId = Integer.parseInt(parts[1]);
            LocalDate bookingDate = LocalDate.parse(parts[2]);
            Customer customer = fbs.getCustomerById(customerId);
            Flight flight = fbs.getFlightById(flightId);
            Booking booking = new Booking(customer, flight, bookingDate);
            customer.addBooking(booking);
            flight.addPassenger(customer);
        }
        br.close();
    }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKINGS_FILE));
        for (Customer customer : fbs.getCustomer()) {
            for (Booking booking : customer.getBookings()) {
                Flight flight = booking.getFlight();
                bw.write(customer.getId() + SEPARATOR + flight.getId() + SEPARATOR + booking.getBookingDate());
                bw.newLine();
            }
        }
        bw.close();
    }
