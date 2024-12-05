package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

public interface DataManager {
    void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    void storeData(FlightBookingSystem fbs) throws IOException;
}