package org.pingus.model;

public class RoomInformation {

    private final int capacity;
    private final int registeredPlayers;
    private final String roomId;

    public RoomInformation(int capacity, int registeredPlayers, String roomId) {
        this.capacity = capacity;
        this.registeredPlayers = registeredPlayers;
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRegisteredPlayers() {
        return registeredPlayers;
    }

    public String getRoomId() {
        return roomId;
    }


}
