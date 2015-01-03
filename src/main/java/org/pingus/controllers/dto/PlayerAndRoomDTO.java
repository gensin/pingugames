package org.pingus.controllers.dto;

public class PlayerAndRoomDTO {

    private final String playerId;
    private final String roomId;

    public PlayerAndRoomDTO(String playerId, String roomId) {
        this.playerId = playerId;
        this.roomId = roomId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getRoomId() {
        return roomId;
    }
}
