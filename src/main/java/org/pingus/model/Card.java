package org.pingus.model;

public class Card {

	private Integer idCard;
	private Integer numCard;
	private String clubCard;
	private String kindCard;
	
	public Card(Integer idCard,Integer numCard,String clubCard,String kindCard){
		this.idCard=idCard;
		this.numCard=numCard;
		this.clubCard=clubCard;
		this.kindCard=kindCard;
	}

	public Integer getIdCard() {
		return idCard;
	}

	public Integer getNumCard() {
		return numCard;
	}

	public String getClubCard() {
		return clubCard;
	}

	public String getKindCard() {
		return kindCard;
	}
	
}
