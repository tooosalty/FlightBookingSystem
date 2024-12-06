import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.data.CustomerDataManager;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class CustomerDataManagerTest {

	@Test
    public void testLoadAndStoreData() throws Exception {
        FlightBookingSystem fbs = new FlightBookingSystem();
        CustomerDataManager manager = new CustomerDataManager();

        // Ensure the data file exists
        File file = new File("./resources/data/customers.txt");
        assertTrue(file.exists());

        // Load data
        manager.loadData(fbs);
        assertFalse(fbs.getCustomers().isEmpty());

        // Store data and reload
        manager.storeData(fbs);
        FlightBookingSystem reloadedFbs = new FlightBookingSystem();
        manager.loadData(reloadedFbs);

        assertEquals(fbs.getCustomers().size(), reloadedFbs.getCustomers().size());
    }
}
