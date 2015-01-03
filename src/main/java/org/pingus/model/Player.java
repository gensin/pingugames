package org.pingus.model;

import java.util.ArrayList;

public class Player {

	private String name;
	private String id;
	private ArrayList<Card> hand;

	public Player(String name, String id) {
		this.name = name;
		this.id = id;
		this.hand = new ArrayList<Card>();
	}

	// METHODS

	public void receiveCards(ArrayList<Card> hand) {
		this.hand.addAll(hand);
	}

	public void receiveCard(Card card) {
		this.hand.add(card);
	}

	public void playedCard(int playedCardID) {

		for(int i = 0; i< this.hand.size(); i++){
			if (this.hand.get(i).getCardId() == playedCardID) {
				
				this.hand.remove(i);
				
			}
		}
	}
    public void playedCard(Card card) {

        for(int i = 0; i< this.hand.size(); i++){
            if (this.hand.get(i).getCardId() == card.getCardId()) {

                this.hand.remove(i);

            }
        }
    }


    public void playedCards(int[] playedCardsID) {

		for (int i = 0; i<playedCardsID.length; i++){
			int j = 0;
			
			while (!this.hand.isEmpty() & j < this.hand.size()) {
				
				if (this.hand.get(j).getCardId() == playedCardsID[i]) {
					this.hand.remove(j);
				}
				j++;
			}
		}
	}


	public boolean hasCard(int cardID) {

		for (int i = 0;i<this.hand.size();i++){
			
			int IDAux = this.hand.get(i).getCardId();
			
			if (IDAux == cardID){
				return true;
			}	
		}
		return false;
	}
	
	public boolean hasCards(int[] cardIDs) {

		boolean hasCards = true;
		
		for(int j = 0;(j < cardIDs.length) & (hasCards == true);j++){
			for(int i = 0;(i < this.hand.size()) & (hasCards == true);i++)
			{	
				if(cardIDs[j] != this.hand.get(i).getCardId()){
					hasCards = false;
				}
			}
		}
		return hasCards;
	}
	
	public boolean hasCard(Card card) {

		for (int i = 0;i<this.hand.size();i++){
			
			int IDAux = this.hand.get(i).getCardId();
			
			if (IDAux == card.getCardId()){
				return true;
			}	
		}
		return false;
	}

	// GETS

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	
}
