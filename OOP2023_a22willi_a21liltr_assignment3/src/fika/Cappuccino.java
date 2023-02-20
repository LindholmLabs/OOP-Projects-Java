package fika;

public class Cappuccino implements Coffee {
	private int energy;
	
	/**
	 * Constructor of coffee class
	 * @param energy	Sets the energy of the coffee.
	 */
	public Cappuccino(int energy) {
		this.energy = energy;
	}
	
	/**
	 * Allows a coffee to be "used".
	 * @return returns the coffees energy.
	 */
	@Override
	public int drink() {
		return this.energy;
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

}
