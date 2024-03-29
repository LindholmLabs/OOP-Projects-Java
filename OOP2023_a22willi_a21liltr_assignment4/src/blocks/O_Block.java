package blocks;

import java.awt.Color;

public class O_Block extends Poly {
	// holds the information that dictates what shape the object becomes on the
	// board
	public int[][] Shape = { { 1, 1 }, { 1, 1 }, };

	public O_Block(int x, int y) {
		super(x, y);
		super.setColor(Color.yellow);
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
