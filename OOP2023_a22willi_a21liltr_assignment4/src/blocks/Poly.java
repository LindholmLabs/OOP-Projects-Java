package blocks;

import java.awt.Color;

public class Poly {
	private boolean falling;
	
	private int posX, posY; 
	
	
	public Poly(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public void rotateRight() {
		
	}
	
	public void rotateLeft() {
		
	}
	
	public boolean isFalling() {
		return this.falling;
	}
	
	public void setFailling(boolean falling) {
		this.falling = falling;
	}
	
	public void move(int x, int y) {
		this.posX += x;
		this.posY += y;
	}
	
	
	public boolean getFalling() {
		return falling;
	}
	
	public int[] getPos() {
		int[] pos = {posX, posY};
		return pos;
	}
	
	public int[][] getShape() {
		return null;
	}
	
	
	public Color getColor() {
		//if no other value is set, red will be the default color.
		return Color.red;
	}
}
