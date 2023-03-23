package game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import blocks.Poly;

public class Game extends Thread {
	private Board board;
	private Timer timer;
	
	private int tickSpeed;
	private final int maxTickSpeed = 250;
	private final int minTickSpeed = 50;

	BlockFactory factory;

	private Poly poly;

	public Game(Board board) {
		
		tickSpeed = maxTickSpeed;
		
		timer = new Timer();
		this.board = board;
		factory = new BlockFactory();
		factory.setSpawn(board.getPrefferedSpawn());
	}
	
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
			
			if (board.isFalling()) {
				board.fall(poly);
			} else {
				board.givePoints(1);
				poly = factory.generateRandomPoly();
				board.addPoly(poly);
			}
			
			board.repaint();
		}
		System.out.println("You lost");
		JOptionPane.showMessageDialog(null, "You scored: " + board.getScore() + " points");
		System.exit(0);
	}
	
	private void calculateTickSpeed() {
		if (tickSpeed >= minTickSpeed) {
			tickSpeed = maxTickSpeed - (board.getScore() / 2);
		} 
	}
}
