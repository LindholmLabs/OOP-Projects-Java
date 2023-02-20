package breakRoom;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		CoffeeQueue queue = new CoffeeQueue();
		CoffeeMaker coffeeMachine = new CoffeeMaker(queue);
		
		Worker worker1 = new Worker("William", queue);
		Worker worker2 = new Worker("Lili", queue);
		Worker worker3 = new Worker("Sol", queue);
		
		worker1.start();
		worker2.start();
		worker3.start();
		coffeeMachine.start();
	}

}
