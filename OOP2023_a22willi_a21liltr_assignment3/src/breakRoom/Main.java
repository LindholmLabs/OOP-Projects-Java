package breakRoom;

public class Main extends Thread{

	public static void main(String[] args) throws InterruptedException {
		CoffeeQueue queue = new CoffeeQueue();
		CoffeeMaker coffeeMachine = new CoffeeMaker(queue);
		
		//declare workers
		Worker worker1 = new Worker("worker1", queue);
		Worker worker2 = new Worker("worker2", queue);
		Worker worker3 = new Worker("worker3", queue);
		Worker worker4 = new Worker("worker4", queue);
		
		//start all threads. (start simulation).
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();
		coffeeMachine.start();
		
		//wait for 20 seconds
		Thread.sleep(20000);
		
		//stop all threads. (exit simulation).
		worker1.cancel();
		worker2.cancel();
		worker3.cancel();
		worker4.cancel();
		coffeeMachine.cancel();
		
		System.out.println("======Closed Simulation=====");
	}

}
