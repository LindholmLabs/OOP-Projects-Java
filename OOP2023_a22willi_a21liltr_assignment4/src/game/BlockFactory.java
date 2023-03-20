package game;

import blocks.I_Block;
import blocks.*;

public class BlockFactory {
	
	private int[] spawnPos;
	
	public Poly generatePoly() {
		return new I_Block(spawnPos[0], spawnPos[1]);
	}
	
	public void setSpawn(int[] spawnPos) {
		this.spawnPos = spawnPos;
	}
}
