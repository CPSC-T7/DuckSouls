package tiles;

/**
 * This class represents all of the bottom wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
 */
public class Wall_B extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "═══";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Wall-Bottom.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a bottom wall tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Wall_B(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
