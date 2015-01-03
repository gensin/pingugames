package org.pingus.model;

import org.pingus.utils.Constants;

public class Card {

	private int cardId;
	private int cardNumber;
	private int isJoker;
	private String cardClub;
	private String cardKind;

	public Card(int cardId, int cardNumber, String cardClub, String cardKind) {
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardClub = cardClub;
		this.cardKind = cardKind;
		this.isJoker = Constants.NO_JOKER;
	}

	public Card(int cardId, int cardNumber, String cardClub, String cardKind,
			int isJoker) {
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardClub = cardClub;
		this.cardKind = cardKind;
		this.isJoker = isJoker;
	}

	public int addValuesJoker(int cardNumber, String cardClub, String cardKind) {
		if (this.isJoker == Constants.NO_JOKER) {
			this.isJoker = Constants.JOKER;
		}
		this.cardNumber = cardNumber;
		this.cardClub = cardClub;
		this.cardKind = cardKind;
		return this.cardId;
	}

	public int getCardId() {
		return cardId;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getIsJoker() {
		return isJoker;
	}

	public String getCardClub() {
		return cardClub;
	}

	public String getCardKind() {
		return cardKind;
	}

}
