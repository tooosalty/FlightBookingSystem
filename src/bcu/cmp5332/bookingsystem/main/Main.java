package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            FlightBookingSystem fbs = FlightBookingSystemData.load();

            System.out.println("Flight Booking System");
            System.out.println("Type 'help' to see a list of commands.");

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
                    Command command = CommandParser.parse(input);
                    command.execute(fbs);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}