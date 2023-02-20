package cofee;

public class BlackCoffee implements Coffee {
	private int energy;
	private String type;
	
	/**
	 * Constructor of coffee class
	 * @param energy	Sets the energy of the coffee.
	 */
	public BlackCoffee(int energy) {
		this.energy = energy;
		this.type = "Black Coffee";
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
	
	@Override
	public String toString() {
		return super.toString();
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
