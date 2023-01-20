package lucky_card;

public class Card {
	private String suit;
	private String rank;
	private int value;
	
	//constructor
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		value = generateCardValue();
	}
	
	//return the cards suit
	String getSuit() {
		return this.suit;
	}
	
	//return the cards rank
	String getRank() {
		return this.rank;
	}
	
	//return the cards value
	int getValue() {
		return this.value;
	}
	
	//calculate each cards value based on suit and rank
	private int generateCardValue() {
		int value = 0; //holds the current cards value
		
		//add "bonus" based on card suit
		switch(suit) {
			case ("Diamonds"):
				value += 4;
				break;
			case ("Clubs"):
				value += 6;
				break;
			case ("Hearts"):
				value += 8;
				break;
			case ("Spades"):
				value += 10;
				break;
		}
		
		//add value based on cards "base value"
		switch(rank) {
			case("Ace"):
				value += 1;
				break;
			case("Jack"):
				value += 11;
				break;
			case("Queen"):
				value += 12;
				break;
			case("King"):
				value += 13;
				break;
			default:
				value+= Integer.parseInt(rank);
			
		}
		return value;
	}
}
