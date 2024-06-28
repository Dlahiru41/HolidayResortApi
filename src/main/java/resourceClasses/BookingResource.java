package resourceClasses;

import DAOs.BookingDAO;
import entityClasses.Booking;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bookings")
public class BookingResource {

    private BookingDAO bookingDAO = new BookingDAO();

    // Create a new booking
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(Booking booking) {
        return bookingDAO.createBooking(booking);
    }

    // Get a booking by ID
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBookingById(@PathParam("id") int id) {
        return bookingDAO.getBookingById(id);
    }

    // Update an existing booking
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response updateBooking(@PathParam("id") int id, Booking booking) {
        return bookingDAO.updateBooking(id, booking);
    }

    // Delete an existing booking
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteBooking(@PathParam("id") int id) {
        return bookingDAO.deleteBooking(id);
    }

    // Get all bookings (optional)
    @GET
    @Produces("application/json")
    public Response getAllBookings() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No bookings found").build();
        }
        return Response.status(Response.Status.OK).entity(bookings).build();
    }
}
