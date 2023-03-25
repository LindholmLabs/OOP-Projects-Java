package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GUI extends JFrame implements KeyListener {
	private Board board;
	private JFrame frame;

	/**
	 * Instantiates the GUI and creates a JFrame / window on which the game is played.
	 * @param board
	 */
	public GUI(Board board) {
		this.board = board;
		frame = new JFrame("Tetris");
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(board.getSize());
		frame.setResizable(false);

		frame.add(board);

		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Handle all of the user input.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// instantly move the falling Poly to the lowest possible position.
		// Spacebar.
		case 32:
			if (board.getFallingPoly() != null) {
				board.instaFall(board.getFallingPoly());
			}
			break;
		// move the currently falling poly to the left.
		// left arrow key.
		case 37:
			movePoly(-1, 0);
			break;
		// move the currently falling poly to the right.
		// right arrow key.
		case 39:
			movePoly(1, 0);
			break;
		// rotate the falling poly.
		// up arrow key.
		case 38:
			if (board.getFallingPoly() != null) {
				board.getFallingPoly().rotateRight();

				// if the rotate is not legal, undo rotation.
				if (!(board.isLegal(board.getFallingPoly(), 0, 0))) {
					board.getFallingPoly().rotateLeft();
				}
			}
			break;
		// slow fall.
		// down arrow key.
		case 40:
			movePoly(0, 1);
			break;
		}

		board.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * checks if there is a falling poly,
	 * then moves it to the desired position.
	 * @param x
	 * @param y
	 */
	private void movePoly(int x, int y) {
		if (board.getFallingPoly() != null) {
			board.move(board.getFallingPoly(), x, y);
		}
	}
}