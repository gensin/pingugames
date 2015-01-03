package org.pingus.controllers;

import java.util.Collection;

import org.pingus.backass.AssGame;
import org.pingus.backass.PlayerRegistrant;
import org.pingus.backass.repository.RoomRepository;
import org.pingus.model.GameStatus;
import org.pingus.model.Player;
import org.pingus.model.Room;
import org.pingus.model.RoomInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

	private final RoomRepository roomRepository;
	private final PlayerRegistrant playerRegistrant;

	@Autowired
	public GameController(RoomRepository roomRepository, PlayerRegistrant playerRegistrant) {
		this.roomRepository = roomRepository;
		this.playerRegistrant = playerRegistrant;
	}

	@RequestMapping("/")
	public String index() {
		return "Bienvenido a mi increible juego!";
	}
	@RequestMapping("/cartas")
	public String cartas() {
		return "Dame cartas!";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public GameStatus joinGame(
			@RequestBody String playerId,
			@RequestBody String roomId) {
		
		System.out.println("hello " + playerId + " asd " + roomId);

		return playerRegistrant.registerPlayerToRoom(playerId, roomId);
	}

	@RequestMapping("/playCardAss")
	public Player playCardAss(
			@RequestParam(value = "playerId") String playerId,
			@RequestParam(value = "roomId") String roomId,
			@RequestParam(value = "cardId") String cardId) {
		AssGame game = roomRepository.getRoom(roomId).getRoomGame();

		if (game != null) {
			return game.playCard(Integer.parseInt(cardId),
					Integer.parseInt(playerId));
		} else {
			return null;
		}
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
