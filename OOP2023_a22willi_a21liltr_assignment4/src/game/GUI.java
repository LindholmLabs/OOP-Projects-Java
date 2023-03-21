package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GUI extends JFrame implements KeyListener {
	Board board;
	JFrame frame;

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

	@Override

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// spaceBar
		case 32:
			if (board.getFallingPoly() != null) {
				board.instaFall(board.getFallingPoly());
			}
			break;
		// left
		case 37:
			movePoly(-1, 0);
			break;
		// right
		case 39:
			movePoly(1, 0);
			break;
		// rotate
		case 38:
			if (board.getFallingPoly() != null) {
				board.getFallingPoly().rotate();
			}
			// slow fall
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

	private void movePoly(int x, int y) {
		if (board.getFallingPoly() != null) {
			board.move(board.getFallingPoly(), x, y);
		}
	}
}