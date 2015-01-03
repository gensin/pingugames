package org.pingus.backass.model;


import java.util.HashSet;
import java.util.Set;

public class Room {

    private final int capacity;
    private final Set<String> registeredPlayers;

    public Room(int capacity) {
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
        registeredPlayers.remove(playerId);
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return capacity == registeredPlayers.size();
    }
}
