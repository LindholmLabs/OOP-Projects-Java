package game;

import blocks.I_Block;

import java.util.Random;

import blocks.*;

public class BlockFactory {

	private static Random random;
	private int[] spawnPos;

	/**
	 * Initiates the blockFactory.
	 */
	public BlockFactory() {
		random = new Random();
	}

	/**
	 * Generates a Poly of given type and spawns it at desired position.
	 * Note: the spawn needs to be set before calling this function.
	 * 
	 * @param type
	 * @return Poly.
	 */
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
	
	/**
	 * Sets the spawnpoint for newly created Polys.
	 * @param spawnPos
	 */
	public void setSpawn(int[] spawnPos) {
		this.spawnPos = spawnPos;
	}

	/**
	 * Return a Poly of random type.
	 * @return Poly.
	 */
	public Poly generateRandomPoly() {
		return generatePoly(random.nextInt(0, 7));
	}
}
