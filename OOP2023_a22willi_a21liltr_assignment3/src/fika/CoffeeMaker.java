package fika;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CoffeeMaker extends Thread {
	private static int timeScale = 10; // used to change speed of simulation (default = 1)
	private Timer timer = new Timer();
	private int T = 2000; //the default time it takes the coffeemaker to create one coffee.
	
	private ConcurrentLinkedQueue<Coffee> coffeBuffer = new ConcurrentLinkedQueue<Coffee>();
	
	private TimerTask task = new TimerTask() {
		public void run() {
			//only create a new coffee if there are less than 20 coffee in the machine.
			if ( coffeBuffer.size() < 20) {
				Coffee coffee = generateRandomCoffee();
				coffeBuffer.add(coffee);
			}
			
			//announce how many coffees are available.
			System.out.println("Coffee Machine has " + coffeBuffer.size() + " drinks in reserve.");
		}
	};
	
	public CoffeeMaker() {
		timer.scheduleAtFixedRate(task, T / timeScale, T / timeScale);
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
