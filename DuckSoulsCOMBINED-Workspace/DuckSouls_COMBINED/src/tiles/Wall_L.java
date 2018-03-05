package tiles;

/**
 * This class represents all of the vertical wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Wall_L extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ║ ";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Wall-Left.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a vertical wall tile.
	 */
	public Wall_L(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
