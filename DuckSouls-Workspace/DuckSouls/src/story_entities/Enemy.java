package story_entities;

import java.util.ArrayList;

import story_tiles.Tile;

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
	
	
	/**
	 * returns the path to the image file corresponding with the enemies's current state
	 * 
	 * @return the String corresponding to the path to the enemy sprite 
	 * 
	 * 
	 */
	public String getImage() {
		return "Sprites/Entities/Rat/Rat-"+ this.getOrientationName() + ".png";
	}
}