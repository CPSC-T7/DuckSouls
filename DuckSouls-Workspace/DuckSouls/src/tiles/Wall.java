package tiles;


/**
 * This class represents all of the wall tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.2
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
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 * @param stringRepr
	 *            The 3-character string used to print the tile.
	 * @param pathToImage
	 *            File path to the image.
	 */
	protected Wall(String stringRepr, String pathToImage) {
		super(stringRepr, false, pathToImage);
	}
	
	protected Wall(int x, int y, String stringRepr, String pathToImage) {
		super(x, y, false, stringRepr, pathToImage);
	}
	
}