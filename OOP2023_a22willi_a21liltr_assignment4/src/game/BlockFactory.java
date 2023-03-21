package game;

import blocks.I_Block;

import java.util.Random;

import blocks.*;

public class BlockFactory {

	private static Random random;
	private int[] spawnPos;

	public BlockFactory() {
		random = new Random();
	}

	public Poly generatePoly(int type) {
		switch (type) {
		case 0:
			return new Z_Block(spawnPos[0], spawnPos[1]);
		case 1:
			return new I_Block(spawnPos[0], spawnPos[1]);
		case 2:
			return new J_Block(spawnPos[0], spawnPos[1]);
		case 3:
			return new L_Block(spawnPos[0], spawnPos[1]);
		case 4:
			return new S_Block(spawnPos[0], spawnPos[1]);
		case 5:
			return new O_Block(spawnPos[0], spawnPos[1]);
		case 6:
			return new T_Block(spawnPos[0], spawnPos[1]);
		default:
			return new O_Block(spawnPos[0], spawnPos[1]);
		}
	}

	public void setSpawn(int[] spawnPos) {
		this.spawnPos = spawnPos;
	}

	public Poly generateRandomPoly() {
		return generatePoly(random.nextInt(0, 6));
	}
}
