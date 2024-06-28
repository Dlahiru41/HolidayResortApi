package DAOs;

import entityClasses.Room;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDAO {

    // Data store for rooms
    private static final Map<Integer, Room> rooms = new HashMap<>();
    private static int nextRoomID = 1;

    // Create a new room
    public Response createRoom(Room room) {
        if (room == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid room data").build();
        }
        room.setId(nextRoomID++); // Use setter assuming it exists
        rooms.put(room.getId(), room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }

    // Read a room by ID
    public Response getRoomById(int id) {
        Room room = rooms.get(id);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        return Response.status(Response.Status.OK).entity(room).build();
    }

    // Update an existing room
    public Response updateRoom(int id, Room updatedRoom) {
        Room existingRoom = rooms.get(id);
        if (existingRoom == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        // Validate and update room data
        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setType(updatedRoom.getType());
        existingRoom.setHotel(updatedRoom.getHotel());
        existingRoom.setBookings(updatedRoom.getBookings());
        // Update other fields as necessary
        return Response.status(Response.Status.OK).entity(existingRoom).build();
    }

    // Delete an existing room
    public Response deleteRoom(int id) {
        if (!rooms.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        rooms.remove(id);
        return Response.status(Response.Status.OK).entity("Room deleted successfully").build();
    }

    // Get all rooms (optional)
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }
}

