package org.pingus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.pingus.utils.Constants;

/**
 * 
 * @author Alvaro
 *
 */

public class Deck {

	private int numPlayers;
	private int typeDeck;
	private int numberOfJokers;
	private ArrayList<Card> deck;
	private ArrayList<Card> originalDeck;

	public Deck(int typeDeck) {
		this.typeDeck = typeDeck;
		switch (typeDeck) {
		case 1:
			this.deck = this.createSpanishDeck(false);
			this.originalDeck = this.createSpanishDeck(false);
			this.numberOfJokers = 0;
			break;
		case 2:
			this.deck = this.createSpanishDeck(true);
			this.originalDeck = this.createSpanishDeck(true);
			this.numberOfJokers = Constants.SPA_NUM_JOKERS;
			break;
		case 3:
			this.deck = this.createEnglishDeck(false);
			this.originalDeck = this.createEnglishDeck(false);
			this.numberOfJokers = 0;
			break;
		case 4:
			this.deck = this.createEnglishDeck(true);
			this.originalDeck = this.createEnglishDeck(true);
			this.numberOfJokers = Constants.ENG_NUM_JOKERS;
			break;
		case 5:
			this.deck = this.createEnglishDeck(true);
			this.originalDeck = this.createEnglishDeck(true);
			this.numberOfJokers = Constants.ENG_NUM_FULLJOKERS;
			break;
		}
		this.shuffle();
	}

	private ArrayList<Card> createSpanishDeck(boolean jokers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		String[] clubs = { Constants.SPA_CLUBS, Constants.SPA_SWORDS,
				Constants.SPA_CUPS, Constants.SPA_GOLDS };
		for (int i = 0; i < clubs.length; i++) {
			for (int j = 0; j < Constants.SPA_CLUBLENGTH; j++) {
				String cardKind = "";
				switch (j) {
				case 0:
					cardKind = Constants.SPA_ACE;
					break;
				case 9:
					cardKind = Constants.SPA_JACK;
					break;
				case 10:
					cardKind = Constants.SPA_KNIGHT;
					break;
				case 11:
					cardKind = Constants.SPA_KING;
					break;
				}
				cards.add(((i + 1) * (j + 1)) - 1, new Card(
						((i + 1) * (j + 1)), j + 1, clubs[i], cardKind));
			}
		}
		if (jokers) {
			cards.add(cards.size(), new Card(cards.size() + 1, -1, "",
					Constants.SPA_JOKER, true));
			cards.add(cards.size(), new Card(cards.size() + 1, -1, "",
					Constants.SPA_JOKER, true));
		}
		return cards;
	}
	
	private ArrayList<Card> createEnglishDeck(boolean jokers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		String[] clubs = { Constants.ENG_SPADES, Constants.ENG_DIAMONDS,
				Constants.ENG_CLUBS, Constants.ENG_HEARTS };
		for (int i = 0; i < clubs.length; i++) {
			for (int j = 0; j < Constants.ENG_CLUBLENGTH; j++) {
				String cardKind = "";
				switch (j) {
				case 0:
					cardKind = Constants.ENG_ACE;
					break;
				case 10:
					cardKind = Constants.ENG_JACK;
					break;
				case 11:
					cardKind = Constants.ENG_QUEEN;
					break;
				case 12:
					cardKind = Constants.ENG_KING;
					break;
				}
				cards.add(((i + 1) * (j + 1)) - 1, new Card(
						((i + 1) * (j + 1)), j + 1, clubs[i], cardKind));
			}
		}
		if (jokers) {
			for(int i=0;i<numberOfJokers;i++){
				cards.add(cards.size(), new Card(cards.size() + 1, -1, "",
						Constants.ENG_JOKER, true));
			}
		}
		return cards;
	}

	public void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(this.deck, new Random(seed));
	}

	public ArrayList<Card> dealCards(int numberOfCards) {
		ArrayList<Card> hand = new ArrayList<Card>();
		for (int i = 0; i < numberOfCards; i++) {
			hand.add(this.deck.get(i));
			this.deck.remove(i);
		}
		return hand;
	}

	public int getDeckSize() {
		return this.deck.size();
	}

	public int getTotalDeckSize() {
		return this.originalDeck.size();
	}

	public Card getCardById(int cardId) {
		Card card;
		if ((cardId-1)>0 && (cardId-1)<this.originalDeck.size()) {
			card = this.originalDeck.get(cardId-1);
		} else {
			card = null;
		}
		return card;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getTypeDeck() {
		return typeDeck;
	}

	public ArrayList<Card> getOriginalDeck() {
		return originalDeck;
	}

	public int getNumberOfJokers() {
		return numberOfJokers;
	}
}
