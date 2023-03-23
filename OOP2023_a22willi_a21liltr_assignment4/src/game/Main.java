package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	/**
	 * Starts all the necessary components for the game to run.
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();
		GUI gui = new GUI(board);
		Game game = new Game(board);
		game.start();
	}
}
