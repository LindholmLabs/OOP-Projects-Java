package breakRoom;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CoffeeQueue {
	private ConcurrentLinkedQueue<Worker> coffeeQueue;
	
	/**
	 * Constructor,
	 * creates a new coffeeQueue where workers
	 * can queue to get their fill of coffee.
	 */
	public CoffeeQueue() {
		coffeeQueue = new ConcurrentLinkedQueue<Worker>();
	}
	
	
	/**
	 * Add worker to queue.
	 * @param w	The worker to add.
	 */
	public void enQueue(Worker w) {
		coffeeQueue.add(w);
	}
	
	
	/**
	 * Removes worker from the queue.
	 * @return returns the removed worker.
	 */
	public Worker deQueue() {
		return coffeeQueue.remove();
	}
	
	/**
	 * get the ammount of workers currently queuing for coffee.
	 * @return size of coffeeQueue.
	 */
	public int getSize() {
		return coffeeQueue.size();
	}
}
