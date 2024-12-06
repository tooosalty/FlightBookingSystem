import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

class FlightTest {

	@Test
    public void testAddPassenger() {
        Flight flight = new Flight(1, "LH123", "Birmingham", "Munich", LocalDate.now());
        Customer customer = new Customer(1, "Alice", "07712345678");

        try {
            flight.addPassenger(customer);
        } catch (FlightBookingSystemException e) {
            fail("Exception should not have been thrown");
        }

        assertTrue(flight.getPassengers().contains(customer));
    }

    @Test(expected = FlightBookingSystemException.class)
    public void testAddDuplicatePassenger() throws FlightBookingSystemException {
        Flight flight = new Flight(1, "LH123", "Birmingham", "Munich", LocalDate.now());
        Customer customer = new Customer(1, "Alice", "07712345678");

        flight.addPassenger(customer);
        flight.addPassenger(customer);  // This should throw an exception
    }

    @Test
    public void testRemovePassenger() throws FlightBookingSystemException {
        Flight flight = new Flight(1, "LH123", "Birmingham", "Munich", LocalDate.now());
        Customer customer = new Customer(1, "Alice", "07712345678");

        flight.addPassenger(customer);
        flight.removePassenger(customer);

        assertFalse(flight.getPassengers().contains(customer));
    }
}
