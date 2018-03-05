package tiles;

/**
 * This class represents all of the horizontal wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Wall_B extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "═══";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Wall-Bottom.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a horizontal wall tile.
	 */
	public Wall_B(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
