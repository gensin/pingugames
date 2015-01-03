package main.java.org.pingus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.java.org.pingus.utils.Constants;

public class Deck {

	private Integer numPlayers;
	private Integer typeDeck;
	private ArrayList<Card> deck;

	public Deck(Integer typeDeck) {
		this.typeDeck=typeDeck;
		if (typeDeck == Constants.SPA_TYPE) {
			deck = this.createSpanishDeck();
		}
		this.shuffle();
	}

	private ArrayList<Card> createSpanishDeck() {
		ArrayList<Card> cards = new ArrayList<Card>();
		String[] clubs = { Constants.SPA_CLUBS, Constants.SPA_SWORDS,
				Constants.SPA_CUPS, Constants.SPA_GOLDS };
		for (int i = 0; i < clubs.length; i++) {
			for (int j = 0; j < Constants.SPA_CLUBLENGTH; j++) {
				String kindCard = "";
				switch (j) {
				case 0:
					kindCard = Constants.SPA_ACE;
					break;
				case 9:
					kindCard = Constants.SPA_JACK;
					break;
				case 10:
					kindCard = Constants.SPA_KNIGHT;
					break;
				case 11:
					kindCard = Constants.SPA_KING;
					break;
				}
				cards.add(((i + 1) * (j + 1)) - 1, new Card(((i + 1) * (j + 1)),
						j + 1, clubs[i], kindCard));
			}
		}
		return cards;
	}
	
	public void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(this.deck, new Random(seed));
    }
	
	public ArrayList<Card> getDeal (Integer numCards){
		ArrayList<Card> hand = new ArrayList<Card>();
		for(int i=0;i<numCards;i++){
			hand.add(this.deck.get(i));
			this.deck.remove(i);
		}
		return hand;
	}
	
	public Integer getDeckSize(){
		return this.deck.size();
	}
	
	public Integer getTotalDeckSize(){
		Integer len = 0;
		if(this.typeDeck==Constants.SPA_TYPE){
			len=Constants.SPA_DECKLENGTH;
		}
		return len;
	}

	public Integer getNumPlayers() {
		return numPlayers;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setNumPlayers(Integer numPlayers) {
		this.numPlayers = numPlayers;
	}

	public Integer getTypeDeck() {
		return typeDeck;
	}
}
