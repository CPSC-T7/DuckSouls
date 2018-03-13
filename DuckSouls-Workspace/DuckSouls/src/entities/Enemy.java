package entities;

import java.util.ArrayList;

import tiles.Tile;

public class Enemy extends Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	stringRepr	= " E ";
	private static final String	pathToImage	= "/Sprites/Entities/Rat/Rat-";

	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new enemy.
	 */
	public Enemy() {
		super(stringRepr, pathToImage);
	}
	
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
		super(x, y, ID, stringRepr, pathToImage);
		
		// TODO: Fill in constructor.
		
	}
	
	/*
	 *
	 * METHODS
	 * 
	 */
	
	//TODO: Make enemy movementß
	public void move(ArrayList<ArrayList<Tile>> map) {
		
	}

}
