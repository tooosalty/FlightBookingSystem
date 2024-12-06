import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class AddCustomerTest {

	@Test
    public void testAddCustomer() throws Exception {
        FlightBookingSystem fbs = new FlightBookingSystem();
        AddCustomer cmd = new AddCustomer("Alice Brown", "07712345678");
        cmd.execute(fbs);

        assertEquals(1, fbs.getCustomers().size());
        assertEquals("Alice Brown", fbs.getCustomers().get(0).getName());
    }
}
