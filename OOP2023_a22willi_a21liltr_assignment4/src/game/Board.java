package game;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import blocks.Tile;
import blocks.Tileable;
import blocks.Poly;

public class Board extends JPanel {
	//size of window
	private final Dimension size = new Dimension(595, 797);
	//size of each individual tile
	private final int tileSize = 20;
	
	private Poly[][] grid;
	
	private ArrayList<Poly> polys = new ArrayList<Poly>();

	
	private Poly fallingPoly;
	private boolean polyFalling;
	
	public Board() {
		grid = new Poly[Math.abs(size.width / tileSize)][Math.abs(size.height / tileSize)];
		setBorder(BorderFactory.createLineBorder(Color.black));
		polyFalling = false;
	}
	
	public Dimension getSize() {
		return this.size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		draw(g);
	}
	
	
	public void addPoly(Poly poly) {
		polys.add(poly);
	}
	
	public int[] getPrefferedSpawn() {
		int[] spawn = {grid.length / 2, 2};
		return spawn;
	}
	
	/*
	public void drawPoly(Poly poly) {
		for (int x = 0; x < poly.getShape().length; x++) {
			for (int y = 0; y < poly.getShape()[0].length; y++) {
				addTile(new Tile(grid.length / 2, 2, Color.red), x, y);
			}
		}
	}*/
	
	/*
	public void drawPoly(Tileable poly) {
		fallingPoly = (Poly) poly;
		int[][] shape = poly.getShape();
		for (int x = 0; x < shape.length; x++) {
			for (int y = 0; y < shape[0].length; y++) {
				Tile tile = new Tile(grid.length / 2, 2, poly.getColor());
				grid[tile.getPos()[0] + y][tile.getPos()[1] + x] = tile;
				addTile(new Tile(grid.length / 2, 2, Color.red), x, y);
			}
		}
	}*/
	
	/*
	private void addTile(Tile tile, int x, int y) {
		grid[tile.getPos()[0] + y][tile.getPos()[1] + x] = tile;
	}*/
	
	/**
	 * Draws the grid (this code needs fixing)
	 * @param g
	 */
	
	/**
	public void draw(Graphics g) {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				
				
				int posX = x*tileSize;
				int posY = y*tileSize;
				
				
				if (grid[x][y] != null) {
					int[][] shape = grid[x][y].getShape();
					for (int i  = 0; i < shape.length; i++) {
						for (int j = 0; j < shape[0].length; j++) {
							if (shape[i][j] == 1) {
								
								System.out.println("drawing tile at position: " + (posX + (i * tileSize))+ " " + (posY + (j * tileSize)));
								g.setColor(grid[x][y].getColor());
								g.fillRect(posX + (i * tileSize), posY + (j * tileSize), tileSize, tileSize);
							}
						}
					}
				}
				else {
					g.setColor(Color.gray);
					//g.fillRect(posX, posY, tileSize, tileSize);
				}
			}
		}
	}**/
	
	private void clearBoard(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, size.width, size.height);
	}
	
	public void draw(Graphics g) {
		clearBoard(g);
		for (Poly poly : polys) {
			g.setColor(poly.getColor());
			int[][] shape = poly.getShape();
			
			for (int i  = 0; i < shape.length; i++) {
				for (int j = 0; j < shape[0].length; j++) {
					if (shape[i][j] == 1) {
						System.out.println("Drawing poly at" + poly.getPos()[0]+ " " + (i*tileSize) + " : " + poly.getPos()[1]+(j*tileSize));
						g.fillRect(poly.getPos()[0] * tileSize + (i * tileSize), poly.getPos()[1] * tileSize + (j * tileSize), tileSize, tileSize);
					}
				}	
			}
		}
	}
	
	/**
	private void generateGrid(Graphics g) {
		for (int x = 0; x < Math.abs(size.width / tileSize); x++) {
			for (int y = 0; y < Math.abs(size.height / tileSize); y++) {
				grid[y][x] = new Cell(g, y, x, size);
			}
		}
	}**/
}
