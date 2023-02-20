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
	 * Remove specific worker from coffeeQueue.
	 * @param w the worker to remove.
	 */
	public void deQueue(Worker w) {
		coffeeQueue.remove(w);
	}
	
	
	/**
	 * get the ammount of workers currently queuing for coffee.
	 * @return size of coffeeQueue.
	 */
	public int getSize() {
		return coffeeQueue.size();
	}
	
	/**
	 * Used to determine if worker exists in queue.
	 * @param 	w the worker whose presence we are observing.
	 * @return	true if the specified worker exists, else false.
	 */
	public boolean inQueue(Worker w) {
		if (coffeeQueue.contains(w)) {
			return true;
		}
		return false;
	}
}
