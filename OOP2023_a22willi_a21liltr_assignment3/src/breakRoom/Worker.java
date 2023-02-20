package breakRoom;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import coffee.Coffee;

public class Worker extends Thread {
	private static int timeScale = 1; // used to change speed of simulation (default = 1)
	
	private String name;
	private int energy;
	private Boolean onBreak;
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
			
			//if the worker has replenished energy, the worker should no longer be on break.
			if (energy >= 100 && onBreak == true) {
				System.out.println(name + " goes back to work with energy level " + energy);
				onBreak = false;
			}
			
			
			/*
			 * The worker goes home if energy is at 0 or below.
			 * The worker goes to get coffee if its energy level is below 30.
			 * Otherwise keep working.
			 */
			if (energy <= 0) {
				System.out.println(name + " is going home with energy level " + energy);
				task.cancel();
			} else if (energy < 30 || onBreak == true) {
				onBreak = true;
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
		this.onBreak = false;
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
	 * NOTE: only adds the same worker once.
	 */
	private void Queue() {
		if (!(coffeeQueue.inQueue(this))) {
			coffeeQueue.enQueue(this);
		}
	}
	
	
	/**
	 * Remove worker from queue.
	 */
	private void deQueue() {
		if (coffeeQueue.inQueue(this)) {
			coffeeQueue.deQueue(this);
		}
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
	
	
	/**
	 * Get the ammount of energy the worker currently has.
	 * @return 	energy as int.
	 */
	public int getEnergy() {
		return this.energy;
	}
	
	
	/**
	 * Override of equals function.
	 * Only compare the names of the workers to determine if they are queuing.
	 * This allows comparisons even if worker energy changes.
	 * 
	 * NOTE: since it only compares names to know if worker is in coffeeQueue,
	 * 		 no two workers can have the same name. 
	 */
	@Override
	public boolean equals(Object object) {
		boolean isEqual = false;

        if (object != null && object instanceof Worker)
        {
            isEqual = this.name == ((Worker) object).name;
        }

        return isEqual;
	}
}
