package tiles;

/**
 * This class represents all of the bottom right corner wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
 */
public class Wall_BR extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = "═╝ ";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Wall-Bottom-Right.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a bottom right corner wall tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Wall_BR(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
