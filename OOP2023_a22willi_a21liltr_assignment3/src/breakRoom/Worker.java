package breakRoom;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cofee.Coffee;

public class Worker extends Thread {
	private static int timeScale = 1; // used to change speed of simulation (default = 1)
	
	private String name;
	private int energy;
	private Random r = new Random();
	private int T; //delay used between every iteration.
	private Timer timer = new Timer();
	private CoffeeQueue coffeeQueue;
	
	/**
	 * Timertask, runs periodically to determine how much energy the worker has
	 * and to determine the workers next move.
	 */
	private TimerTask task = new TimerTask() {
		public void run() {
			energy--;
			
			/*
			 * The worker goes home if energy is at 0 or below.
			 * The worker goes to get coffee if its energy level is below 30.
			 * Otherwise keep working.
			 */
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
	
	
	/**
	 * Run thread. 
	 * Starts scheduled task.
	 */
	public void run() {
		timer.scheduleAtFixedRate(task, T / timeScale, T / timeScale);
	}
	
	
	/**
	 * Constructor
	 * Creates a worker.
	 * @param name			Name of the worker.
	 * @param coffeeQueue	The queue for getting coffee.
	 */
	public Worker(String name, CoffeeQueue coffeeQueue) {
		this.name = name;
		this.coffeeQueue = coffeeQueue;
		this.energy = r.nextInt(30, 90); //set the starting value of energy to a random int.
		this.T = r.nextInt(500, 1500); //set the duration between each iteration of task to a random int.
	}
	
	/**
	 * Constructor
	 * Creates a worker.
	 * @param name			Name of the worker.
	 * @param coffeeQueue	The queue for getting coffee.
	 * @param energy		The ammount of energy the worker has.
	 */
	public Worker(String name, CoffeeQueue coffeeQueue, int energy) {
		this.name = name;
		this.coffeeQueue = coffeeQueue;
		this.energy = energy;
		this.T = r.nextInt(500, 1500);
	}
	
	
	/**
	 * start queuing to get coffee.
	 */
	private void Queue() {
		coffeeQueue.enQueue(this);
	}
	
	
	/**
	 * Allows a worker to drink a cup of coffee
	 * @param coffee the coffee to drink
	 */
	public void drink(Coffee coffee) {
		this.energy += coffee.getEnergy();
	}
	
	/**
	 * Get the workers name
	 * @return returns the workers name.
	 */
	public String getWorkerName() {
		return this.name;
	}
}
