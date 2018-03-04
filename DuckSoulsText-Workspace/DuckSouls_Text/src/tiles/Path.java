package tiles;

/**
 * This class represents all of the path tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Path extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " . ";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a path tile.
	 */
	public Path() {
		super(stringRepr, true);
	}

}
