package org.pingus.client;

import org.pingus.model.GameStatus;
import org.pingus.model.RoomInformation;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

public class GameClient {

    public static final String BASE_URL = "http://localhost:8080";
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

    public RoomInformation getRoomStatus(String roomId) {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append("/getRoomStatus?roomId=").append(roomId);

        RoomInformation info = restTemplate.getForObject(url.toString(), RoomInformation.class);
        return info;
    }

    public ArrayList<RoomInformation> getRoomStatuses() {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append("/getRoomStatuses");

        ArrayList<RoomInformation> info = (ArrayList<RoomInformation>) restTemplate.getForObject(url.toString(), ArrayList.class);
        return info;

    }
}
