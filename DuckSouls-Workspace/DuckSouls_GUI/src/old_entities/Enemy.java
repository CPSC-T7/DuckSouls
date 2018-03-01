package old_entities;

import java.util.ArrayList;

import old_tiles.Tile;

/**
 * This class represents an enemy character.
 * 
 * @author Colin Yeung
 */
public class Enemy extends Entity {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new enemy character at a specific position.
	 * 
	 * @param x
	 *            The X co-ordinate of the enemy.
	 * @param y
	 *            The Y co-ordinate of the enemy.
	 */
	public Enemy(int x, int y, int ID) {
		
		// Create a new character
		super(x, y, false, ID);
		
		// TODO: Fill in constructor.
		
	}
	
	/*
	 *
	 * METHODS
	 * 
	 */
	
	//TODO: What does this do?
	public void move(ArrayList<ArrayList<Tile>> map) {
		
	}
	
	public String getImage() {
		return "Sprites/Rat/Rat-"+ this.getOrientationName() + ".png";
	}
}
