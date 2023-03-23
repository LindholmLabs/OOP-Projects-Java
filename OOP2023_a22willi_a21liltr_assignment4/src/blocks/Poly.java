package blocks;

import java.awt.Color;

public abstract class Poly implements Tileable {
	private int posX, posY;
	private boolean occupied;

	private Color color;

	/**
	 * Instantiates a new poly at given coordinates.
	 * @param x spawn position.
	 * @param y spawn position.
	 */
	public Poly(int x, int y) {
		this.posX = x;
		this.posY = y;
		occupied = false;
	}

	/**
	 * Rotates a given matrix clockwise.
	 * Note: all rows and columns must be of the same cardinality.
	 * @param shape, the matrix to rotate
	 * @return the rotated matrix 
	 */
	protected int[][] rotateClockwise(int[][] shape) {
		int h = shape.length;
		int w = shape[0].length;
		int[][] rotatedShape = new int[w][h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				rotatedShape[x][h - 1 - y] = shape[y][x];
			}
		}
		return rotatedShape;
	}

	/**
	 * Rotates a given matrix counter clockwise.
	 * Note: all rows and columns must be of the same cardinality.
	 * @param shape, the matrix to rotate
	 * @return the rotated matrix 
	 */
	protected int[][] rotateCounterClockwise(int[][] shape) {
		int h = shape.length;
		int w = shape[0].length;
		int[][] rotatedShape = new int[w][h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				rotatedShape[w - 1 - x][y] = shape[y][x];
			}
		}
		return rotatedShape;
	}
	
	/**
	 * Abstract rotate function used by the subclasses of Poly.
	 */
	public abstract void rotateRight();
	
	/**
	 * Abstract rotate function used by the subclasses of Poly.
	 */
	public abstract void rotateLeft();

	/**
	 * Moves the poly in the desired direction.
	 * @param distance to move in x direction.
	 * @param distance to move in y direction.
	 */
	public void move(int x, int y) {
		this.posX += x;
		this.posY += y;
	}

	/**
	 * Get the position of an instantiated poly.
	 * @return
	 */
	public int[] getPos() {
		int[] pos = { posX, posY };
		return pos;
	}

	/**
	 * Get the shape of a Poly, only used by subclasses.
	 * @return
	 */
	public abstract int[][] getShape();

	/**
	 * Set the color of the poly.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get the color of the poly.
	 */
	public Color getColor() {
		// if no other value is set, red will be the default color.
		return color;
	}

	/**
	 * @return is it occupied.
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Set whether or not the Square is occupied.
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
