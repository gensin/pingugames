package org.pingus.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    @RequestMapping("/")
    public String index() {
        return "Bienvenido a mi increible juego!";
    }

    @RequestMapping("/cartas")
    public String cartas() {
        return "Dame cartas!";
    }
}
