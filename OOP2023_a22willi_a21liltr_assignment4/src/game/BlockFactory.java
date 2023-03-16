package game;

import java.util.Random;

import blocks.I_Block;
import blocks.J_Block;
import blocks.L_Block;
import blocks.TetrisBlock;

public class BlockFactory {
	private Random random;
	
	public BlockFactory() {
		//code
	}
	
	private TetrisBlock createBlock() {
		int blockType = random.nextInt(1, 3);
		
		switch (blockType) {
		case 1:
			return new I_Block();
		case 2:
			return new J_Block();
		case 3:
			return new L_Block();
		}
		
		return null;
	}
}
