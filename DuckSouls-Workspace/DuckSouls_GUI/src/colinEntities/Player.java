package colinEntities;

import java.util.ArrayList;
import java.util.Scanner;

import colintiles.*;

/**
 * This class represents a player character.
 * 
 * @author Colin Yeung
 */
public class Player extends Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
//	private ArrayList<Item>	inventory	= new ArrayList<Item>();
	private Scanner scanner = new Scanner(System.in);
	
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
		super(0, 0, true);
		
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
		super(x, y, true);
		this.setPos(x, y, map_2DArrayList);
		
	} // End of constructor
	
	/*
	 *
	 * METHODS
	 * 
	 */
	
	/**
	 * Prompts the user for input and moves the player within the map accordingly.
	 * 
	 * @param map_2DArrayList
	 *            The map in which the player is moving.
	 */
	public void move(ArrayList<ArrayList<Tile>> map_2DArrayList) {
		
		// Define some control variables
		String[] possibleInput = { "w", "a", "s", "d"};
		boolean hasMoved = false;
		
		/*
		 * 
		 * Loop:
		 * 
		 * Asks the user where they would like to move (wasd for nesw).
		 * 
		 * Breaks once the player has moved.
		 * 
		 */
		do {
			
			// Ask the user for input
			System.out.println("Where do you want to move? (input with wasd)");
			String input = scanner.nextLine();
			input = input.trim();
			
			// For each possible direction...
			for (String direction : possibleInput) {
				
				// If the input matches...
				if (input.equals(direction)) {
					
					// Move the appropriate direction
					switch (input) {
						
						/*
						 * For each case:
						 * 
						 * If the player is within the bounds to move...
						 * 		Move the player,
						 * 		And set the boolean accordingly.
						 * 
						 */
						
						case "w":
							if(this.getY() >= 1) {
								if(map_2DArrayList.get(this.getY()-1).get(this.getX()).canMove()) {
									super.move(this.getY()-1, this.getX(), map_2DArrayList);
									hasMoved = true;
								}
							}
							break;
							
						case "s":
							if(this.getY() < map_2DArrayList.size()-1) {
								if(map_2DArrayList.get(this.getY()+1).get(this.getX()).canMove()) {
									super.move(this.getY()+1, this.getX(), map_2DArrayList);
									hasMoved = true;
								}
							}
							break;
							
						case "a":
							if(this.getX() > 0) {
								if(map_2DArrayList.get(this.getY()).get(this.getX()-1).canMove()) {
									super.move(this.getY(), this.getX()-1, map_2DArrayList);
									hasMoved = true;
								}
							}
							break;
							
						case "d":
							if(this.getX() < map_2DArrayList.get(this.getY()).size()-1) {
								if(map_2DArrayList.get(this.getY()).get(this.getX()+1).canMove()) {
									super.move(this.getY(), this.getX()+1, map_2DArrayList);
									hasMoved = true;
								}
							}
							break;
//						case "i":
//							System.out.println("Player Inventory:\n");
//							for(Item item : this.getInventory()) {
//								System.out.println(item.getName());
//							}
//							System.out.println("\nPress Enter To Exit.");
//							scanner.nextLine();
//							hasMoved = true;
//							break;
						
					}
					
				}
				
			}
			
			// If the player hasn't moved, yell at the player and repeat
			if (!hasMoved) {
				
				System.out.println("Invalid move");
				
			}
			
		} while (!hasMoved);
		
	}// End of move
	
	
	/**
	 * Accepts a String corresponding to wasd and moves accordingly
	 * 
	 * @param map_2DArrayList
	 *            The map in which the player is moving.
	 *            
	 * @param input
	 * 			  one of w, a, s, d which represents the direction the player is moving
	 */
	public void move(ArrayList<ArrayList<Tile>> map_2DArrayList, String input) {
		
		// Define some control variables
		boolean hasMove = false;
		
		// For each possible direction...
		for (String direction : super.POSSIBLE_ORIENTATIONS) {
			
			// If the input matches...
			if (input.equals(direction)) {
				hasMove = true;
				
			}
		}
					
		// If the input is valid
		if(hasMove) {
			switch (input) {
					
					/*
					 * For each case:
					 * 
					 * If the player is within the bounds to move...
					 * 		Move the player,
					 * 		And set the boolean accordingly.
					 * 
					 */
					
					case "w":
						if(!input.equals(this.getOrientation())) {
							this.turn("w");
						}
						else {
							if(this.getY() >= 1) {
								if(map_2DArrayList.get(this.getY()-1).get(this.getX()).canMove()) {
									super.move(this.getY()-1, this.getX(), map_2DArrayList);
								}
							}
						}
						break;
						
					case "s":
						if(!input.equals(this.getOrientation())) {
							this.turn("s");
						}
						else {
							if(this.getY() < map_2DArrayList.size()-1) {
								if(map_2DArrayList.get(this.getY()+1).get(this.getX()).canMove()) {
									super.move(this.getY()+1, this.getX(), map_2DArrayList);
								}
							}
						}
						break;
						
					case "a":
						if(!input.equals(this.getOrientation())) {
							this.turn("a");
						}
						else {
							if(this.getX() > 0) {
								if(map_2DArrayList.get(this.getY()).get(this.getX()-1).canMove()) {
									super.move(this.getY(), this.getX()-1, map_2DArrayList);
								}
							}
						}
						break;
						
					case "d":
						if(!input.equals(this.getOrientation())) {
							this.turn("d");
						}
						else {
							if(this.getX() < map_2DArrayList.get(this.getY()).size()-1) {
								if(map_2DArrayList.get(this.getY()).get(this.getX()+1).canMove()) {
									super.move(this.getY(), this.getX()+1, map_2DArrayList);
								}
							}
						}
						break;
						
//					case "i":
//						System.out.println("Player Inventory:\n");
//						for(Item item : this.getInventory()) {
//							System.out.println(item.getName());
//						}
//						System.out.println("\nPress Enter To Exit.");
//						scanner.nextLine();
//						break;
					
				}
		}
	}// End of move

	
//	public void addToInventory(Item item) {
//		this.inventory.add(item);
//	}
//	
//	public ArrayList<Item> getInventory() {
//		return this.inventory;
//	}
	
	
	/**
	 * returns the path to the image file corresponding with the player's current state
	 * 
	 * @return the String corresponding to the path to the player sprite 
	 * 
	 */
	public String getImage() {
		
		return "Sprites/Duck/Duck-"+ this.getOrientationName() + ".png";
		
	}//end of getImage
}
