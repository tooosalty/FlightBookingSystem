package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {

    private final LocalDate systemDate = LocalDate.parse("2024-11-11");
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();

    // Getter for the system date
    public LocalDate getSystemDate() {
        return systemDate;
    }

    // Method to get an unmodifiable list of flights
    public List<Flight> getFlights() {
        return Collections.unmodifiableList(new ArrayList<>(flights.values()));
    }
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(new ArrayList<>(customers.values()));
    }

    // Method to get a flight by its ID
    public Flight getFlightById(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("No flight found with ID " + id);
        }
        return flights.get(id);
    }

    // Method to get a customer by their ID
    public Customer getCustomerById(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("No customer found with ID " + id);
        }
        return customers.get(id);
    }

    // Method to add a new flight
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException(
                    "A flight with the same number and departure date already exists in the system."
                );
            }
        }
        flights.put(flight.getId(), flight);
    }

    // Method to add a new customer
    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        customers.put(customer.getId(), customer);
    }
}