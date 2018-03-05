package tiles;

/**
 * This class represents all of the wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1.2
 */
public class Wall extends Tile {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a wall tile.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the tile.
	 */
	protected Wall(boolean isGUI, String stringRepr, String pathToImage) {
		super(isGUI, stringRepr, false, pathToImage);
	}
	
}
