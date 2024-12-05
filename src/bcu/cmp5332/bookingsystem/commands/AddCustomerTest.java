import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddCustomerTest {

    @Test
    public void testAddCustomer() throws Exception {
        FlightBookingSystem fbs = new FlightBookingSystem();
        AddCustomer cmd = new AddCustomer("Alice Brown", "07712345678");
        cmd.execute(fbs);

        assertEquals(1, fbs.getCustomers().size());
        assertEquals("Alice Brown", fbs.getCustomers().get(0).getName());
    }
}