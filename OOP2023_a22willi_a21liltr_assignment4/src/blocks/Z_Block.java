package blocks;

import java.awt.Color;

public class Z_Block extends Poly {
	// holds the information that dictates what shape the object becomes on the
	// board
	public int[][] Shape = {

			{ 1, 1, 0 }, { 0, 1, 1 }, };

	public Z_Block(int x, int y) {
		super(x, y);
		super.setColor(Color.red);
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
