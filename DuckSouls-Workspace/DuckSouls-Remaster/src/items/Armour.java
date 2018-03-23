package items;

import java.awt.Image;

public enum Armour implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	CLOTH_ARMOUR,
	LEATHER_ARMOUR,
	METAL_ARMOUR;
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String NAME;
	private final String STRING_REPR;
	private final Image IMAGE;
	private final int SPAWN_CHANCE;
	private final int DEFENSE;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/*
	 * 
	 * METHODS
	 * 
	 */

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringRepr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryToSpawn() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
