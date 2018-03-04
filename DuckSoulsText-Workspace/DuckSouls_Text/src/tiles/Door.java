package tiles;

import utils.Orientation;

/**
 * This class represents all of the door for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.3
 */
public class Door extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private String		key;			// TODO: Make keys an enum.
	private Orientation	orientation;	// TODO: Implement.
	private boolean		locked	= false;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new unlocked door.
	 */
	public Door() {
		super(" D ", true);
	}
	
	/**
	 * Creates a new locked door.
	 * 
	 * @param key
	 *            The key that can be used to unlock the door.
	 */
	public Door(String key) {
		super(" D ", false);
		this.locked = true;
		this.key = key;
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Checks to see if the door can be unlocked with a key. If it can, the door is
	 * then unlocked. If it can't, then the door remains locked.
	 * 
	 * @param key
	 *            The key to try on the door.
	 * @return whether the door was unlocked with the key or not.
	 */
	public boolean tryUnlock(String key) {
		
		// If it can be unlocked...
		if (key.equals(this.key)) {
			
			// Unlock the door
			this.locked = false;
			this.canWalkOn = true;
			return true;
			
		} else { // If it can't...
			
			// Don't
			return false;
			
		}
		
	}
	
	/**
	 * Returns whether the door is locked or not.
	 * 
	 * @return Whether the door is locked or not.
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Returns a copy of the key that this door is locked with.
	 * 
	 * @return A copy of the key that this door is locked with.
	 */
	public String getKey() {
		return new String(this.key);
	}
	
}
