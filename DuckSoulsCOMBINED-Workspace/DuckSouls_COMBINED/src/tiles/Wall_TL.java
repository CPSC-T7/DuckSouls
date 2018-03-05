package tiles;

/**
 * This class represents all of the top left corner wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Wall_TL extends Wall {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ╔═";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Wall-Top-Left.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a top left corner wall tile.
	 */
	public Wall_TL(boolean isGUI) {
		super(isGUI, stringRepr, pathToImage);
	}
	
}
