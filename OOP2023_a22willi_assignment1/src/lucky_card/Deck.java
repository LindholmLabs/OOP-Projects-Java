package lucky_card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	//arrayList where all cards are stored
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	//used to build cards
	private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	//constructor
	public Deck() {
		//generate deck
		for (String suit : suits) {
			for (String rank : ranks) {
				//create new card object for every suit and rank
				deck.add(new Card(suit, rank));
			}
		}
		shuffle();
	}
	
	//shuffle the deck
	void shuffle() {
		//shuffle deck
		Collections.shuffle(deck);
	}
	
	//add card to the end of the deck.
	void addToBottom(Card card) {
		deck.add(0, card);
	}
	
	//retrieve a card from deck at specified position (cardNumber)
	Card getCard(int cardNumber) {
		return deck.get(cardNumber);
	}
	
	//return and remove the first card in deck
	Card pop() {
		return deck.remove(this.getLength()-1);
	}
	
	//return the amount of cards in the deck.
	int getLength() {
		return deck.size();
	}
}
