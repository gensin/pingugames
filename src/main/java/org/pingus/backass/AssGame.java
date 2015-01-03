package org.pingus.backass;

import org.pingus.model.Deck;
import org.pingus.model.Player;

/**
 * Created by gabi on 1/3/15.
 */
public class AssGame {
    private final int numberOfPlayers = 4;
    private final Player[] players;
    private final Deck deck;
    private int currentPlayer=0;
    private Card lastCardPlayed = null;

    public AssGame(final Player[] players, final Deck deck){
        this.players = players;
        this.deck = deck;
        final int deckSize = deck.getRemainingDeckSize();
        int residueClass = deckSize-(deckSize/numberOfPlayers)*numberOfPlayers;
        for (int i = 0; i<numberOfPlayers; i++){
            if(residueClass>0){
                players[i].receiveCards(deck.dealCards(deckSize/numberOfPlayers+1));
                residueClass = residueClass-1;
            }
            else{
                players[i].receiveCards(deck.dealCards(deckSize/numberOfPlayers));
            }
        }


    }
    private int getPlayerByID(int ID){
        int playerPosition = -1;
        for(int i= 0; i<numberOfPlayers; i++){
            if(players[i].getID() == ID){
                playerPosition = i;
                break;
            }
        }
        return playerPosition;
    }

    public Player[] getPlayers(){
        return players;
    }
    public void playCard(int cardID; int playerID ){

    }
}
