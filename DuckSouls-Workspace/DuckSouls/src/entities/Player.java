package entities;

import java.util.ArrayList;

import tiles.Tile;
import utils.Orientation;

/**
 * This class represents a player within DuckSouls. They can have equipped
 * weapons, armour, and an inventory.
 * 
 * @author Matthew Allwright
 * @author Colin Yeung
 */
public class Player extends Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	stringRepr	= " @ ";
	private static final String	pathToImage	= "/Sprites/Entities/Duck/Duck-";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new player.
	 */
	public Player() {
		
		// Create a new character
		super(0, 0, stringRepr, pathToImage);
		
	} // End of default constructor.
	
	/**
	 * Creates a new player at a specified position in a map.
	 * 
	 * @param x
	 *            The X co-ordinate of the player.
	 * @param y
	 *            The Y co-ordinate of the player.
	 * @param map_2DArrayList
	 *            The map of tiles the player is a part of.
	 */
	public Player(int x, int y, ArrayList<ArrayList<Tile>> map_2DArrayList) {
		
		// Create a new character at the position
		super(0, 0, stringRepr, pathToImage);
		this.move(x, y, map_2DArrayList);
		
	} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Accepts a String corresponding to wasd and moves accordingly
	 * 
	 * @param map_2DArrayList
	 *            The map in which the player is moving.
	 * 
	 * @param input
	 *            one of w, a, s, d which represents the direction the player is
	 *            moving
	 */
	public void move(ArrayList<ArrayList<Tile>> map_2DArrayList, String input) {
		
		// Define some control variables
		boolean hasMove = false;
		String[] possiblemove = { "W", "A", "S", "D" };
		
		// For each possible direction...
		for (String direction : possiblemove) {
			
			// If the input matches...
			if (input.equals(direction)) {
				hasMove = true;
				
			}
		}
		
		// If the input is valid
		if (hasMove) {
			switch (input) {
				
				/*
				 * For each case:
				 * 
				 * If the player is within the bounds to move... Move the player, And set the
				 * boolean accordingly.
				 * 
				 */
				
				case "W":
					if (!(Orientation.NORTH == this.getOrientation())) {
						this.setOrientation(Orientation.NORTH);
					} else {
						if (this.getY() >= 1) {
							if (map_2DArrayList.get(this.getY() - 1).get(this.getX()).getCanWalkOn()) {
								super.move(this.getX(), this.getY() - 1, map_2DArrayList);
							}
						}
					}
					break;
				
				case "S":
					if (!(Orientation.SOUTH == this.getOrientation())) {
						this.setOrientation(Orientation.SOUTH);
					} else {
						if (this.getY() < map_2DArrayList.size() - 1) {
							if (map_2DArrayList.get(this.getY() + 1).get(this.getX()).getCanWalkOn()) {
								super.move(this.getX(), this.getY() + 1, map_2DArrayList);
							}
						}
					}
					break;
				
				case "A":
					if (!(Orientation.WEST == this.getOrientation())) {
						this.setOrientation(Orientation.WEST);
					} else {
						if (this.getX() > 0) {
							if (map_2DArrayList.get(this.getY()).get(this.getX() - 1).getCanWalkOn()) {
								super.move(this.getX() - 1, this.getY(), map_2DArrayList);
							}
						}
					}
					break;
				
				case "D":
					if (!(Orientation.EAST == this.getOrientation())) {
						this.setOrientation(Orientation.EAST);
					} else {
						if (this.getX() < map_2DArrayList.get(this.getY()).size() - 1) {
							if (map_2DArrayList.get(this.getY()).get(this.getX() + 1).getCanWalkOn()) {
								super.move(this.getX() + 1, this.getY(), map_2DArrayList);
							}
						}
					}
					break;
				
			}
		}
	}// End of move
	
}
