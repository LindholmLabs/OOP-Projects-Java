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
	private final Dimension size = new Dimension(490, 788);

	private final Color backgroundColor = Color.DARK_GRAY;

	// size of each individual tile
	private final int tileSize = 25;

	private int score;

	private Tileable[][] grid;

	private ArrayList<Poly> polys = new ArrayList<Poly>();

	private boolean polyFalling;
	
	
	/**
	 * Constructs board.
	 * Calculates the size of the grid based of the size of the window.
	 */
	public Board() {
		grid = new Tileable[Math.abs(size.width / tileSize)][Math.abs(size.height / tileSize)];
		setBorder(BorderFactory.createLineBorder(Color.black));
		polyFalling = false;
		score = 0;
		fillGrid();
	}

	/**
	 * Fills the grid with unoccupied Tiles.
	 */
	public void fillGrid() {
		for (int x = 0; x < grid[0].length; x++) {
			for (int y = 0; y < grid.length; y++) {
				grid[y][x] = new Tile(false);
			}
		}
	}

	/**
	 * Detects full rows. Then calls on ClearRow and fall which together 
	 * makes sure full rows are handled correctly.
	 */
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
	
	/**
	 * Getter for score.
	 * @return current score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets all Tiles on a given row to be unoccupied.
	 * @param rowNumber
	 */
	private void clearRow(int rowNumber) {
		for (int col = 0; col < grid.length; col++) {
			grid[col][rowNumber].setOccupied(false);
			grid[col][rowNumber].setColor(backgroundColor);
		}
	}

	/**
	 * increase score by given amount.
	 * @param amount
	 */
	public void givePoints(int amount) {
		score += amount;
	}

	/**
	 * Returns the size of the window.
	 * @return size.
	 */
	public Dimension getSize() {
		return this.size;
	}

	/**
	 * Used for the graphical components of the game.
	 * Draws all components of the board.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		draw(g);
	}
	
	/**
	 * Move a Poly in given direction
	 * @param poly the poly to move
	 * @param x movement in X direction.
	 * @param y movement in Y direction.
	 * @return return whether movement was carried through or not.
	 */
	public boolean move(Poly poly, int x, int y) {
		if (isLegal(poly, x, y)) {
			poly.move(x, y);
			return true;
		}
		return false;
	}

	/**
	 * Checks if a movement, or current position of poly is allowed.
	 * @param poly the poly to check.
	 * @param x offset in X direction.
	 * @param y offset in Y direction.
	 * @return boolean movement legal or not.
	 */
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

	/**
	 * Returns the currently falling poly, if any.
	 * @return falling poly.
	 */
	public Poly getFallingPoly() {
		if (!(polys.isEmpty())) {
			return polys.get(polys.size() - 1);
		}
		return null;
	}

	/**
	 * Converts a poly in to Tiles at its current position.
	 * After conversion the Poly is no longer controllable.
	 * @param poly the poly to convert.
	 */
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

	/**
	 * Is there a Poly falling currently.
	 * @return is there a falling poly.
	 */
	public boolean isFalling() {
		return polyFalling;
	}

	/**
	 * Moves a given Poly one step down, for as long as possible.
	 * When movement is no longer possible, freeze it into position.
	 * @param poly to move.
	 */
	public void fall(Poly poly) {
		polyFalling = true;
		if (move(poly, 0, 1)) {
			return;
		} else {
			freeze(poly);
		}
	}
	
	/**
	 * Move given row down one number in grid.
	 * @param row the row number to move downwards.
	 */
	public void fall(int row) {
		for (int j = row; j > 0; j--) {
			for (int i = grid.length - 1; i >= 0; i--) {
				grid[i][j] = grid[i][j - 1];
			}
		}
	}

	/**
	 * Moves a Poly to its lowest possible position and freezes it.
	 * @param poly
	 */
	public void instaFall(Poly poly) {
		polyFalling = true;
		boolean result = true;
		do {
			result = move(poly, 0, 1);
		} while (result);
		freeze(poly);
	}
	
	/**
	 * Has the user lost.
	 * @return whether the user has lost or not.
	 */
	public boolean hasLost() {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][0].isOccupied()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a new poly to the board.
	 * @param poly
	 */
	public void addPoly(Poly poly) {
		polys.add(poly);
		polyFalling = true;
	}

	/**
	 * Get the spawnpoint for new Polys.
	 * @return coordinates of spawnpoint.
	 */
	public int[] getPrefferedSpawn() {
		int[] spawn = { grid.length / 2, 0 };
		return spawn;
	}

	/**
	 * Turns the entire board into the desired background color.
	 * @param g Java graphics.
	 */
	private void clearBoard(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, size.width, size.height);
	}

	/**
	 * Draw all elements of the board that are to be displayed.
	 * @param g Java graphics.
	 */
	public void draw(Graphics g) {
		clearBoard(g);
		
		// generate grid lines
		drawGrid(g);
		
		// draw guidelines
		drawPolyGuide(g);
		
		// draw polys
		drawPoly(g);
		
		// draw tiles
		drawTiles(g);
		
		// show score
		drawScore(g);
	}

	/**
	 * Draws the falling poly(s).
	 * @param g
	 */
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

	/**
	 * Draws all the Tiles.
	 * @param g
	 */
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

	/**
	 * Draws the guidelines that show where the falling Poly is going to land.
	 * @param g
	 */
	private void drawPolyGuide(Graphics g) {
		g.setColor(Color.white);
		if (getFallingPoly() != null) {
			Poly poly = getFallingPoly();
			int[][] shape = poly.getShape();
			g.drawLine(poly.getPos()[0] * tileSize + (shape[0].length * tileSize), 0,
					poly.getPos()[0] * tileSize + (shape[0].length * tileSize), size.height);
			g.drawLine(poly.getPos()[0] * tileSize, 0, poly.getPos()[0] * tileSize, size.height);
		}
	}

	/**
	 * Displays the score at the top left of the window.
	 * @param g
	 */
	private void drawScore(Graphics g) {
		// show score
		g.setColor(Color.white);
		String scoreText = "Score: " + String.valueOf(score);
		g.setFont(new Font("Helvetica", Font.PLAIN, tileSize));
		g.drawString(scoreText, tileSize, g.getFontMetrics().getHeight());
	}

	/**
	 * Draws the gridlines representing the board.
	 * @param g
	 */
	private void drawGrid(Graphics g) {
		g.setColor(Color.gray);

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				//draw x lines
				g.drawLine(x * tileSize, 0, x * tileSize, grid[0].length * tileSize);
				//draw y lines
				g.drawLine(0, y * tileSize, grid[1].length * tileSize, y * tileSize);
			}
		}
	}
}
