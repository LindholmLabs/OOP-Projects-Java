package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class GUI {
	Board board;
	
	public GUI(Board board) {
		this.board = board;
		JFrame frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(board.getSize());
		frame.setResizable(false);
		
		frame.add(board);
		
		frame.setVisible(true);
	}
}