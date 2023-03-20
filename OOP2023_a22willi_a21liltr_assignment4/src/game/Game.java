package game;

import java.util.Timer;
import java.util.TimerTask;

import blocks.Poly;

public class Game {
	private Board board;
	private Timer timer;
	
	BlockFactory factory;
	
	private Poly poly;
	
	
	public Game(Board board) {
		timer = new Timer();
		timer.scheduleAtFixedRate(gameLoop, (long) 500, (long) 500);
		this.board = board;
		factory = new BlockFactory();
		factory.setSpawn(board.getPrefferedSpawn());
		
		poly = factory.generatePoly();
		board.addPoly(poly);
	}
	
	private TimerTask gameLoop = new TimerTask() {
		@Override
		public void run() {
			board.repaint();
			
			
			poly.move(0, 1);
			
			board.repaint();
		}
	};
}
