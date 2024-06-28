package resourceClasses;

import DAOs.HotelDAO;
import entityClasses.Hotel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hotels")
public class HotelResource {

    private HotelDAO hotelDAO = new HotelDAO();

    // Create a new hotel
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHotel(Hotel hotel) {
        return hotelDAO.createHotel(hotel);
    }

    // Get a hotel by ID
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getHotelById(@PathParam("id") int id) {
        return hotelDAO.getHotelById(id);
    }

    // Update an existing hotel
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHotel(@PathParam("id") int id, Hotel hotel) {
        return hotelDAO.updateHotel(id, hotel);
    }

    // Delete an existing hotel
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHotel(@PathParam("id") int id) {
        return hotelDAO.deleteHotel(id);
    }

    // Get all hotels (optional)
    @GET
    @Produces("application/json")
    public Response getAllHotels() {
        List<Hotel> hotels = hotelDAO.getAllHotels();
        if (hotels.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No hotels found").build();
        }
        return Response.status(Response.Status.OK).entity(hotels).build();
    }
}
