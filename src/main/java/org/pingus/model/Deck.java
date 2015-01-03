package org.pingus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.pingus.utils.Constants;

public class Deck {

	private int numPlayers;
	private int deckType;
	private ArrayList<Card> deck;
	private ArrayList<Card> originalDeck;

	public Deck(int typeDeck) {
		this.deckType = typeDeck;
		if (this.deckType == Constants.SPA_TYPE
				|| this.deckType == Constants.SPA_TYPE_JOKERS) {
			this.deck = this.createSpanishDeck(typeDeck);
			this.originalDeck = this.createSpanishDeck(typeDeck);
		}
		this.shuffle();
	}

	private ArrayList<Card> createSpanishDeck(int jokers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		String[] clubs = { Constants.SPA_CLUBS, Constants.SPA_SWORDS,
				Constants.SPA_CUPS, Constants.SPA_GOLDS };
		for (int i = 0; i < clubs.length; i++) {
			for (int j = 0; j < Constants.SPA_TYPE_JOKERS; j++) {
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
				case 12:
				}
				cards.add(((i + 1) * (j + 1)) - 1, new Card(
						((i + 1) * (j + 1)), j + 1, clubs[i], cardKind));
			}
		}
		if (jokers == Constants.SPA_TYPE_JOKERS) {
			cards.add(cards.size(), new Card(cards.size() + 1, -1, "",
					Constants.SPA_JOKER, Constants.JOKER));
			cards.add(cards.size(), new Card(cards.size() + 1, -1, "",
					Constants.SPA_JOKER, Constants.JOKER));
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
		int len = 0;
		if (this.deckType == Constants.SPA_TYPE
				|| this.deckType == Constants.SPA_TYPE_JOKERS) {
			len = Constants.SPA_DECKLENGTH;
		}
		return len;
	}

	public Card getCardById(int idCard) {
		Card card = null;
		try {
			card = this.originalDeck.get(idCard);
		} catch (IndexOutOfBoundsException e) {
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
		return deckType;
	}

	public ArrayList<Card> getOriginalDeck() {
		return originalDeck;
	}
}
