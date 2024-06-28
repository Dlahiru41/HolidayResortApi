package resourceClasses;

import DAOs.RoomDAO;
import entityClasses.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rooms")
public class RoomResource {

    private RoomDAO roomDAO = new RoomDAO();

    // Create a new room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {
        return roomDAO.createRoom(room);
    }

    // Get a room by ID
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getRoomById(@PathParam("id") int id) {
        return roomDAO.getRoomById(id);
    }

    // Update an existing room
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRoom(@PathParam("id") int id, Room room) {
        return roomDAO.updateRoom(id, room);
    }

    // Delete an existing room
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("id") int id) {
        return roomDAO.deleteRoom(id);
    }

    // Get all rooms (optional)
    @GET
    @Produces("application/json")
    public Response getAllRooms() {
        List<Room> rooms = roomDAO.getAllRooms();
        if (rooms.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No rooms found").build();
        }
        return Response.status(Response.Status.OK).entity(rooms).build();
    }
}

