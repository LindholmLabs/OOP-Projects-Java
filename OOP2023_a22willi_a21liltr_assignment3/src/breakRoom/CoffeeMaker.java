package breakRoom;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import coffee.BlackCoffee;
import coffee.Cappuccino;
import coffee.Coffee;
import coffee.Latte;

public class CoffeeMaker extends Thread {
	private static final int timeScale = 1; // used to change speed of simulation (default = 1).
	private int timeToCreateCoffee = 2000; //the default time it takes the coffeemaker to create one coffee.
	private int timeToServeCoffee = 1000; //the default time it takes the coffeemaker to serve one cup of coffee.
	private int coffeBufferSize = 20; //the max amount of coffees the coffeemaker can store.
	private int maxWorkerEnergy = 100; //do not give worker more coffee if it exceeds this number. 
	
	private Timer timer;
	private Random random;
	
	private CoffeeQueue coffeeQueue; //queue for workers.
	private ConcurrentLinkedQueue<Coffee> coffeeBuffer; //queue for coffee.
	
	
	/**
	 * TimerTask used for creating coffee.
	 * Each cup is added to the coffeeBuffer where a worker then can retrieve it.
	 */
	private TimerTask createCoffee = new TimerTask() {
		public void run() {
			//only create a new coffee if there are less than x coffees in the machine.
			if ( coffeeBuffer.size() < coffeBufferSize) {
				Coffee coffee = generateRandomCoffee();
				coffeeBuffer.add(coffee);
			}
			
			//announce how many coffees are available.
			System.out.println("Coffee Machine has " + coffeeBuffer.size() + " drinks in reserve.");
		}
	};
	
	
	/**
	 * Timertask serveCoffee.
	 * Serves the first worker in the queue with the next cup of coffee from the coffeeBuffer;
	 * a concurrentLinkedQueue containing all cups of coffee the machine has created.
	 */
	private TimerTask serveCoffee = new TimerTask() {
		public void run() {
			if (coffeeQueue.getSize() != 0 && coffeeBuffer.size() != 0) {
				Worker worker = coffeeQueue.deQueue();
				Coffee coffee = coffeeBuffer.remove();
				
				if (worker.getEnergy() < maxWorkerEnergy ) {
					worker.drink(coffee);
					System.out.println(worker.getWorkerName() + " enjoyed a " + coffee.getType() + " with " + coffee.getEnergy() + " energy");
				}
			}
		}
	};
	
	
	/**
	 * Run thread.
	 * Start serveCoffee and CreateCoffee schedules.
	 */
	public void run() {
		timer.scheduleAtFixedRate(createCoffee, timeToCreateCoffee / timeScale, timeToCreateCoffee / timeScale);
		timer.scheduleAtFixedRate(serveCoffee, timeToServeCoffee / timeScale, timeToServeCoffee / timeScale);
	}
	
	
	/**
	 * Used to cancel the scheduled tasks. 
	 */
	public void cancel() {
		createCoffee.cancel();
		serveCoffee.cancel();
	}
	
	
	/**
	 * Constructor, requires variable coffeeQueue
	 * @param coffeeQueue The queue that workers use to interface with the coffeemaker.
	 */
	public CoffeeMaker(CoffeeQueue coffeeQueue) {
		this.coffeeQueue = coffeeQueue;
		this.coffeeBuffer = new ConcurrentLinkedQueue<Coffee>();
		this.timer = new Timer();
		this.random = new Random();
	}
	
	
	/**
	 * Generate a random coffee. Each coffee gets a random energy value
	 * depending on type.
	 * @return Coffee	Returns a coffee of random type.
	 */
	private Coffee generateRandomCoffee() {
		int coffeType = random.nextInt(1, 4);
		int energy;
		
		switch(coffeType) {
			case 1:
				energy = random.nextInt(25, 35);
				return new BlackCoffee(energy);
			case 2:
				energy = random.nextInt(15, 20);
				return new Latte(energy);
			case 3:
				energy = random.nextInt(20, 30);
				return new Cappuccino(energy);
			default:
				System.out.println("Something went wrong");
				return null;
		}
	}
}
