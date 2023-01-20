package lucky_card;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private Deck deck;
	private boolean playing;
	private Scanner inputHandler;
	
	
	//constructor
	public Game() {
		deck = new Deck();
		playing = true;
		inputHandler = new Scanner(System.in);
	}

	//start the game
	public void play() {
		//greet user
		System.out.println("Welcome to Lucky Card game by William Lindholm\n");
		
		//main game loop
		while (playing) {
			
			//display starting round information
			System.out.println("\n------- Playing a game round");
			
			//draw three cards from top of deck
			ArrayList<Card> drawnCards = new ArrayList<Card>(); 
			for (int i = 0; i < 3; i++) {
				drawnCards.add(deck.pop());
			}
			
			//re-add the drawn cards
			for (Card card : drawnCards) {
				deck.addToBottom(card);
			}
			
			//display cards and determine if user won.
			displayCards(drawnCards);
			
			//check if user has won and display result
			displayResult(isWin(drawnCards));
			
			//ask if user wants to play again
			System.out.print("\n=========> Press ENTER to play again or \"q\" to quit: ");
			
			//handle the user input, if user presses q exit game
			handleInput();
		}
	}
	
	//Handles the user input
	private void handleInput() {
		//handle input
		String userinput = inputHandler.nextLine();
		System.out.println(userinput);
		if (userinput.equals("q")) {
			System.out.println("\nThank you for playing and welcome back!");
			playing = false;
		}
	}
	
	//display cards and determine if user won
	private void displayCards(ArrayList<Card> cards) {
		//display cards 
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i); //get current card
			
			//print out cards suit, rank and value 
			System.out.println("Card " + i + ": " + card.getSuit() 
			+ " -> " + card.getRank() + " Value = " + card.getValue());
		}
	}
	
	private void displayResult(boolean hasWon) {
		//display win
		if (hasWon) {
			System.out.println("You win!");
		} else {
			System.out.println("You lose!");
		}
	}
	
	private boolean isWin(ArrayList<Card> cards) {
		//grab values from drawnCards
		int[] cardValues = new int[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			cardValues[i] = cards.get(i).getValue();
		}
		
		//compare values from drawnCards
		if ((cardValues[cardValues.length-1] > cardValues[0] 
			&& cardValues[cardValues.length-1] < cardValues[1])
			|| (cardValues[cardValues.length-1] < cardValues[0] 
			&& cardValues[cardValues.length-1] > cardValues[1])){
			return true;
		}
		return false;
	}
}
