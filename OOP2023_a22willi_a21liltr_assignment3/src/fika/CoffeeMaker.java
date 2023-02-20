package fika;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CoffeeMaker extends Thread {
	private static int timeScale = 1; // used to change speed of simulation (default = 1)
	private Timer timer = new Timer();
	private int timeToCreateCoffee = 2000; //the default time it takes the coffeemaker to create one coffee.
	private int timeToServeCoffee = 1000; //the default time it takes the coffeemaker to serve one cup of coffee.
	
	private CoffeeQueue coffeeQueue;
	private ConcurrentLinkedQueue<Coffee> coffeeBuffer = new ConcurrentLinkedQueue<Coffee>();
	
	private TimerTask createCoffee = new TimerTask() {
		public void run() {
			//only create a new coffee if there are less than 20 coffee in the machine.
			if ( coffeeBuffer.size() < 20) {
				Coffee coffee = generateRandomCoffee();
				coffeeBuffer.add(coffee);
			}
			
			//announce how many coffees are available.
			System.out.println("Coffee Machine has " + coffeeBuffer.size() + " drinks in reserve.");
		}
	};
	
	private TimerTask serveCoffee = new TimerTask() {
		public void run() {
			
		}
	};
	
	public CoffeeMaker(CoffeeQueue coffeeQueue) {
		this.coffeeQueue = coffeeQueue;
		timer.scheduleAtFixedRate(createCoffee, timeToCreateCoffee / timeScale, timeToCreateCoffee / timeScale);
		timer.scheduleAtFixedRate(serveCoffee, timeToServeCoffee / timeScale, timeToServeCoffee / timeScale);
	}
	
	private Coffee generateRandomCoffee() {
		Random r = new Random();
		int coffeType = r.nextInt(0, 2);
		int energy;
		
		switch(coffeType) {
			case 0:
				energy = r.nextInt(25, 35);
				return new BlackCoffee(energy);
			case 1:
				energy = r.nextInt(15, 20);
				return new BlackCoffee(energy);
			case 2:
				energy = r.nextInt(20, 30);
				return new Cappuccino(energy);
			default:
				System.out.println("Something went wrong");
				return null;
		}
	}
}
