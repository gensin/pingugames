package org.pingus.client;

import org.pingus.model.GameStatus;
import org.springframework.web.client.RestTemplate;

public class GameClient {

    public static final String BASE_URL = "http://localhost:8080";
    public static final String JOIN_GAME_URL = "/join?playerId={}&roomId={}";
    RestTemplate restTemplate = new RestTemplate();

    public String invokame() {
        String mensaje = restTemplate.getForObject(BASE_URL, String.class);
        return mensaje;
    }

    public GameStatus joinGame(String playerId, String roomId) {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append("/join?playerId=").append(playerId);
        url.append("&roomId=").append(roomId);

        GameStatus gameStatus = restTemplate.getForObject(url.toString(), GameStatus.class);
        return gameStatus;
    }
}
