package org.pingus.backass.repository;

import org.pingus.backass.model.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomRepository {

    private static final Map<String, Room> rooms = new HashMap<>(5);

    static {
        rooms.put("roomId1", new Room(4));
        rooms.put("roomId1", new Room(4));
        rooms.put("roomId1", new Room(4));
        rooms.put("roomId1", new Room(4));
        rooms.put("roomId1", new Room(4));
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }
}
