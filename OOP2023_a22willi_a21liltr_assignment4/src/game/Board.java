package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import blocks.Cell;

public class Board extends JPanel {
	//size of window
	private final Dimension size = new Dimension(595, 797);
	//size of each individual tile
	private final int tileSize = 20;
	
	private Cell[][] grid;
	
	public Board() {
		
		grid = new Cell[Math.abs(size.width / tileSize)][Math.abs(size.height / tileSize)];
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public Dimension getSize() {
		return this.size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillRect(0, 0, size.width, size.height);
		generateGrid(g);
		
	}
	
	
	private void generateGrid(Graphics g) {
		for (int x = 0; x < Math.abs(size.width / tileSize); x++) {
			for (int y = 0; y < Math.abs(size.height / tileSize); y++) {
				grid[y][x] = new Cell(g, y, x, size);
			}
		}
	}
}
