package tiles;

/**
 * This class represents all of the horizontal wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Wall_T extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "═══";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Wall-Top.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a horizontal wall tile.
	 */
	public Wall_T(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
