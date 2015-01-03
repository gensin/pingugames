package org.pingus.backass;

import org.pingus.backass.model.Room;
import org.pingus.backass.repository.RoomRepository;
import org.pingus.model.GameStatus;

public class PlayerRegistrant {

    private final RoomRepository roomRepository;

    public PlayerRegistrant(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public GameStatus registerPlayerToRoom(String playerId, String roomId) {

        //TODO safeguard room repo against nulls
        Room room = roomRepository.getRoom(roomId);
        room.registerPlayer(playerId);

        if (room.isFull()) {
            //TODO NOTIFY OTHER PLAYERS
            return GameStatus.READY_TO_PLAY;
        } else {
            return GameStatus.WAITING_FOR_MORE_PLAYERS;
        }
    }
}
