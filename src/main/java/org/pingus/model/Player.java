package org.pingus.model;

import java.util.ArrayList;

public class Player {

	  private String nombre;
	  private int ID;
	  private ArrayList<Card> hand;
	  private ArrayList<Card> handAux;
	  




	public Player(String nombre, int ID){
		  this.nombre = nombre;
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
		
		ArrayList<Card> playedCards2 = (ArrayList<Card>) playedCards.clone();
		
		while (!playedCards.isEmpty() & !this.hand.isEmpty()){
			
			Card playedCard = playedCards2.get(1);
			
			if (this.hand.contains(playedCard) == true){
				
				this.hand.remove(playedCard);
				playedCards2.remove(playedCard);
			}
			
		}
	}
	
	public boolean hasCard(Card card){
		
		return this.hand.contains(card);
		
	}
	
	
	//GETS
	
	public String getNombre() {
		return nombre;
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
