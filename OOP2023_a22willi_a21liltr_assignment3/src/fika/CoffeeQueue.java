package fika;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CoffeeQueue {
	private ConcurrentLinkedQueue<Worker> coffeeQueue;
	
	public CoffeeQueue() {
		coffeeQueue = new ConcurrentLinkedQueue<Worker>();
	}
	
	public void enQueue(Worker w) {
		coffeeQueue.add(w);
	}
	
	public void deQueue() {
		coffeeQueue.remove();
	}
}
