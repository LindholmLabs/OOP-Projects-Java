package game;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
	private Board board;
	private Timer timer;
	
	public GameManager() {
		board = new Board();
		GUI gui = new GUI(board);
		timer = new Timer();
		timer.scheduleAtFixedRate(tick, 1000, 100000);
	}
	
	private TimerTask tick = new TimerTask() {
		public void run() {
			// check if active piece exists
			System.out.println("tick");
			
			board.test();
		}
	};
	
}
