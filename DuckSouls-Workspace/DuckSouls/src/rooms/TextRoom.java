package rooms;

import java.awt.Point;
import java.util.ArrayList;

import entities.*;
import items.*;
import tiles.*;
import utils.Orientation;
import utils.Utilities;

/**
 * This class represents a text-based room in DuckSouls. Each room is a
 * rectangle of a desired width and height, and can hold one item and entity in
 * each inner tile.
 * 
 * @author Matthew Allwright
 * @author Nadhif Satriana
 * @version 1.11
 */
public class TextRoom extends Room {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final boolean	IS_GUI		= false;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public TextRoom(int size, int enemySpawnChance) {
		
		super(IS_GUI, size, enemySpawnChance);
		
	}
	
	// TODO: Refurbish file reading constructor
	// /**
	// * Creates a room from a specified file.
	// *
	// * @param name
	// * The name of the room. (Used for file i/o)
	// * @param fileName
	// * The file containing the data for the room.
	// */
	// public TextRoom(String name, String fileName) {
	//
	// // Read the file
	// String[] lines = Utilities.readLines(fileName);
	//
	// // Width of the room is the number of characters in a row, minus the walls
	// this.internalWidth = lines[0].split(",").length - 2;
	//
	// // Height of the room is the number of lines, minus the walls
	// this.internalHeight = lines.length - 2;
	//
	// // Generate an array for reading the file
	// String[][] textTileArray = new String[this.internalWidth +
	// 2][this.internalHeight + 2];
	//
	// // Generate the room's tile array
	// this.genTileArray();
	//
	// // Split all the lines of the file by commas to get the tile strings
	// for (int i = 0; i < this.internalHeight + 2; i++) {
	//
	// textTileArray[i] = lines[i].split(",");
	//
	// }
	//
	// // For each position in the room...
	// for (int y = 0; y < this.internalHeight + 2; y++) {
	// for (int x = 0; x < this.internalWidth + 2; x++) {
	//
	// // For each type of tile possible...
	// for (Tile tile : Tile.values()) {
	//
	// // If the file says there should be said tile there...
	// if (textTileArray[x][y].equals(tile.FILE_CHAR)) {
	//
	// // Place said tile
	// this.tileArray[x][y] = tile;
	//
	// // TODO: Delete?
	// // Record where the player was placed
	// // if (tile == Tile.PLAYER) {
	// // this.playerPosition = new Point(x, y);
	// // }
	//
	// // Stop searching through tile types
	// break;
	//
	// }
	//
	// }
	//
	// }
	//
	// }
	//
	// }
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the point of an enemy within 1 tile of the player, if one exists.
	 * Otherwise, returns null.
	 * 
	 * @return The point of an enemy within 1 tile of the player, if one exists.
	 */
	/*
	 * TODO: Make this better.
	 */
	public Point checkForBattlePoint() {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				// If there is an enemy...
				if (this.entityArray[x][y] instanceof Enemy) {
					
					// Note its position
					Point enemyPoint = new Point(x, y);
					
					// If the enemy is within 1 tile of the player
					if (this.getPlayerPoint().distance(enemyPoint) < 1.41421) { // sqrt(2) for the diagonal
						
						// Return the enemy's position
						return enemyPoint;
						
					}
					
				}
				
			}
		}
		
		// If there are no enemies within 1 tile...
		return null;
		
	}
	
	/**
	 * Draws the room to the console.
	 */
	public void draw_Text() {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				if (this.entityArray[x][y] != null) { // If there is a enemy...
					
					// Print the entity
					System.out.print(this.entityArray[x][y].getStringRepr());
					
				} else if (this.itemArray[x][y] != null) { // Or if there is an item...
					
					// Print the item
					System.out.print(this.itemArray[x][y].getStringRepr());
					
				} else { // Otherwise just print the tile
					
					// Print the tile
					System.out.print(this.tileArray[x][y].getStringRepr());
					
				}
				
			}
			
			// Row has been printed, wrap the line
			System.out.println();
			
		}
		
	}
	
	/**
	 * Gets the entity at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The entity at position.
	 */
	public Entity entityAt(Point pos) {
		
		return this.entityArray[pos.x][pos.y];
		
	}
	
	/**
	 * Gets the item at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The item at position.
	 */
	public Item itemAt(Point pos) {
		
		return this.itemArray[pos.x][pos.y];
		
	}
	
	/**
	 * Moves an entity from one point to another, throwing a tantrum, I mean
	 * message, if the ending tile cannot be moved to (walls, etc.).
	 * 
	 * @param toMove
	 *            Point of the entity that should be moved.
	 * @param moveTo
	 *            Point of the entity that will be moved to.
	 */
	public void moveEntity(Point toMove, Point moveTo) {
		
		// If the player can walk on the tile...
		if (this.tileAt(moveTo).getCanWalkOn()) {
			
			// Move the entity
			this.placeEntity(moveTo, this.entityAt(toMove));
			this.placeEntity(toMove, null);
			
			// Note the new position if the player is moved
			if (this.entityAt(moveTo) instanceof Player) {
				this.setPlayerPoint(moveTo);
			}
			
			// Set path tiles...
			// Path tile indicates it has been stepped on
			if (this.tileAt(toMove) instanceof Floor) {
				this.setTile(toMove, new Path(false));
			}
			if (this.tileAt(moveTo) instanceof Floor) {
				this.setTile(moveTo, new Path(false));
			}
			
			// Deal with the stepped on item
			if (this.itemAt(moveTo) != null) {
				
				// TODO: Add step-on methods/actions.
				// https://www.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html
				// Section 2.2
				
				this.placeItem(moveTo, null);
				
			}
			
		} else {
			
			/*
			 * System.out.println("Cannot move tile " + this.tileAt(toMove).toString() +
			 * " from point " + toMove.toString() + " to point " + moveTo.toString() +
			 * ". There's a " + this.tileAt(moveTo).toString() + " there!");
			 */
			
			System.out.println("You can't walk there!");
			
			Utilities.waitMilliseconds(300);
			
		}
		
	}
	
	/**
	 * Takes an array of points and places a door at every point.
	 * 
	 * @param doors
	 *            The array of points to place doors at.
	 */
	public void placeDoors(Point[] doors) {
		
		for (Point pos : doors) {
			
			if (pos != null) {
				
				if (pos.x == 0) { // Left Door
					
					this.setTile(pos, new Door(false, Orientation.WEST));
					
				} else if (pos.x == this.internalWidth + 1) { // Right Door
					
					this.setTile(pos, new Door(false, Orientation.EAST));
					
				} else if (pos.y == 0) { // Top Door
					
					this.setTile(pos, new Door(false, Orientation.NORTH));
					
				} else { // Bottom Door
					
					this.setTile(pos, new Door(false, Orientation.SOUTH));
					
				}
			}
		}
	}
	
	/**
	 * Sets a position at a point in the entity array to a specific entity.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to change.
	 * @param entity
	 *            The entity to set the position to.
	 */
	public void placeEntity(Point position, Entity entity) {
		
		// Note the position if the player is moved
		if (entity instanceof Player) {
			
			this.setPlayerPoint(position);
			
			// Pickup the item that is about to be stepped on
			if (this.itemArray[position.x][position.y] != null) {
				entity.addToInventory(this.itemArray[position.x][position.y]);
				
				// TODO:
				// If weapon or armour automatically equip it NADHIF FIX PLS
				// Don't put/ Put it in inventory if equipped
				// Give player choice
				if (this.itemArray[position.x][position.y].getClass().getSuperclass() == Weapon.class) {
					entity.setWeapon(this.itemArray[position.x][position.y]);
				} else if (this.itemArray[position.x][position.y].getClass().getSuperclass() == Armour.class) {
					entity.setArmour(this.itemArray[position.x][position.y]);
				}
				
				this.itemArray[position.x][position.y] = null;
			}
			
		}
		
		this.entityArray[position.x][position.y] = entity;
		
	}
	
	/**
	 * Sets a position at a point in the item array to a specific item.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to change.
	 * @param item
	 *            The item to set the position to.
	 */
	public void placeItem(Point position, Item item) {
		
		this.itemArray[position.x][position.y] = item;
		
	}
	
	/**
	 * Removes an entity at a point in the entity array.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to remove.
	 */
	public void removeEntity(Point position) {
		
		this.entityArray[position.x][position.y] = null;
		
	}
	
	/**
	 * Removes an item at a point in the item array.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to remove.
	 */
	public void removeItem(Point position) {
		
		this.itemArray[position.x][position.y] = null;
		
	}
	
	/**
	 * Saves the current room object to a text file
	 */
	public void saveToTextFile() {
		
		// Get info for the file
		String fileName = "../TextRooms/" + this.roomName + ".txt", temp;
		ArrayList<String> lines = new ArrayList<String>();
		
		// Print all of the lines to the file
		for (int y = 0; y < this.internalHeight + 2; y++) {
			temp = "";
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				// Format the line
				temp += this.tileArray[x][y].getFileCharacter() + ",";
				
			}
			
			temp = temp.substring(0, temp.length() - 1);
			lines.add(temp);
			
		}
		
		// Write the line
		Utilities.writeFile(fileName, lines);
		
	}
	
	/**
	 * Scatters enemies at random throughout the room.
	 */
	public void scatterEnemies() {
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				// If the spawn chance is high enough...
				if (_random.nextInt(100) < this.enemySpawnChance) {
					
					// Place the entity
					this.placeEntity(new Point(x, y), new Enemy());
					
				}
				
			}
		}
		
	}
	
	/**
	 * Scatters items at random throughout the level.
	 */
	/*
	 * TODO: Probably redo. IDEA: GENERIC CLASSES, OBJECTS, AND CASTING! Save until
	 * after demo 2.
	 */
	public void scatterItems() {
		
		// Random number containers
		int itemTypeNumber, randomItemNumber;
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				// This is used to pick what type of item will be spawned.
				/*
				 * There are 3 types of item: Consumable, Weapon, and Armour. The random number
				 * is from 0 to 5 so that some types of item will be favored over others.
				 * 
				 * As it is configured right now, 3/6 times consumable will be chosen, 2/6 for
				 * weapon, and 1/6 for armour.
				 */
				itemTypeNumber = _random.nextInt(6);
				
				switch (itemTypeNumber) {
					
					// Consumables
					case 0:
					case 1:
					case 2:
						// Generate a random consumable. MUST BE IN THE RIGHT ORDER. I KNOW, IT NEEDS TO
						// BE FIXED!
						randomItemNumber = _random.nextInt(4);
						if (_random.nextInt(100) < Item.allSpawnableConsumables[randomItemNumber].getSpawnChance()) {
							
							switch (randomItemNumber) {
								
								case 0:
									this.placeItem(new Point(x, y), new Bugs());
									break;
								
								case 1:
									this.placeItem(new Point(x, y), new Crouton());
									break;
								
								case 2:
									this.placeItem(new Point(x, y), new Fish());
									break;
								
								case 3:
									this.placeItem(new Point(x, y), new Goo());
									break;
								
							}
							
						}
						break;
					
					// Weapons
					case 3:
					case 4:
						// Generate a random weapon. MUST BE IN THE RIGHT ORDER. I KNOW, IT NEEDS TO BE
						// FIXED!
						randomItemNumber = _random.nextInt(2);
						if (_random.nextInt(100) < Item.allSpawnableWeapons[randomItemNumber].getSpawnChance()) {
							
							switch (randomItemNumber) {
								
								case 0:
									this.placeItem(new Point(x, y), new Knife());
									break;
								
								case 1:
									this.placeItem(new Point(x, y), new Sword());
									break;
								
							}
							
						}
						break;
					
					// Armour
					case 5:
						// Generate a random armour. MUST BE IN THE RIGHT ORDER. I KNOW, IT NEEDS TO BE
						// FIXED!
						randomItemNumber = _random.nextInt(3);
						if (_random.nextInt(100) < Item.allSpawnableArmour[randomItemNumber].getSpawnChance()) {
							
							switch (randomItemNumber) {
								
								case 0:
									this.placeItem(new Point(x, y), new ClothArmour());
									break;
								
								case 1:
									this.placeItem(new Point(x, y), new LeatherArmour());
									break;
								
								case 2:
									this.placeItem(new Point(x, y), new MetalArmour());
									break;
								
							}
							
						}
						break;
					
				}
				
			}
		}
		
	}
	
	/**
	 * Sets a tile at a point in the tile array to a specific tile.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the tile to change.
	 * @param tile
	 *            The tile to set the position to.
	 */
	public void setTile(Point position, Tile tile) {
		
		this.tileArray[position.x][position.y] = tile;
		
	}
	
	/**
	 * Gets the tile at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The tile at position.
	 */
	public Tile tileAt(Point pos) {
		
		return this.tileArray[pos.x][pos.y];
		
	}
	
}
