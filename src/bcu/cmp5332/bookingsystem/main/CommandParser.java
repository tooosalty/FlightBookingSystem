package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.*;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {

    public static Command parse(String line) throws FlightBookingSystemException {
        String[] parts = line.trim().split(" ", 2);
        String cmd = parts[0].toLowerCase();

        try {
            switch (cmd) {
                case "addflight":
                    return parseAddFlight();
                case "addcustomer":
                    return parseAddCustomer(parts);
                case "addbooking":
                    return parseAddBooking(parts);
                case "cancelbooking":
                    return parseCancelBooking(parts);
                case "listflights":
                    return new ListFlights();
                case "listcustomers":
                    return new ListCustomers();
                case "showflight":
                    return parseShowFlight(parts);
                case "showcustomer":
                    return parseShowCustomer(parts);
                case "loadgui":
                    return new LoadGUI();
                case "help":
                    return new Help();
                default:
                    throw new FlightBookingSystemException("Unknown command: " + cmd);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new FlightBookingSystemException("Invalid input format for command: " + cmd, ex);
        }
    }

    // Parses the 'addflight' command
    private static Command parseAddFlight() throws IOException, FlightBookingSystemException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Flight Number: ");
        String flightNumber = reader.readLine();
        System.out.print("Origin: ");
        String origin = reader.readLine();
        System.out.print("Destination: ");
        String destination = reader.readLine();
        LocalDate departureDate = parseDateWithAttempts(reader, 3);

        return new AddFlight(flightNumber, origin, destination, departureDate);
    }

    // Parses the 'addcustomer' command
    private static Command parseAddCustomer(String[] parts) throws FlightBookingSystemException {
        if (parts.length < 2) {
            throw new FlightBookingSystemException("Invalid input for addcustomer. Format: addcustomer [name] [phone]");
        }
        String[] customerArgs = parts[1].split(" ", 2);
        if (customerArgs.length < 2) {
            throw new FlightBookingSystemException("Invalid input for addcustomer. Format: addcustomer [name] [phone]");
        }
        return new AddCustomer(customerArgs[0], customerArgs[1]);
    }

    // Parses the 'addbooking' command
    private static Command parseAddBooking(String[] parts) throws FlightBookingSystemException {
        if (parts.length < 2) {
            throw new FlightBookingSystemException("Invalid input for addbooking. Format: addbooking [customerId] [flightId]");
        }
        String[] bookingArgs = parts[1].split(" ");
        if (bookingArgs.length < 2) {
            throw new FlightBookingSystemException("Invalid input for addbooking. Format: addbooking [customerId] [flightId]");
        }
        return new AddBooking(Integer.parseInt(bookingArgs[0]), Integer.parseInt(bookingArgs[1]));
    }

    // Parses the 'cancelbooking' command
    private static Command parseCancelBooking(String[] parts) throws FlightBookingSystemException {
        if (parts.length < 2) {
            throw new FlightBookingSystemException("Invalid input for cancelbooking. Format: cancelbooking [customerId] [flightId]");
        }
        String[] bookingArgs = parts[1].split(" ");
        if (bookingArgs.length < 2) {
            throw new FlightBookingSystemException("Invalid input for cancelbooking. Format: cancelbooking [customerId] [flightId]");
        }
        return new CancelBooking(Integer.parseInt(bookingArgs[0]), Integer.parseInt(bookingArgs[1]));
    }

    // Parses the 'showflight' command
    private static Command parseShowFlight(String[] parts) throws FlightBookingSystemException {
        if (parts.length < 2) {
            throw new FlightBookingSystemException("Invalid input for showflight. Format: showflight [flightId]");
        }
        int flightId = Integer.parseInt(parts[1]);
        return new ShowFlight(flightId);
    }

    // Parses the 'showcustomer' command
    private static Command parseShowCustomer(String[] parts) throws FlightBookingSystemException {
        if (parts.length < 2) {
            throw new FlightBookingSystemException("Invalid input for showcustomer. Format: showcustomer [customerId]");
        }
        int customerId = Integer.parseInt(parts[1]);
        return new ShowCustomer(customerId);
    }

    // Parses a date with retry attempts
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (YYYY-MM-DD): ");
            try {
                return LocalDate.parse(br.readLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again. Attempts remaining: " + attempts);
            }
        }
        throw new FlightBookingSystemException("Failed to parse a valid departure date.");
    }
}