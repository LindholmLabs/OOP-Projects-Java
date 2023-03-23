package blocks;

import java.awt.Color;

public interface Tileable {
	/**
	 * Is the Tile occupied.
	 * @return occupancy state.
	 */
	public boolean isOccupied();
	
	/**
	 * Set if the Tile is occupied or not.
	 * @param state.
	 */
	void setOccupied(boolean state);

	/**
	 * get the color of the Tile.
	 * @return Color of tile.
	 */
	public Color getColor();
	
	/**
	 * Set the color of the Tile.
	 * @param color.
	 */
	public void setColor(Color color);

	
}
