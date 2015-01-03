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
	private Card lastCardPlayed = null;
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
			remainingPlayers[playerPosition] = false;
			if (numberOfRemainingPlayers() == 1) {
				gameFinished();
			}
		}
		if (playedCard.getPlayedNumber() == lastCardPlayed.getPlayedNumber()) {
			currentPlayer = nextPlayer(2);
		} else {
			currentPlayer = nextPlayer(1);
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

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public Card getLastCardPlayed() {
		return lastCardPlayed;
	}

	public int getPLAYER_NOT_FOUND() {
		return PLAYER_NOT_FOUND;
	}

	public boolean[] getRemainingPlayers() {
		return remainingPlayers;
	}

	public int[] getArrayOfPositions() {
		return arrayOfPositions;
	}

	public boolean isPresidentToAss() {
		return presidentToAss;
	}

	public boolean isVicepresidentToViceass() {
		return vicepresidentToViceass;
	}
}
