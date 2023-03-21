package blocks;

import java.awt.Color;

public class J_Block extends Poly {
	// holds the information that dictates what shape the object becomes on the
	// board
	public int[][] Shape = { { 0, 1 }, { 0, 1 }, { 1, 1 } };

	public J_Block(int x, int y) {
		super(x, y);
		super.setColor(Color.blue);
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return Shape;
	}

	@Override
	public void rotate() {
		Shape = super.rotateRight(Shape);
	}
}
