package tiles;


/**
 * This class represents all of the floor tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Colin Au Yeung
 * @version 1.1
 */
public class Floor extends Tile {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	stringRepr	= "   ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Floor.png";
	
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new floor tile.
	 */
	public Floor() {
		super(stringRepr, true, pathToImage);
	}

	/**
	 * Creates a new floor tile.
	 * 
	 * @param x
	 *            The x co-ord of the tile.
	 * @param y
	 *            The y co-ord of the tile.
	 */
	public Floor(int x, int y) {
		super(x, y, true, stringRepr, pathToImage);
	}

}
