package tiles;

/**
 * This class represents the tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.2.2
 */
public class Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private String		stringRepr	= "TER";
	private char		fileCharacter;
	protected boolean	canWalkOn	= true;

	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */

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
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the string representation of the tile.
	 * 
	 * @return The string representation of the tile.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	}
	
	/**
	 * Returns the file character of the tile.
	 * 
	 * @return The file character of the tile.
	 */
	public char getFileCharacter() {
		
		return this.fileCharacter;
		
	}
	
	/**
	 * Returns whether the tile can be walked on or not.
	 * 
	 * @return Whether the tile can be walked on or not.
	 */
	public boolean getCanWalkOn() {
		
		return this.canWalkOn;
		
	}
	
}
