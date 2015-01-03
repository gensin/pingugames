package org.pingus.client;


import org.pingus.model.GameStatus;

public class Harness {

    public static void main(String... args) {
        GameClient client = new GameClient();

        System.out.println(client.invokame());

        GameStatus gameStatus = client.joinGame("player1", "roomId1");
        gameStatus = client.joinGame("player2", "roomId1");
        gameStatus = client.joinGame("player3", "roomId1");
        gameStatus = client.joinGame("player4", "roomId1");
        gameStatus = client.joinGame("player5", "roomId1");
        gameStatus = client.joinGame("player6", "roomId1");
    }

}
