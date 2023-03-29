package breakRoom;

import java.util.ArrayList;

public class Main extends Thread {
	
	/**
	 * Used to start the coffee simulation
	 * @throws InterruptedException
	 */
	private void startCoffeSimulation() throws InterruptedException {
		CoffeeQueue queue = new CoffeeQueue();
		CoffeeMaker coffeeMachine = new CoffeeMaker(queue);
		ArrayList<Worker> workers = new ArrayList<Worker>();
		final int numberOfWorkers = 10;
		
		System.out.println("======Started Simulation=====");
		
		//start all workers
		for (int i = 0; i < numberOfWorkers; i++) {
			workers.add(new Worker(("worker" + i), queue));
			workers.get(i).start();
		}
		
		// wait for 20 seconds
		Thread.sleep(20000);
		
		//Stop all workers
		for (Worker worker : workers) {
			worker.cancel();
		}
		
		coffeeMachine.cancel();

		System.out.println("======Closed Simulation=====");
	}

	public static void main(String[] args) {
		Main main = new Main();
		try {
			main.startCoffeSimulation();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
