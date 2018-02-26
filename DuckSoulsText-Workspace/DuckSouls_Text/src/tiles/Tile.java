package tiles;

public class Tile {
	
	private String		stringRepr	= "TER";
	protected boolean	canWalkOn	= true;
	private char		fileCharacter;
	
	/**
	 * Creates a tile.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the tile.
	 * @param canWalkOn
	 *            Whether or not a player can walk on the tile.
	 */
	protected Tile(String stringRepr, boolean canWalkOn) {
		
		this.stringRepr = stringRepr;
		this.fileCharacter = this.stringRepr.charAt(1); // Middle char
		this.canWalkOn = canWalkOn;
		
	}
	
	/**
	 * Returns the string representation of the tile.
	 * 
	 * @return The string representation of the tile.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	} // End of getStringRepr
	
	/**
	 * Returns the file character of the tile.
	 * 
	 * @return The file character of the tile.
	 */
	public char getFileChar() {
		
		return new Character(this.fileCharacter);
		
	} // End of getStringRepr
	
	/**
	 * Returns whether the tile can be walked on or not.
	 * 
	 * @return Whether the tile can be walked on or not.
	 */
	public boolean getCanWalkOn() {
		
		return new Boolean(this.canWalkOn);
		
	} // End of getStringRepr
	
}
