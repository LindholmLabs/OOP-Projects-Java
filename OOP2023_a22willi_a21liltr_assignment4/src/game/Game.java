package game;

import java.util.Timer;
import java.util.TimerTask;

import blocks.Poly;

public class Game {
	private Board board;
	private Timer timer;
	private final int tickRate = 200;

	BlockFactory factory;

	private Poly poly;

	public Game(Board board) {
		timer = new Timer();
		timer.scheduleAtFixedRate(gameLoop, tickRate, tickRate);
		this.board = board;
		factory = new BlockFactory();
		factory.setSpawn(board.getPrefferedSpawn());
	}

	private TimerTask gameLoop = new TimerTask() {
		@Override
		public void run() {
			board.detectFullRow();
			if (!(board.hasLost())) {
				board.repaint();
				if (board.isFalling()) {
					board.fall(poly);
				} else {
					board.givePoints(1);
					poly = factory.generateRandomPoly();
					board.addPoly(poly);
				}

				board.repaint();
			} else {
				resetGame();
			}
		}
	};
	
	private void resetGame() {
		System.out.println("Restarting game");
		this.board = new Board();
	}
}
