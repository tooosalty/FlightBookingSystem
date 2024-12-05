package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Flight {

    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private final Set<Customer> passengers;
    private int capacity;

    private boolean isDeleted = false;

public boolean isDeleted() {
    return isDeleted;
}

public void setDeleted(boolean deleted) {
    this.isDeleted = deleted;
}

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.passengers = new HashSet<>();
    }

    // Getters and Setters
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

    public Set<Customer> getPassengers() {
        return passengers;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getDetailsShort() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " + destination + " - Departure: " + departureDate.format(formatter);
    }

    public String getDetailsLong() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("Flight #").append(id).append("\n")
          .append("Flight No: ").append(flightNumber).append("\n")
          .append("Origin: ").append(origin).append("\n")
          .append("Destination: ").append(destination).append("\n")
          .append("Departure Date: ").append(departureDate.format(formatter)).append("\n")
          .append("---------------------------\n")
          .append("Passengers:\n");
        for (Customer passenger : passengers) {
            sb.append("* ").append(passenger.getDetailsShort()).append("\n");
        }
        sb.append(passengers.size()).append(" passenger(s)\n");
        return sb.toString();
    }

    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
        if (!passengers.add(passenger)) {
            throw new FlightBookingSystemException("Passenger already booked for this flight.");
        }
    }

    public void removePassenger(Customer passenger) throws FlightBookingSystemException {
        if (!passengers.remove(passenger)) {
            throw new FlightBookingSystemException("Passenger is not booked on this flight.");
        }
    }
    
    public double calculatePrice() {
        long daysToDeparture = LocalDate.now().until(departureDate).getDays();
        double basePrice = 100.0; // Example base price
        double capacityFactor = 1.0 - ((double) passengers.size() / capacity);
        double timeFactor = daysToDeparture < 7 ? 1.5 : 1.0;
    
        return basePrice * capacityFactor * timeFactor;
    }
}