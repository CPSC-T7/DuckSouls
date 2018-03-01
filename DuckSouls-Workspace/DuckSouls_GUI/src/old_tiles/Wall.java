package old_tiles;

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
	private String image;
	
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
			
			case "L":
				this.setStringRepr(" ║ ");
				this.image = "Tiles/Sewer/Wall-Left.png";
				break;
			
			case "R":
				this.setStringRepr(" ║ ");
				this.image = "Tiles/Sewer/Wall-Right.png";
				break;
				
			case "T":
				this.setStringRepr("═══");
				this.image = "Tiles/Sewer/Wall-Top.png";
				break;
				
			case "B":
				this.setStringRepr("═══");
				this.image = "Tiles/Sewer/Wall-Bottom.png";
				break;
				
			case "TR":
				this.setStringRepr("═╗ ");
				this.image = "Tiles/Sewer/Wall-Top-Right.png";
				break;
				
			case "TL":
				this.setStringRepr(" ╔═");
				this.image = "Tiles/Sewer/Wall-Top-Left.png";
				break;
				
			case "BL":
				this.setStringRepr(" ╚═");
				this.image = "Tiles/Sewer/Wall-Bottom-Left.png";
				break;
				
			case "BR":
				this.setStringRepr("═╝ ");
				this.image = "Tiles/Sewer/Wall-Bottom-Right.png";
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
	
	public String getImage() {
		return this.image;
	}
}
