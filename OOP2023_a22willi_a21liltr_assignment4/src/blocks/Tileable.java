package blocks;

import java.awt.Color;

public interface Tileable {
	public boolean isOccupied();
	
	void setOccupied(boolean state);

	public Color getColor();
	
	public void setColor(Color color);

	
}
