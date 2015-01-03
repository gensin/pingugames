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
    private int PLAYER_NOT_FOUND = -1;

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
    private int getPlayerPositionByID(int ID){
        int playerPosition = PLAYER_NOT_FOUND;
        for(int i= 0; i<numberOfPlayers; i++){
            if(players[i].getID() == ID){
                playerPosition = i;
                break;
            }
        }
        return playerPosition;
    }

    private Player[] getPlayers(){
        return players;
    }
    public Player playCard(Card playedCard, int playerID ){
        int playerPosition = getPlayerPositionByID(playerID);
        Player player = players[playerPosition];
        //Card playedCard = deck.getCardByID(cardID);
        //Wrong card
        if (!deck.validateCard(playedCard)){
            return null;
        }
        //Wrong PlayerID
        if(playerPosition == PLAYER_NOT_FOUND){
            return null;

        }
        //Player does not have card
        if(!player.hasCard(playedCard)){
            return null;
        }
        //Card Can be Played
        if(!correctPlay(playedCard)){
            return null;
        }
        player.playedCard(playedCard);
        if(player.getHand().size() == 0){
        gameFinished();
        }
        if (playedCard.getPlayedNumber() == lastCardPlayed.getPlayedNumber()){
            currentPlayer = (currentPlayer + 2)/numberOfPlayers;
        }
        else{
            currentPlayer = (currentPlayer + 1)/numberOfPlayers;
        }
        lastCardPlayed = playedCard;
        return player;

    }
    private void gameFinished(){

    }
    private boolean correctPlay(Card playedCard){
        return firstCardIsBiggerOrEqual(playedCard.getPlayedNumber(),lastCardPlayed.getPlayedNumber());

   }
    private boolean firstCardIsBiggerOrEqual(int firstCardNumber, int secondCardNumber){
        if(secondCardNumber == 0){
            if(firstCardNumber==0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(secondCardNumber == 2){
            if(firstCardNumber == 0 || firstCardNumber == 2){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return firstCardNumber>=secondCardNumber;
        }
    }
}
