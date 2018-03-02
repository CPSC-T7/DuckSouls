package old_entities;

import java.awt.Point;
import java.util.ArrayList;

import old_tiles.Tile;

/**
 * This class represents a default character, and is used as a super class for
 * enemy and player characters.
 * 
 * @author Colin Yeung
 */
public class Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Point	position				= new Point();
	private String orientation				= "d";
	private boolean	isPlayer				= false;
	private String	stringRepr				= new String();
	public final String[]	POSSIBLE_ORIENTATIONS	= { "w", "a", "s", "d" };
	private int ID;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new character at a defined position.
	 * 
	 * @param x
	 *            The X co-ordinate of the character.
	 * @param y
	 *            The Y co-ordinate of the character.
	 * @param isPlayer
	 *            Whether the character is the player or not.
	 */
	public Entity(int x, int y, boolean isPlayer) {
		
		// Set the character characteristics
		this.position.setLocation(x, y);
		this.isPlayer = isPlayer;
		
		// Set the appropriate string representation
		if (this.isPlayer) {
			
			this.stringRepr = " @ "; // Player
			
		} else {
			
			this.stringRepr = " E "; // Enemy
			
		}
		
	} // End of constructor
	
	public Entity(int x, int y, boolean isPlayer, int ID) {
			
			// Set the character characteristics
			this.position.setLocation(x, y);
			this.isPlayer = isPlayer;
			this.ID = ID;
			
			// Set the appropriate string representation
			if (this.isPlayer) {
				
				this.stringRepr = " @ "; // Player
				
			} else {
				
				this.stringRepr = " E "; // Enemy
				
			}
			
		} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the string representation of the character.
	 * 
	 * @return The string representation of the character.
	 */
	public String getStringRepr() {
		
		return this.stringRepr;
		
	} // End of getStringRepr
	
	/**
	 * Returns the orientation of the entity
	 * 
	 * @return String, the orientation of the entity in form wasd
	 */
	public String getOrientation() {
		return this.orientation;
	}// End of getOrientation
	
	/**
	 * Moves the character to a specified position.
	 * 
	 * @param x
	 *            The X co-ordinate to move to.
	 * @param y
	 *            The Y co-ordinate to move to.
	 * @param mapdata
	 *            The array list of tiles of the map the character should move in.
	 */
	public void setPos(int x, int y, ArrayList<ArrayList<Tile>> mapdata) {
		
		// Move the character
		//this.position.setLocation(x, y);
		
		
		// If the specific point can be moved to...
		if (mapdata.get(y).get(x).canMove()) {
			
			// Move the character
			position.setLocation(x, y);
			
		}
		
	} // End of setPos
	
	/**
	 * Moves the character to a specified position. <br>
	 * TODO: Swap Y and X. X should always come first when imputing co-ords!
	 * 
	 * @param y
	 *            The Y co-ordinate to move to.
	 * @param x
	 *            The X co-ordinate to move to.
	 * @param mapdata
	 *            The array list of tiles of the map the character should move in.
	 */
	public void move(int y, int x, ArrayList<ArrayList<Tile>> mapdata) {
		
		// Move the character
		//this.position.setLocation(x, y);
		
		
		// If the specific point can be moved to...
		if (mapdata.get(y).get(x).canMove()) {
			
			// Move the character
			this.position.setLocation(x, y);
			
		}
		
	} // End of move
	
	/**
	 * Turn the character.
	 * 
	 * @param direction
	 *            The direction to face. Must be one of: w, a, s, or d.
	 */
	public void turn(String direction) {
		
		// For each possible direction...
		for (String i : this.POSSIBLE_ORIENTATIONS) {
			
			// If the direction matches the one given...
			if (direction == i) {
				
				// Face that way
				this.orientation = direction;
				
			}
			
		}
		
	} // End of turn
	
	/**
	 * Returns the X co-ordinate of the player.
	 * 
	 * @return The X co-ordinate of the player.
	 */
	public int getX() {
		
		return this.position.x;
		
	} // End of getX
	
	/**
	 * Returns the Y co-ordinate of the player.
	 * 
	 * @return The Y co-ordinate of the player.
	 */
	public int getY() {
		
		return this.position.y;
		
	} // End of getY
	
	/**
	 * Returns whether the character is the player or not.
	 * 
	 * @return Whether the character is the player or not.
	 */
	public boolean isPlayer() {
		
		return this.isPlayer;
		
	} // End of isPlayer
	
	/**
	 * Returns whether the character is next to a specific position.
	 * 
	 * @param x
	 *            The X co-ordinate of the position to check.
	 * @param y
	 *            The Y co-ordinate of the position to check.
	 * @return Whether the character is next to the position.
	 */
	public boolean isNextTo(int x, int y) {
		
		// Return whether the position is less than or equal to 1 tile away in x & y
		return (Math.abs(this.position.y - y) <= 1) && (Math.abs(this.position.x - x) <= 1);
		
	} // End of isNextTo
	
	/**
	 * Sets an entity's id
	 * 
	 * @param Id
	 *            The int ID to give to the entity
	 *            
	 */
	public void setID(int Id) {
		
		this.ID = Id;
		
	}// End of setID
	
	/**
	 * Returns an entity's int id
	 * 
	 * @return An int representing the entity's id
	 * 
	 */
	public int getID() {
		
		return this.ID;
		
	}// End of getID
}
