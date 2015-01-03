
package org.pingus.model;

import java.util.ArrayList;

public class Player {

	  private String name;
	  private int ID;
	  private ArrayList<Card> hand;
	  private ArrayList<Card> handAux;

	public Player(String name, int ID){
		  this.name = name;
		  this.ID = ID;
		  this.hand = new ArrayList<Card>();
	  }

  //METHODS
	
	public void receiveCards(ArrayList<Card> hand){
		this.hand = hand;
	}
	
	public void receiveCard(Card card){
		this.hand.add(card);
	}
	
	public void playedCard (Card playedCard){
		
		if (this.hand.contains(playedCard) == true){
			
			this.hand.remove(playedCard);
		}
	}
	
	public void playedCards (ArrayList<Card> playedCards){
		
		while (!playedCards.isEmpty() & !this.hand.isEmpty()){
			
			Card playedCard = playedCards.get(1);
			
			if (this.hand.contains(playedCard) == true){
				
				this.hand.remove(playedCard);
				playedCards.remove(playedCard);
			}
		}
	}
	
	public boolean hasCard(Card card){
		
		return this.hand.contains(card);
	}
	
	
	//GETS
	
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getHandAux() {
		return handAux;
	}

}

