package org.pingus.backass;

import java.util.ArrayList;
import java.util.Arrays;

import org.pingus.model.Card;
import org.pingus.model.Deck;
import org.pingus.model.Player;
import org.pingus.utils.Constants;

/**
 * Created by gabi on 1/3/15.
 */

public class AssGame {

	// private final int numberOfPlayers = 4;
	private final int numberOfPlayers;
	private final Player[] players;
	private final Deck deck;
	private int currentPlayer;
    private int lastPlayer;
	private ArrayList<Card> lastCardPlayed = null;
	private final int PLAYER_NOT_FOUND = -1;
	private boolean[] remainingPlayers;
	private int[] arrayOfPositions;
	private boolean presidentToAss = false;
	private boolean vicepresidentToViceass = false;

	public AssGame(final Player[] players, int[] arrayOfPositions) {
		this.players = players;
		this.numberOfPlayers = players.length;

		if (arrayOfPositions.length != 4) {
			throw (new Error());
		}
		for (int i = 0; i < arrayOfPositions.length; i++) {
			if (arrayOfPositions[i] > players.length - 1) {
				throw new Error();
			}
		}
		if (numberOfPlayers < 4) {
			throw (new Error());
		}

		this.arrayOfPositions = arrayOfPositions;
		currentPlayer = arrayOfPositions[Constants.ASS];
		this.deck = new Deck(Constants.SPA_TYPE);
		final int deckSize = deck.getDeckSize();
		int residueClass = deckSize - (deckSize / numberOfPlayers)
				* numberOfPlayers;
		for (int i = 0; i < numberOfPlayers; i++) {
			if (residueClass > 0) {
				players[i].receiveCards(deck.dealCards(deckSize
						/ numberOfPlayers + 1));
				residueClass = residueClass - 1;
			} else {
				players[i].receiveCards(deck.dealCards(deckSize
						/ numberOfPlayers));
			}
		}
		remainingPlayers = new boolean[numberOfPlayers];
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

    //TODO add when card is a joker
	public Player playCard(int cardId, int playerID) {
		int playerPosition = getPlayerPositionByID(playerID);

		//Card playedCard = deck.getCardById(cardId);
		// Card playedCard = deck.getCardByID(cardID);
		// Wrong card
		/*if (playedCard == null) {
			return null;
		}*/
		// Wrong PlayerID
		if (playerPosition == PLAYER_NOT_FOUND) {
			return null;
		}
        Player player = players[playerPosition];
		// Player does not have card
		if (!player.hasCard(cardId)) {
			return null;
		}
		// Card Can be Played
        Card playedCard = deck.getCardById(cardId);
		if (!correctPlay(playedCard)) {
			return null;
		}
		player.playedCard(playedCard);
		if (player.getHand().size() == 0) {
			remainingPlayers[playerPosition] = false;
			if (numberOfRemainingPlayers() == 1) {
				gameFinished();
			}
		}
        lastPlayer = currentPlayer;
        if(lastCardPlayed == null){
            currentPlayer = nextPlayer(1);
        }else if (playedCard.getPlayedNumber() == lastCardPlayed.get(0).getPlayedNumber()) {
			currentPlayer = nextPlayer(2);
		} else {
			currentPlayer = nextPlayer(1);
		}
        ArrayList<Card> auxiliaryCard = new ArrayList<>(1);
        auxiliaryCard.set(0, playedCard);
		lastCardPlayed = auxiliaryCard;
		return player;

	}

    public Player playCards(int[] cardIds, int playerID){
        int playerPosition = getPlayerPositionByID(playerID);

        //Card playedCard = deck.getCardById(cardId);
        // Card playedCard = deck.getCardByID(cardID);
        // Wrong card
        //if (deck.getCardById(playedCard.getCardId()) == null) {
            //return null;
        //}
        // Wrong PlayerID
        if (playerPosition == PLAYER_NOT_FOUND) {
            return null;
        }
        Player player = players[playerPosition];
        // Player does not have card
        if (!player.hasCards(cardIds)) {
            return null;
        }
        // Card Can be Played
        ArrayList<Card> playedCards = new ArrayList<Card>();
        for( int i = 0; i<cardIds.length; i++){
            playedCards.add(deck.getCardById(cardIds[i]));
        }
        if (!checkMultipleCards(playedCards)){
            return null;
        }

        if (!correctPlay(playedCards)) {
            return null;
        }
        player.playedCards(cardIds);
        if (player.getHand().size() == 0) {
            remainingPlayers[playerPosition] = false;
            if (numberOfRemainingPlayers() == 1) {
                gameFinished();
            }
        }
        lastPlayer = currentPlayer;
        if(lastCardPlayed == null){
            currentPlayer = nextPlayer(1);
        }
        if (playedCards.get(0).getPlayedNumber() == lastCardPlayed.get(0).getPlayedNumber()) {
            currentPlayer = nextPlayer(2);
        } else {
            currentPlayer = nextPlayer(1);
        }
        ArrayList<Card> auxiliaryCards = new ArrayList<>(playedCards.size());
        //TODO adapt to joker
        auxiliaryCards.set(0,playedCards.get(0));
        return player;

    }
    //TODO modify return to mean that the player starts again
    public boolean pass(int playerID){
        int playerPosition = getPlayerPositionByID(playerID);
        Player player = players[playerPosition];
        currentPlayer = nextPlayer(1);
        if (currentPlayer == lastPlayer){
            lastCardPlayed = null;
            return false;
        }
        return true;


    }

	private void gameFinished() {

	}

	private boolean correctPlay(Card playedCard) {
        if (lastCardPlayed == null){
            return true;
        }
        else{
		    return firstCardIsBiggerOrEqual(playedCard.getPlayedNumber(),lastCardPlayed.get(0).getPlayedNumber());
        }
	}
    //TODO accept jokers
    private boolean correctPlay(ArrayList<Card> playedCards){
        if(lastCardPlayed == null ){
            return true;
        }
        else if( playedCards.size() != lastCardPlayed.size())
        {return false;}
        else{
            return firstCardIsBiggerOrEqual(playedCards.get(0).getPlayedNumber(),lastCardPlayed.get(0).getPlayedNumber());
        }
    }
    //TODO accept jokers
    private boolean checkMultipleCards(ArrayList<Card> playedCards){
        int value = playedCards.get(0).getCardNumber();
        for( int i = 1; i< playedCards.size(); i++){
            if( playedCards.get(i).getCardNumber() != value){
                return false;

            }
        }
        return true;
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

	private int numberOfRemainingPlayers() {
		int numberOfRemainingPlayers = 0;
		for (int i = 0; i < numberOfPlayers; i++) {
			if (remainingPlayers[i] == true) {
				numberOfRemainingPlayers += 1;

			}
		}
		return numberOfRemainingPlayers;
	}

	private int nextPlayer(int numberOfSteps) {
		int auxiliaryPlayer = currentPlayer;
		while (numberOfSteps > 0) {
			auxiliaryPlayer = (auxiliaryPlayer + 1) / numberOfPlayers;
			if (remainingPlayers[auxiliaryPlayer]) {
				numberOfSteps = -1;
			}

		}
		return auxiliaryPlayer;
	}

	public void viceassToVicepresident() {
		Player viceass = players[Constants.VICEASS];
		Player vicepresident = players[Constants.VICEPRESIDENT];
		Card bestCardViceass = bestCard(viceass);
		viceass.playedCard(bestCardViceass);
		vicepresident.receiveCard(bestCardViceass);
	}

	public void assToPresident() {
		Player ass = players[Constants.ASS];
		Player president = players[Constants.PRESIDENT];
		Card bestCardAss = bestCard(ass);
		Card secondBestCardAss = bestCard(ass, bestCardAss.getCardId());
		ass.playedCard(bestCardAss);
		ass.playedCard(secondBestCardAss);
		president.receiveCard(bestCardAss);
		president.receiveCard(secondBestCardAss);
	}
    public void presidentToAss(Card firstCard, Card secondCard){
        if(presidentToAss){
            throw new Error();
        }
        Player president = players[arrayOfPositions[Constants.PRESIDENT]];
        Player ass = players[arrayOfPositions[Constants.ASS]];
        if(!president.hasCard(firstCard) || !president.hasCard(secondCard))
        {
            throw new Error();
        }
        president.playedCard(firstCard);
        president.playedCard(secondCard);
        ass.receiveCard(firstCard);
        ass.receiveCard(secondCard);
        presidentToAss=true;
    }
    public void vicepresidentToViceass(Card card){
        if(vicepresidentToViceass){
            throw new Error();
        }
        Player vicepresident = players[arrayOfPositions[Constants.VICEPRESIDENT]];
        Player viceass = players[arrayOfPositions[Constants.VICEASS]];
        if(!vicepresident.hasCard(card)){
            throw new Error();
        }
        vicepresident.playedCard(card);
        viceass.receiveCard(card);
        vicepresidentToViceass=true;

    }


	private Card bestCard(Player player) {
		ArrayList<Card> hand = player.getHand();
		Card bestCard = hand.get(0);
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getIsJoker()) {
				bestCard = hand.get(i);
			} else if (firstCardIsBiggerOrEqual(hand.get(i).getPlayedNumber(),
					bestCard.getPlayedNumber())) {
				bestCard = hand.get(i);
			}

		}
		return bestCard;

	}

	private Card bestCard(Player player, int cardIdToAvoid) {
		ArrayList<Card> hand = player.getHand();
		Card bestCard = hand.get(0);
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getCardId() != cardIdToAvoid) {
				if (hand.get(i).getIsJoker()) {
					bestCard = hand.get(i);
				} else if (firstCardIsBiggerOrEqual(hand.get(i)
						.getPlayedNumber(), bestCard.getPlayedNumber())) {
					bestCard = hand.get(i);
				}

			}

		}
		return bestCard;

	}


}
