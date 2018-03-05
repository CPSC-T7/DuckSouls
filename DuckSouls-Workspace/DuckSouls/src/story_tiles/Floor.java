package story_tiles;

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
	
	
	/**
	 * Returns the path to the image file corresponding with the tile's current state
	 * 
	 * @return the String corresponding to the path to the tile sprite 
	 * 
	 */
	public String getImage() {
		
		return "Sprites/Tiles/Sewer/Floor.png";
		
	}// End of getImage

}