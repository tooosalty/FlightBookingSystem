package bcu.cmp5332.bookingsystem.main;

/**
 * FlightBookingSystemException extends {@link Exception} class and is a custom exception
 * that is used to notify the user about errors or invalid commands.
 * 
 */
public class FlightBookingSystemException extends Exception {

	 // Unique identifier for this serializable class
    private static final long serialVersionUID = 1L;

    public FlightBookingSystemException(String message) {
        super(message);
    }

    public FlightBookingSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}