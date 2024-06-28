package DAOs;

import entityClasses.Booking;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDAO {

    // Data store for bookings
    private static final Map<Integer, Booking> bookings = new HashMap<>();
    private static int nextBookingID = 1;

    // Create a new booking
    public Response createBooking(Booking booking) {
        if (booking == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid booking data").build();
        }
        booking.setId(nextBookingID++); // Use setter assuming it exists
        bookings.put(booking.getId(), booking);
        return Response.status(Response.Status.CREATED).entity(booking).build();
    }

    // Read a booking by ID
    public Response getBookingById(int id) {
        Booking booking = bookings.get(id);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
        }
        return Response.status(Response.Status.OK).entity(booking).build();
    }

    // Update an existing booking
    public Response updateBooking(int id, Booking updatedBooking) {
        Booking existingBooking = bookings.get(id);
        if (existingBooking == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
        }
        // Validate and update booking data
        existingBooking.setCustomerId(updatedBooking.getCustomerId());
        existingBooking.setRoomId(updatedBooking.getRoomId());
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        // Update other fields as necessary
        return Response.status(Response.Status.OK).entity(existingBooking).build();
    }

    // Delete an existing booking
    public Response deleteBooking(int id) {
        if (!bookings.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
        }
        bookings.remove(id);
        return Response.status(Response.Status.OK).entity("Booking deleted successfully").build();
    }

    // Get all bookings (optional)
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}

