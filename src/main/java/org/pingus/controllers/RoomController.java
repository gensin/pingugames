package org.pingus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pingus.backass.PlayerRegistrant;
import org.pingus.backass.repository.RoomRepository;
import org.pingus.controllers.dto.PlayerAndRoomDTO;
import org.pingus.model.GameStatus;
import org.pingus.model.Room;
import org.pingus.model.RoomInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
public class RoomController {

    private final RoomRepository roomRepository;
    private final PlayerRegistrant playerRegistrant;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RoomController(RoomRepository roomRepository, PlayerRegistrant playerRegistrant) {
        this.roomRepository = roomRepository;
        this.playerRegistrant = playerRegistrant;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public GameStatus joinGame(@RequestBody String playerAndRoomBody) throws IOException {
        PlayerAndRoomDTO playerAndRoomDTO = objectMapper.readValue(playerAndRoomBody, PlayerAndRoomDTO.class);

        return playerRegistrant.registerPlayerToRoom(playerAndRoomDTO.getPlayerId(), playerAndRoomDTO.getRoomId());
    }

    // puede que no lo necesitemos
    @RequestMapping("/getRoomStatus")
    public RoomInformation getRoomStatus(
            @RequestParam(value = "roomId") String roomId) {
        // TODO SAFEGUARD against null
        return roomRepository.getRoom(roomId).getStatus();
    }

    @RequestMapping("/getRoomStatuses")
    public RoomInformation[] getRoomStatuses() {
        Collection<Room> rooms = roomRepository.getRooms();

        RoomInformation[] statuses = new RoomInformation[rooms.size()];

        int index = 0;
        for (Room room : rooms) {
            statuses[index++] = room.getStatus();
        }

        return statuses;
    }

}
