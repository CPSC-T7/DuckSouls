package tiles;

/**
 * This class represents all of the top wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
 */
public class Wall_BL extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ╚═";
	private static final String	pathToImage	= "/Sprites/Tiles/Sewer/Wall-Bottom-Left.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a top wall tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Wall_BL(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
	public Wall_BL(int x, int y) {
		super(x, y, stringRepr, pathToImage);
	}
	
}