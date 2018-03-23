package tiles;

/**
 * This class represents all of the top wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
 */
public class Wall_T extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	stringRepr	= "═══";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Wall-Top.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a top wall tile.
	 */
	public Wall_T() {
		super(stringRepr, pathToImage);
	}
	
	/**
	 * Creates a top wall tile.
	 * 
	 * @param x
	 *            The x co-ord of the tile.
	 * @param y
	 *            The y co-ord of the tile.
	 */
	public Wall_T(int x, int y) {
		super(x, y, stringRepr, pathToImage);
	}
	
}