package assignment2;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hej Världen!");
		
		JFrame frame = new JFrame();
		frame.setSize(400, 200);
		frame.add(new JCheckBox());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
