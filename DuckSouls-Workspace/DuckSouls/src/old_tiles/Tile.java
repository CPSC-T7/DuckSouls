package old_tiles;

import java.awt.Point;

/**
 * This class represents a basic, empty tile. It is used as the super class for
 * many other specific types of tile.
 * 
 * @author Colin Yeung
 */
public class Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Point	position	= new Point();
	private boolean	canMoveOn;
	private String	stringRepr;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new tile at a specified position.
	 * 
	 * @param x The X co-ordinate of the tile.
	 * @param y The Y co-ordinate of the tile.
	 */
	public Tile(int x, int y) {
		
		this.canMoveOn = false;
		this.stringRepr = "   ";
		this.position.setLocation(x, y);
		
	} // End of constructor
	
	/**
	 * Creates a new tile at a specified position and defined characteristics.
	 * 
	 * @param x The X co-ordinate of the tile.
	 * @param y The Y co-ordinate of the tile.
	 * @param canMoveOn Whether the player can move on this tile or not.
	 * @param stringRepr The string to display when printing the tile.
	 */
	public Tile(int x, int y, boolean canMoveOn, String stringRepr) {
		
		this.position.setLocation(x, y);
		this.canMoveOn = canMoveOn;
		this.stringRepr = stringRepr;
		
	} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns whether the tile can be moved on or not.
	 * 
	 * @return Whether the tile can be moved on or not.
	 */
	public boolean canMove() {
		
		return this.canMoveOn;
		
	} // End of canMove
	
	/**
	 * Returns the X position of the tile.
	 * 
	 * @return The X position of the tile.
	 */
	public int getX() {
		
		return (int) this.position.getX();
		
	} // End of getX
	
	/**
	 * Returns the Y position of the tile.
	 * 
	 * @return The Y position of the tile.
	 */
	public int getY() {
		
		return (int) this.position.getY();
		
	} // End of getY
	
	/**
	 * Sets if the tile can be moved to or not.
	 * 
	 * @param canMoveOn Whether the tile can be moved to or not.
	 */
	public void setCanMoveOn(boolean canMoveOn) {
		
		this.canMoveOn = canMoveOn;
	
	} // End of canMoveOn
	
	/**
	 * Returns the string representation of the tile.
	 * 
	 * @return The string representation of the tile.
	 */
	public String getStringRepr() {
		
		return this.stringRepr;
		
	} // End of getStringRepr
	
	/**
	 * Returns the type of the tile.
	 * 
	 * @return The type of the tile.
	 */
	public String getType() {
		
		return "Empty";
	
	} // End of getType
	
	/**
	 * Sets the string representation of the tile.
	 * 
	 * @param stringRepr The new string representation of the tile.
	 */
	public void setStringRepr(String stringRepr) {
		
		this.stringRepr = stringRepr;
		
	} // End of setStringRepr
	
	/**
	 * Returns the ID of the map this tile is in.
	 * 
	 * @return The ID of the map this tile is in.
	 */
	public String getMapID() {
		
		return null;
		
	}
	
	
	/**
	 * Returns the path to the image file corresponding with the entity's current state
	 * 
	 * @return the String corresponding to the path to the entity sprite (default floor sprite)
	 * 
	 */
	public String getImage() {
		
		return "Sprites/Tiles/Sewer/Floor.png";
		
	}// End of getImage
	
}