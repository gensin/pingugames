package org.pingus.controllers;


import org.pingus.backass.AssGame;
import org.pingus.backass.repository.RoomRepository;
import org.pingus.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

	private final RoomRepository roomRepository;

	@Autowired
	public GameController(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@RequestMapping("/")
	public String index() {
		return "Bienvenido a mi increible juego!";
	}
	@RequestMapping("/cartas")
	public String cartas() {
		return "Dame cartas!";
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




}
