package coffee;

public interface Coffee {
	
	/**
	 * Get the energy of coffee
	 * 
	 * @return returns energy as integer.
	 */
	public int getEnergy();

	/**
	 * Return the type of coffee.
	 * 
	 * @return type The type of coffee.
	 */
	public String getType();
	
	/**
	 * Set the energy of cofee object.
	 * 
	 * @param int energy sets the energy of coffee object.
	 */
	public void setEnergy(int energy);
}
