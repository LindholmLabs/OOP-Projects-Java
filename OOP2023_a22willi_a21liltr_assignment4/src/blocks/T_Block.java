package blocks;

import java.awt.Color;

public class T_Block extends Poly {
	// holds the information that dictates what shape the object becomes on the
	// board
	public int[][] Shape = { { 0, 1, 0 }, { 1, 1, 1 }, };

	public T_Block(int x, int y) {
		super(x, y);
		super.setColor(Color.magenta);
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return Shape;
	}

	@Override
	public void rotateRight() {
		Shape = super.rotateClockwise(Shape);
	}
	
	@Override
	public void rotateLeft() {
		Shape = super.rotateCounterClockwise(Shape);
	}
}
