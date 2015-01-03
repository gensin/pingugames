package org.pingus.backass.repository;

import org.pingus.model.Room;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
public class RoomRepository {


    private static final Map<String, Room> rooms = new TreeMap<>();


    static {
        rooms.put("Sala 1", new Room("Sala 1", 4));
        rooms.put("Sala 2", new Room("Sala 2", 4));
        rooms.put("Sala 3", new Room("Sala 3", 4));
        rooms.put("Sala 4", new Room("Sala 4", 4));
        rooms.put("Sala 5", new Room("Sala 5", 4));
    }

    public Collection<Room> getRooms() {
        return rooms.values();
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }
}
