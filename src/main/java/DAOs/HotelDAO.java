package DAOs;

import entityClasses.Hotel;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelDAO {

    // Data store for hotels
    private static final Map<Integer, Hotel> hotels = new HashMap<>();
    private static int nextHotelID = 1;

    // Create a new hotel
    public Response createHotel(Hotel hotel) {
        if (hotel == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid hotel data").build();
        }
        hotel.setId(nextHotelID++); // Use setter assuming it exists
        hotels.put(hotel.getId(), hotel);
        return Response.status(Response.Status.CREATED).entity(hotel).build();
    }

    // Read a hotel by ID
    public Response getHotelById(int id) {
        Hotel hotel = hotels.get(id);
        if (hotel == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Hotel not found").build();
        }
        return Response.status(Response.Status.OK).entity(hotel).build();
    }

    // Update an existing hotel
    public Response updateHotel(int id, Hotel updatedHotel) {
        Hotel existingHotel = hotels.get(id);
        if (existingHotel == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Hotel not found").build();
        }
        // Validate and update hotel data
        existingHotel.setName(updatedHotel.getName());
        existingHotel.setLocation(updatedHotel.getLocation());
        existingHotel.setRooms(updatedHotel.getRooms());
        // Update other fields as necessary
        return Response.status(Response.Status.OK).entity(existingHotel).build();
    }

    // Delete an existing hotel
    public Response deleteHotel(int id) {
        if (!hotels.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Hotel not found").build();
        }
        hotels.remove(id);
        return Response.status(Response.Status.OK).entity("Hotel deleted successfully").build();
    }

    // Get all hotels (optional)
    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotels.values());
    }
}
