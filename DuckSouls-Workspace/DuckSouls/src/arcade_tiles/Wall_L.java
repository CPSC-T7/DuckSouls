package arcade_tiles;

/**
 * This class represents all of the left wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
 */
public class Wall_L extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ║ ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Wall-Left.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a left wall tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Wall_L(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
