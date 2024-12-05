package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;

    private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        
        passengers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        StringBuilder details = new StringBuilder();
        details.append("Flight #").append(id).append("\n")
                .append("Flight No: ").append(flightNumber).append("\n")
                .append("Origin: ").append(origin).append("\n")
                .append("Destination: ").append(destination).append("\n")
                .append("Departure Date: ").append(departureDate.format(dtf)).append("\n")
                .append("---------------------------\n")
                .append("Passengers:\n");

        for (Customer passenger : passengers) {
            details.append("* Id: ").append(passenger.getId()).append(" - ")
                    .append(passenger.getName()).append(" - ")
                    .append(passenger.getPhone()).append("\n");
        }

        details.append(passengers.size()).append(" passenger(s)");
        return details.toString();
    }

    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
        if (passengers.contains(passenger)) {
            throw new FlightBookingSystemException("Passenger is already on this flight.");
        }
        passengers.add(passenger);
    }

    public void removePassenger(Customer passenger) throws FlightBookingSystemException {
        if (!passengers.contains(passenger)) {
            throw new FlightBookingSystemException("Passenger is not on this flight.");
        }
        passengers.remove(passenger);
    }
                return null;
    }
    
    public void addPassenger(Customer passenger) {
        
    }

