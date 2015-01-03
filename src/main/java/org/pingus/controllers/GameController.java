package org.pingus.controllers;


import org.pingus.backass.PlayerRegistrant;
import org.pingus.backass.repository.RoomRepository;
import org.pingus.model.GameStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    private PlayerRegistrant playerRegistrant = new PlayerRegistrant(new RoomRepository());

    @RequestMapping("/")
    public String index() {
        return "Bienvenido a mi increible juego!";
    }

    @RequestMapping("/cartas")
    public String cartas() {
        return "Dame cartas!";
    }

    @RequestMapping("/join")
    public GameStatus joinGame(@RequestParam(value="playerId") String playerId,
                               @RequestParam(value="roomId") String roomId) {

        return playerRegistrant.registerPlayerToRoom(playerId, roomId);
    }
}
