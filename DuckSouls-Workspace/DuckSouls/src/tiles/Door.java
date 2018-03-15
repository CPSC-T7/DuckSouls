package tiles;

import utils.Orientation;

public class Door extends Tile {

	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Orientation		orientation;									// TODO: Implement.
	private static String	pathToImage	= "/Sprites/Tiles/Sewer/Door-";
	private String	mapID		= new String();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new unlocked door.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 * @param orientation
	 *            The orientation the door is facing.
	 */
	public Door(Orientation orientation) {
		super(" D ", true, pathToImage + orientation.str + ".png");
		this.orientation = orientation;
	}
	
	
	public Door(int x, int y, String mapID, Orientation orientation) {
		
		// Create a tile at the position
		super(x, y, true, " D ", pathToImage + orientation.str + ".png");
		
		// Set the other values accordingly
		this.mapID = mapID;
		this.orientation = orientation;
		
	} // End of constructor
	
	
	/**
	 * Returns the ID of the map the door is on.
	 * 
	 * @return The ID of the map the door is on.
	 */
	public String getMapID() {
		
		return mapID;
	
	} // End of getMapID

}
