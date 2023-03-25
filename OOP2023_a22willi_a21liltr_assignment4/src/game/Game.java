package game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import blocks.Poly;

public class Game extends Thread {
	private Board board;
	
	//gamespeed variables.
	private int tickSpeed;
	private final int maxTickSpeed = 250;
	private final int minTickSpeed = 50;

	BlockFactory factory;

	private Poly fallingPoly;

	/**
	 * Initiate game.
	 * @param The board on which the game shall be played out.
	 */
	public Game(Board board) {
		tickSpeed = maxTickSpeed;
		this.board = board;
		factory = new BlockFactory();
		factory.setSpawn(board.getPrefferedSpawn());
	}
	
	/**
	 * Run the game thread.
	 */
	public void run() {
		while (!(board.hasLost())) {
			calculateTickSpeed();
			
			//controll game speed
			try {
				Thread.sleep(tickSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//run game
			board.detectFullRow();
			board.repaint();
			
			// if there is a falling Poly move it one step down every iteration
			// otherwise spawn a new Poly.
			if (board.isFalling()) {
				board.fall(fallingPoly);
			} else {
				board.givePoints(1);
				fallingPoly = factory.generateRandomPoly();
				board.addPoly(fallingPoly);
			}
			
			//after all movement is done, redraw all graphics.
			board.repaint();
		}
		//if the user has lost, display the number of points collected by the user and exit the game.
		System.out.println("You lost");
		JOptionPane.showMessageDialog(null, "You scored: " + board.getScore() + " points");
		System.exit(0);
	}
	
	/**
	 * Calculate the current speed at which the game should run.
	 */
	private void calculateTickSpeed() {
		if (tickSpeed >= minTickSpeed) {
			tickSpeed = maxTickSpeed - (board.getScore() / 2);
		} 
	}
}
