package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Tile {
	int posX;
	int posY;
	Color color;
	
	
	
	public Tile(int x, int y, Color color) {
		this.color = color;
		this.posX = x;
		this.posY = y;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int[] getPos() {
		int[] positions = {posX, posY};
		return positions;
	}
	
	
}
