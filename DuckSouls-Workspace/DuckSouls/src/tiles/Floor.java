package tiles;

/**
 * This class represents all of the floor tiles for DuckSouls.
 * 
 * @author Matthew Allwright
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
	 * Creates a floor tile.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	public Floor(boolean isGUI) {
		super(isGUI, stringRepr, true, pathToImage);
	}
	
}
