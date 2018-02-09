package tiles;

/**
 * This class represents a wall tile.
 * 
 * @author Colin Yeung
 *
 */
public class Wall extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private String orientation;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a wall at a certain position with a specific orientation.
	 * 
	 * @param x
	 *            The X co-ordinate of the wall.
	 * @param y
	 *            The Y co-ordinate of the floor tile.
	 * @param orientation
	 *            The orientation of the wall. Must be one of: V, H, TL, TR, BL, or
	 *            BR.
	 */
	public Wall(int x, int y, String orientation) {
		
		// Create a blank tile
		super(x, y);
		
		this.orientation = orientation;
		
		// Players cannot walk through walls!
		this.setCanMoveOn(false);
		
		// Set the string representation to the appropriate unicode box characters
		switch (orientation) {
			
			case "V":
				this.setStringRepr(" ║ ");
				break;
				
			case "H":
				this.setStringRepr("═══");
				break;
				
			case "TR":
				this.setStringRepr("═╗ ");
				break;
				
			case "TL":
				this.setStringRepr(" ╔═");
				break;
				
			case "BL":
				this.setStringRepr(" ╚═");
				break;
				
			case "BR":
				this.setStringRepr("═╝ ");
				break;
				
		}
		
	} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the type of the tile.
	 * 
	 * @return "Wall".
	 */
	public String getType() {
		
		return "Wall";
		
	} // End of getType
	
}
