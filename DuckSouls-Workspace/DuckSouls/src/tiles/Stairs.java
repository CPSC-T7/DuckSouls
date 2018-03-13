package tiles;

import utils.Orientation;

public class Stairs extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ╘ ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Stairs.png";
	private String	mapID		= new String();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a stairs tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Stairs(boolean isGUI) {
		super(isGUI, stringRepr, true, pathToImage);
	}
	
	public Stairs(int x, int y, String mapID) {
		super(x, y, true, stringRepr, pathToImage);
		this.mapID = mapID;
	}
	
	
	/**
	 * Returns the ID of the map the door is on.
	 * 
	 * @return The ID of the map the door is on.
	 */
	public String getMapID() {
		
		return mapID;
	
	} // End of getMapID

}
