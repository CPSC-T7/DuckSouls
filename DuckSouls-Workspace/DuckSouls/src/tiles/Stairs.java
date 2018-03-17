package tiles;

public class Stairs extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	stringRepr	= " ╘ ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Stairs.png";
	private String				mapID		= new String();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a stairs tile.
	 */
	public Stairs() {
		super(stringRepr, true, pathToImage);
	}
	
	/**
	 * Creates a stairs tile.
	 * 
	 * @param x
	 *            The x co-ord of the tile.
	 * @param y
	 *            The y co-ord of the tile.
	 * @param mapID
	 *            The ID of the map the stairs link to.
	 */
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
