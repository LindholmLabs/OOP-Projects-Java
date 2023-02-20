package fika;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {
	private static int timeScale = 1; // used to change speed of simulation (default = 1)
	
	private String name;
	private int energy;
	private Random r = new Random();
	private int T; //delay used between every iteration.
	private Timer timer = new Timer();
	private CoffeeQueue coffeeQueue;
	
	private TimerTask task = new TimerTask() {
		public void run() {
			energy--;
			
			if (energy <= 0) {
				System.out.println(name + " is going home with energy level " + energy);
				task.cancel();
			} else if (energy < 30) {
				System.out.println(name + " is taking a break with energy level " + energy);
				Queue();
			} else {
				System.out.println(name + " Is working with energy level " + energy);
			}
		}
	};
	
	private void Queue() {
		coffeeQueue.enQueue(this);
	}
	
	
	public Worker(String name, CoffeeQueue coffeeQueue) {
		this.name = name;
		this.coffeeQueue = coffeeQueue;
		this.energy = r.nextInt(30, 90);
		this.T = r.nextInt(500, 1500);
		
		timer.scheduleAtFixedRate(task, T / timeScale, T / timeScale);
	}
	
	public Worker(String name, CoffeeQueue coffeeQueue, int energy) {
		this.name = name;
		this.coffeeQueue = coffeeQueue;
		this.energy = energy;
		this.T = r.nextInt(500, 1500);
		
		timer.scheduleAtFixedRate(task, T / timeScale, T / timeScale);
	}
}
