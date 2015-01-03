package org.pingus.controllers.dto;

public class PlayerAndRoomDTO {

    private String playerId;
    private String roomId;

    public PlayerAndRoomDTO() {
    }

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

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "PlayerAndRoomDTO{" +
                "playerId='" + playerId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
