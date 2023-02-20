package fika;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {
	private String name;
	private int energy;
	private Random r = new Random();
	private int T;
	private Timer timer = new Timer();
	
	private TimerTask task = new TimerTask() {
		public void run() {
			energy--;
			System.out.println(name + ": " + energy);
		}
	};
	
	
	public Worker(String name) {
		this.name = name;
		this.energy = r.nextInt(30, 90);
		this.T = r.nextInt(500, 1500);
		
		timer.scheduleAtFixedRate(task, T, T);
		
		
	}
	
	public Worker(String name, int energy) {
		this.name = name;
		this.energy = energy;
		this.T = r.nextInt(500, 1500);
		
		timer.scheduleAtFixedRate(task, T, T);
	}
}
