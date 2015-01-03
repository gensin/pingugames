package org.pingus.model;


import java.util.HashSet;
import java.util.Set;

public class Room {

    private final String roomId;
    private final int capacity;
    private final Set<String> registeredPlayers;

    public Room(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.registeredPlayers = new HashSet<>();
    }

    /**
     *
     * @param playerId the player wishing to join this room
     * @return true iff the player successfully joined the room
     */
    public boolean registerPlayer(String playerId) {
        if (registeredPlayers.size() == capacity) {
            return false;
        }

        registeredPlayers.add(playerId);
        return true;
    }

    public void unregisterPlayer(String playerId) {
        //TODO notify front end to update numbers of this room
        registeredPlayers.remove(playerId);
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return capacity == registeredPlayers.size();
    }

    public boolean hasPlayer(String playerId) {
        return registeredPlayers.contains(playerId);
    }

    public RoomInformation getStatus() {
        return new RoomInformation(capacity, registeredPlayers.size(), roomId);
    }
}
