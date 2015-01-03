package org.pingus.backass.repository;

import org.pingus.model.Room;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RoomRepository {

    private static final Map<String, Room> rooms = new HashMap<>(5);

    static {
        rooms.put("roomId1", new Room("roomId1", 4));
        rooms.put("roomId2", new Room("roomId2", 4));
        rooms.put("roomId3", new Room("roomId3", 4));
        rooms.put("roomId4", new Room("roomId4", 4));
        rooms.put("roomId5", new Room("roomId5", 4));
    }

    public Collection<Room> getRooms() {
        return rooms.values();
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }
}
