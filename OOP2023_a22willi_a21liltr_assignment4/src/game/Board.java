package game;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import blocks.Tile;
import blocks.Tileable;
import blocks.Poly;

public class Board extends JPanel {
	// size of window
	private final Dimension size = new Dimension(495, 790);

	private final Color backgroundColor = Color.DARK_GRAY;

	// size of each individual tile
	private final int tileSize = 25;

	private int score;

	private Tileable[][] grid;

	private ArrayList<Poly> polys = new ArrayList<Poly>();

	private boolean polyFalling;

	public Board() {
		grid = new Tileable[Math.abs(size.width / tileSize)][Math.abs(size.height / tileSize)];
		setBorder(BorderFactory.createLineBorder(Color.black));
		polyFalling = false;
		score = 0;
		fillGrid();
	}

	public void fillGrid() {
		for (int x = 0; x < grid[0].length; x++) {
			for (int y = 0; y < grid.length; y++) {
				grid[y][x] = new Tile(false);
			}
		}
	}

	public void detectFullRow() {
		for (int row = 0; row < grid[0].length; row++) {
			for (int col = 0; col < grid.length; col++) {
				if (!(grid[col][row].isOccupied())) {
					break;
				}
				if (col == grid.length - 1) {
					score += 10;
					clearRow(row);
					fall(row);
				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	private void clearRow(int rowNumber) {
		for (int col = 0; col < grid.length; col++) {
			grid[col][rowNumber].setOccupied(false);
			grid[col][rowNumber].setColor(backgroundColor);
		}
	}

	public void givePoints(int amount) {
		score += amount;
	}

	public Dimension getSize() {
		return this.size;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		draw(g);
	}

	public boolean move(Poly poly, int x, int y) {
		if (isLegal(poly, x, y)) {
			poly.move(x, y);
			return true;
		}
		return false;
	}

	public boolean isLegal(Poly poly, int x, int y) {
		int[][] shape = poly.getShape();
		int[] position = poly.getPos();

		// iterate over every "tile" in poly shape
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {
				int realX = position[0] + j;
				int realY = position[1] + i;
				if (shape[i][j] == 1) {
					
					// check X direction
					if (realX + x >= grid.length || realX + x < 0) {
						return false;
					}
					if (grid[realX + x][realY].isOccupied()) {
						return false;
					}


					// check y direction
					if (realY + y >= grid[0].length - 1) {
						return false;
					}
					if (grid[realX][realY + y].isOccupied()) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public Poly getFallingPoly() {
		if (!(polys.isEmpty())) {
			return polys.get(polys.size() - 1);
		}
		return null;
	}

	private void freeze(Poly poly) {
		int[][] shape = poly.getShape();
		int[] position = poly.getPos();
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {
				if (shape[i][j] == 1) {
					grid[position[0] + j][position[1] + i] = new Tile(poly.getColor());
				}
			}
		}
		polyFalling = false;
		polys.remove(poly);
	}

	public boolean isFalling() {
		return polyFalling;
	}

	public void fall(Poly poly) {
		polyFalling = true;
		if (move(poly, 0, 1)) {
			return;
		} else {
			freeze(poly);
		}
	}

	public void fall(int row) {
		for (int j = row; j > 0; j--) {
			for (int i = grid.length - 1; i >= 0; i--) {
				grid[i][j] = grid[i][j - 1];
			}
		}
	}

	public void instaFall(Poly poly) {
		polyFalling = true;
		boolean result = true;
		do {
			result = move(poly, 0, 1);
		} while (result);
		freeze(poly);
	}

	public boolean hasLost() {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][0].isOccupied()) {
				return true;
			}
		}
		return false;
	}

	public void addPoly(Poly poly) {
		polys.add(poly);
		polyFalling = true;
	}

	public int[] getPrefferedSpawn() {
		int[] spawn = { grid.length / 2, 0 };
		return spawn;
	}

	private void clearBoard(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, size.width, size.height);
	}

	public void draw(Graphics g) {
		clearBoard(g);

		// show score
		drawScore(g);

		// draw polys
		drawPoly(g);

		// draw guidelines
		drawPolyGuide(g);

		// draw tiles
		drawTiles(g);
	}

	private void drawPoly(Graphics g) {
		// draw polys
		if (polys.size() > 0) {
			for (Poly poly : polys) {
				g.setColor(poly.getColor());
				int[][] shape = poly.getShape();

				for (int i = 0; i < shape.length; i++) {
					for (int j = 0; j < shape[0].length; j++) {
						if (shape[i][j] == 1) {
							g.fillRect(poly.getPos()[0] * tileSize + (j * tileSize),
									poly.getPos()[1] * tileSize + (i * tileSize), tileSize, tileSize);

						}
					}
				}
			}
		}
	}

	private void drawTiles(Graphics g) {
		for (int x = 0; x < grid[0].length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if (grid[y][x].isOccupied()) {
					g.setColor(grid[y][x].getColor());
					g.fillRect(y * tileSize, x * tileSize, tileSize, tileSize);
				}
			}
		}
	}

	private void drawPolyGuide(Graphics g) {
		g.setColor(Color.white);
		if (getFallingPoly() != null) {
			Poly poly = getFallingPoly();
			int[][] shape = poly.getShape();
			g.drawLine(poly.getPos()[0] * tileSize + (shape[0].length * tileSize) - 1,
					poly.getPos()[1] * tileSize + shape.length * tileSize,
					poly.getPos()[0] * tileSize + (shape[0].length * tileSize) - 1, size.height - tileSize * 2);
			g.drawLine(poly.getPos()[0] * tileSize, poly.getPos()[1] * tileSize + shape.length * tileSize,
					poly.getPos()[0] * tileSize, size.height - tileSize * 2);
		}
	}

	private void drawScore(Graphics g) {
		// show score
		g.setColor(Color.white);
		String scoreText = "Score: " + String.valueOf(score);
		g.setFont(new Font("Helvetica", Font.PLAIN, tileSize));
		g.drawString(scoreText, tileSize, g.getFontMetrics().getHeight());
	}
}
