package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Cell {
	Dimension size;
	Graphics g;
	boolean occupied = true;
	
	public Cell(Graphics g, int x, int y, Dimension size) {
		this.g = g;
		this.size = size;
		g.drawRect(x, y, size.width, size.height);
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
