package utils;

/**
 * This enumerator is used to define the four cardinal directions. This prevents
 * having to pass characters or stings to determine direction.
 * 
 * @author Matthew Allwright
 * @version 1.2.1
 *
 */
public enum Orientation {
	
	NORTH("Up"),
	EAST("Right"),
	SOUTH("Down"),
	WEST("Left");
	
	public String str; // For basic access
	
	/**
	 * Creates a new orientation.
	 * @param str The colloquial name for the orientation. This is used for things such as file access.
	 */
	private Orientation(String str) {
		this.str = str;
	}
	
}
