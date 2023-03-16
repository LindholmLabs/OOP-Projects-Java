package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Cell {
	Dimension size;
	Graphics g;
	int x, y;
	boolean occupied = true;
	
	public Cell(Graphics g, int x, int y, Dimension size) {
		this.g = g;
		this.size = size;
		this.x = x;
		this.y = y;
		g.setColor(Color.black);
		g.drawRect(x, y, size.width, size.height);
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
		
	}
	
	public void occupyCell(Color color) {
		g.setColor(color);
		g.fillRect(x, y, size.width, size.height);
		occupied = true;
		
		System.out.println("occ");
	}
	
	public void unoccupyCell() {
		g.setColor(Color.gray);
		occupied = false;
	}
}
