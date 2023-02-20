package cofee;

public class Latte implements Coffee {
	private int energy;
	private String type;
	
	/**
	 * Constructor of coffee class
	 * @param energy	Sets the energy of the coffee.
	 */
	public Latte(int energy) {
		this.energy = energy;
		this.type = "Latte";
	}
	
	/**
	 * Get the energy of coffee
	 * @return 	returns energy as integer.
	 */
	@Override
	public int getEnergy() {
		return this.energy;
	}
	
	/**
	 * Set the energy of cofee object.
	 * @param int energy	sets the energy of coffee object.
	 */
	@Override
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * Return the type of coffee.
	 * @return type	The type of coffee.
	 */
	@Override
	public String getType() {
		return this.type;
	}
}
