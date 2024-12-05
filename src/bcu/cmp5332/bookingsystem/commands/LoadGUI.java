package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGUI implements Command {

    @Override
    public void execute(FlightBookingSystem fbs) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(fbs));
    }

    private void createAndShowGUI(FlightBookingSystem fbs) {
        JFrame frame = new JFrame("Flight Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Buttons for enhanced features
        JButton addFlightButton = new JButton("Add Flight");
        JButton addBookingButton = new JButton("Add Booking");
        JButton searchCustomerButton = new JButton("Search Customer");
        JButton searchFlightButton = new JButton("Search Flight");
        JButton exitButton = new JButton("Exit");

        // Add action listeners for buttons
        addFlightButton.addActionListener(e -> addFlightGUI(fbs));
        addBookingButton.addActionListener(e -> addBookingGUI(fbs));
        searchCustomerButton.addActionListener(e -> searchCustomerGUI(fbs));
        searchFlightButton.addActionListener(e -> searchFlightGUI(fbs));
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        panel.add(new JLabel("Welcome to the Flight Booking System", JLabel.CENTER));
        panel.add(addFlightButton);
        panel.add(addBookingButton);
        panel.add(searchCustomerButton);
        panel.add(searchFlightButton);
        panel.add(exitButton);

        // Add panel to frame and display
        frame.add(panel);
        frame.setVisible(true);
    }

    private void addFlightGUI(FlightBookingSystem fbs) {
        JTextField flightNumberField = new JTextField();
        JTextField originField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField departureDateField = new JTextField();

        Object[] fields = {
            "Flight Number:", flightNumberField,
            "Origin:", originField,
            "Destination:", destinationField,
            "Departure Date (YYYY-MM-DD):", departureDateField,
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String flightNumber = flightNumberField.getText();
                String origin = originField.getText();
                String destination = destinationField.getText();
                LocalDate departureDate = LocalDate.parse(departureDateField.getText());

                Command addFlightCommand = new AddFlight(flightNumber, origin, destination, departureDate);
                addFlightCommand.execute(fbs);

                JOptionPane.showMessageDialog(null, "Flight added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addBookingGUI(FlightBookingSystem fbs) {
        JTextField customerIdField = new JTextField();
        JTextField flightIdField = new JTextField();

        Object[] fields = {
            "Customer ID:", customerIdField,
            "Flight ID:", flightIdField,
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Booking", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                int flightId = Integer.parseInt(flightIdField.getText());

                Command addBookingCommand = new AddBooking(customerId, flightId);
                addBookingCommand.execute(fbs);

                JOptionPane.showMessageDialog(null, "Booking added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchCustomerGUI(FlightBookingSystem fbs) {
        String input = JOptionPane.showInputDialog("Enter Customer ID to search:");
        try {
            int customerId = Integer.parseInt(input);
            Command showCustomerCommand = new ShowCustomer(customerId);
            showCustomerCommand.execute(fbs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchFlightGUI(FlightBookingSystem fbs) {
        String input = JOptionPane.showInputDialog("Enter Flight ID to search:");
        try {
            int flightId = Integer.parseInt(input);
            Command showFlightCommand = new ShowFlight(flightId);
            showFlightCommand.execute(fbs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}