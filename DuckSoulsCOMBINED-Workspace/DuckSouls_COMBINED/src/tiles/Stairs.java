package tiles;

/**
 * This class represents all of the stairs tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Stairs extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " ╘ ";
	private static final String	pathToImage	= "../Sprites/Tiles/Sewer/Stairs.png";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a stairs tile.
	 */
	public Stairs(boolean isGUI) {
		super(isGUI, stringRepr, true, pathToImage);
	}
	
}
