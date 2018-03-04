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
	
	private static final String stringRepr = "   ";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a floor tile.
	 */
	public Floor() {
		super(stringRepr, true);
	}

}
