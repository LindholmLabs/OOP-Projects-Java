package blocks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Tile implements Tileable {
	Color color;
	boolean occupied;

	public Tile(boolean occupied) {
		this.occupied = occupied;
	}

	public Tile(Color color) {
		this.color = color;
		this.occupied = true;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isOccupied() {
		return occupied;
	}

}
