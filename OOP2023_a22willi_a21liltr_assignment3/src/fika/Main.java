package fika;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Worker thr1 = new Worker("William");
		Worker thr2 = new Worker("Lili");
		Worker thr3 = new Worker("Sol");
		CoffeeMaker thr4 = new CoffeeMaker();
		thr1.start();
		thr2.start();
		thr3.start();
		thr4.start();
	}

}
