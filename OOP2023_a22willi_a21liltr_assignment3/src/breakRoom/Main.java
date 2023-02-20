package breakRoom;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		CoffeeQueue queue = new CoffeeQueue();
		
		CoffeeMaker thr4 = new CoffeeMaker(queue);
		
		
		Worker thr1 = new Worker("William", queue);
		Worker thr2 = new Worker("Lili", queue);
		Worker thr3 = new Worker("Sol", queue);
		
		thr1.start();
		thr2.start();
		thr3.start();
		thr4.start();
	}

}
