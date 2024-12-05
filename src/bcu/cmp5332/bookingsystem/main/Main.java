package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            // Load data into FlightBookingSystem
            FlightBookingSystem fbs = FlightBookingSystemData.load();

            System.out.println("Flight Booking System");
            System.out.println("Type 'help' for a list of commands.");

            // Read user input in a loop
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("> ");
                String input = reader.readLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    FlightBookingSystemData.store(fbs);
                    System.out.println("Data saved. Exiting program...");
                    break;
                }

                try {
                    // Parse and execute commands
                    Command command = CommandParser.parse(input);

                    // Special case for LoadGUI to prevent blocking
                    if (command instanceof LoadGUI) {
                        ((LoadGUI) command).execute(fbs);
                    } else {
                        command.execute(fbs);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}