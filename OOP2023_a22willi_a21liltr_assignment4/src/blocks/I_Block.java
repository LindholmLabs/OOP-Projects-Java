package blocks;

import java.awt.Color;

public class I_Block extends Poly {
	
	//holds the information that dictates what shape the object becomes on the board
	public int[][] Shape = {
				{1, 0},
				{1, 0},
				{1, 0},
				{1, 1},
	};
		
	private Color color;
	
	public I_Block(int x, int y) {
		super(x, y);
		color = Color.green;
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return Shape;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
}
