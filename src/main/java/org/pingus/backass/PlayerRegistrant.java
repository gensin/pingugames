package org.pingus.backass;

import org.pingus.model.Room;
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

        if (room.hasPlayer(playerId)) {
            return GameStatus.YA_TE_HAS_APUNTAO_BACALAO;
        }
        boolean registeredPlayer = room.registerPlayer(playerId);

        if (!registeredPlayer) {
            return GameStatus.A_TU_PUTA_CASA;
        }
        else if (room.isFull()) {
            //TODO NOTIFY OTHER PLAYERS
            return GameStatus.READY_TO_PLAY;
        } else {
            return GameStatus.WAITING_FOR_MORE_PLAYERS;
        }
    }
}
