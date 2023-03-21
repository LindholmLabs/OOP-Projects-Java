package blocks;

import java.awt.Color;

public abstract class Poly implements Tileable {
	private int posX, posY;
	private boolean occupied;

	private Color color;

	public Poly(int x, int y) {
		this.posX = x;
		this.posY = y;
		occupied = false;
	}

	public int[][] rotateClockwise(int[][] shape) {
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

	public int[][] rotateCounterClockwise(int[][] shape) {
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

	public abstract void rotateRight();
	
	public abstract void rotateLeft();

	public void move(int x, int y) {
		this.posX += x;
		this.posY += y;
	}

	public int[] getPos() {
		int[] pos = { posX, posY };
		return pos;
	}

	public int[][] getShape() {
		return null;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		// if no other value is set, red will be the default color.
		return color;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
