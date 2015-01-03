package org.pingus.backass;

import org.pingus.model.Card;
import org.pingus.model.Deck;
import org.pingus.model.Player;

import java.util.Arrays;

/**
 * Created by gabi on 1/3/15.
 */

public class AssGame {

	private final int numberOfPlayers = 4;
	private final Player[] players;
	private final Deck deck;
	private int currentPlayer = 0;
	private Card lastCardPlayed = null;
	private final int PLAYER_NOT_FOUND = -1;
    private boolean[] remainingPlayers;

	public AssGame(final Player[] players, final Deck deck) {
		this.players = players;
		this.deck = deck;
		final int deckSize = deck.getDeckSize();
		int residueClass = deckSize - (deckSize / numberOfPlayers)
				* numberOfPlayers;
		for (int i = 0; i < numberOfPlayers; i++) {
			if (residueClass > 0) {
				players[i].receiveCards(deck.dealCards(deckSize/ numberOfPlayers + 1));
				residueClass = residueClass - 1;
			} else {
				players[i].receiveCards(deck.dealCards(deckSize/ numberOfPlayers));
			}
		}
        remainingPlayers=new boolean[numberOfPlayers];
        Arrays.fill(remainingPlayers, true);

	}

	private int getPlayerPositionByID(int ID) {
		int playerPosition = PLAYER_NOT_FOUND;
		for (int i = 0; i < numberOfPlayers; i++) {
			if (players[i].getID() == ID) {
				playerPosition = i;
				break;
			}
		}
		return playerPosition;
	}

	public Player[] getPlayers() {
		return players;
	}

	public Player playCard(Card playedCard, int playerID) {
		int playerPosition = getPlayerPositionByID(playerID);
		Player player = players[playerPosition];
		// Card playedCard = deck.getCardByID(cardID);
		// Wrong card
		if (deck.getCardById(playedCard.getCardId()) == null) {
			return null;
		}
		// Wrong PlayerID
		if (playerPosition == PLAYER_NOT_FOUND) {
			return null;

		}
		// Player does not have card
		if (!player.hasCard(playedCard)) {
			return null;
		}
		// Card Can be Played
		if (!correctPlay(playedCard)) {
			return null;
		}
		player.playedCard(playedCard);
		if (player.getHand().size() == 0) {
            remainingPlayers[playerPosition]=false;
            if(numberOfRemainingPlayers()==1){
			gameFinished();
            }
		}
		if (playedCard.getPlayedNumber() == lastCardPlayed.getPlayedNumber()) {
			currentPlayer=nextPlayer(2);
        } else {
			currentPlayer=nextPlayer(1);
		}
		lastCardPlayed = playedCard;
		return player;

	}

	private void gameFinished() {

	}

	private boolean correctPlay(Card playedCard) {
		return firstCardIsBiggerOrEqual(playedCard.getPlayedNumber(),
				lastCardPlayed.getPlayedNumber());

	}

	private boolean firstCardIsBiggerOrEqual(int firstCardNumber,
			int secondCardNumber) {
		if (secondCardNumber == 0) {
			if (firstCardNumber == 0) {
				return true;
			} else {
				return false;
			}
		} else if (secondCardNumber == 2) {
			if (firstCardNumber == 0 || firstCardNumber == 2) {
				return true;
			} else {
				return false;
			}
		} else {
			return firstCardNumber >= secondCardNumber;
		}
	}
    private int numberOfRemainingPlayers(){
        int numberOfRemainingPlayers=0;
        for(int i = 0; i<numberOfPlayers; i++){
            if(remainingPlayers[i] == true){
                numberOfRemainingPlayers+=1;

            }
        }
        return numberOfRemainingPlayers;
    }
    private int nextPlayer(int numberOfSteps){
        int auxiliaryPlayer=currentPlayer;
        while(numberOfSteps>0){
            auxiliaryPlayer= (auxiliaryPlayer+1)/numberOfPlayers;
            if(remainingPlayers[auxiliaryPlayer]){
                numberOfSteps=-1;
            }

        }
        return auxiliaryPlayer;
    }
}
