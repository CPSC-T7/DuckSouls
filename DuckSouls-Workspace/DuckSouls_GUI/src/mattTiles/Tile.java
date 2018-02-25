package mattTiles;

public class Tile {
	
	public String	STRING_REPR	= "TER";
	public String	FILE_CHAR;
	public boolean	CAN_WALK_ON	= true;
	
	/**
	 * Creates a tile.
	 * 
	 * @param STRING_REPR
	 *            The 3-character string used to print the tile.
	 * @param CAN_WALK_ON
	 *            Whether or not a player can walk on the tile.
	 */
	protected Tile(String STRING_REPR, boolean CAN_WALK_ON) {
		
		this.STRING_REPR = STRING_REPR;
		this.FILE_CHAR = Character.toString(this.STRING_REPR.charAt(1)); // Middle char
		this.CAN_WALK_ON = CAN_WALK_ON;
		
	}
	
}
