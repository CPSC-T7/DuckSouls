package old_tiles;

/**
 * This class represents a floor tile.
 * 
 * @author Colin Yeung
 */
public class Floor extends Tile {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */

	/**
	 * Creates a new floor tile at a given position.
	 * 
	 * @param x The X co-ordinate of the floor tile.
	 * @param y The Y co-ordinate of the floor tile.
	 */
	public Floor(int x, int y) {
		
		super(x, y, true, " . ");
	
	} // End of constructor

	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the type of the tile.
	 * 
	 * @return "Floor".
	 */
	public String getType() {
		
		return "Floor";
		
	} // End of getType

}
