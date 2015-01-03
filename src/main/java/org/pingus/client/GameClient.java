package org.pingus.client;

import org.springframework.web.client.RestTemplate;

public class GameClient {

    RestTemplate restTemplate = new RestTemplate();

    public String invokame() {
        String mensaje = restTemplate.getForObject("http://localhost:8080", String.class);
        return mensaje;
    }
}
