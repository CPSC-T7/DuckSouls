package tiles;

/**
 * This class represents all of the top right corner wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Wall_TR extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "═╗ ";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Wall-Top-Right.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a top right corner wall tile.
	 */
	public Wall_TR(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
