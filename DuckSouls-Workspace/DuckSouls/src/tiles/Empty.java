package tiles;

/**
 * This class represents all of the path tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Empty extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "   ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Floor.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates an empty tile.
	 */
	public Empty() {
		super(stringRepr, true, pathToImage);
	}

}
