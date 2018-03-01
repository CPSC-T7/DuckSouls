package colintiles;

import java.util.ArrayList;

/**
 * This class represents a door tile.
 * 
 * @author Colin Yeung
 */
public class Door extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private boolean	isLocked	= false;
	private String	mapID		= new String();
	private String	keyID		= new String();
	private char orientation;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new door with all of the required information
	 * 
	 * @param x
	 *            The X co-ordinate of the door.
	 * @param y
	 *            The Y co-ordinate of the door.
	 * @param isLocked
	 *            Whether the door is locked or not.
	 * @param mapID
	 *            The ID of the map this door is on.
	 * @param isVertical
	 *            Whether or not this door is on a vertical (side) wall.
	 * @param keyID
	 *            The ID of the key that unlocks this door.
	 */
	public Door(int x, int y, boolean isLocked, String mapID, char Orientation, String keyID) {
		
		// Create a tile at the position
		super(x, y, false, " D ");
		
		// Set the other values accordingly
		this.isLocked = isLocked;
		this.setCanMoveOn(!this.isLocked);
		this.mapID = mapID;
		this.keyID = keyID;
		this.orientation = Orientation;
		
	} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the type of the tile.
	 * 
	 * @return "Door".
	 */
	public String getType() {
		
		return "Door";
		
	} // End of getType
	
	/**
	 * Unlocks the door.
	 */
	public void unlock() {
		
		this.isLocked = false;
		
		// The player can now move through this door
		this.setCanMoveOn(true);
		
	} // End of unlock
	
	/**
	 * Returns the ID of the map the door is on.
	 * 
	 * @return The ID of the map the door is on.
	 */
	public String getMapID() {
		
		return mapID;
	
	} // End of getMapID
	
	/**
	 * Sees if the door can be unlocked with any key in an array list of keys.
	 * 
	 * @param keyIDs The array list of keys to check.
	 * @return Whether or not the door can be unlocked with a key in keyIDs.
	 */
	public boolean canUnlockWith(ArrayList<String> keyIDs) {
		
		// For each key...
		for (String key : keyIDs) {
			
			// If the key is the needed key...
			if (key.equals(keyID)) {
				
				// The door can be unlocked
				return true;
				
			}
				
		}
		
		// Otherwise the door cannot be unlocked
		return false;
	
	} // End of canUnlockWith
	
	public String getImage() {
		String direction = "Left";
		switch(this.orientation) {
			case 'T': direction = "Top"; break; 
			case 'L': direction = "Left"; break; 
			case 'R': direction = "Right"; break; 
			case 'B': direction = "Bottom"; break; 
			
		}
		return "Tiles/Sewer/Door-" + direction + ".png";
	}
	
}
