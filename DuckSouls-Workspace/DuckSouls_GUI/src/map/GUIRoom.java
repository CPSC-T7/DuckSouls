package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

//JavaFX
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import mattEntities.*;
import mattTiles.*;
import mattItems.*;
import utils.Utilities;

//TODO: Fill in JavaDocs

/**
 * GUI-Based room class.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @requires java.awt.Point
 * @version 1.6.2
 */
public class GUIRoom {
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Random	_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_ROOM_SIZE	= 5;
	
	private int				internalWidth, internalHeight;
	private int				enemySpawnChance	= 5;
	
	//JavaFX Variables
	private int				tileSize 	= 64;
	
	private Tile[][]		tileArray;
	private Item[][]		itemArray;
	private Entity[][]		entityArray;
	
	public Point			playerPoint;
	
	public String			roomName;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a square room of set size with scattered items. <br>
	 * This is the constructor used by TextLevel to create rooms.
	 * 
	 * @param size
	 *            Width and height of the room. Must be 0 < width < 32.
	 */
	public GUIRoom(int size) {
		
		this.internalWidth = size;
		this.internalHeight = size;
		
		this.genTileArray();
		this.scatterItems();
		this.scatterEnemies();
		
	}
	
	/**
	 * Creates an empty room of set size.
	 * 
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 */
	public GUIRoom(int width, int height) {
		
		this.internalWidth = width;
		this.internalHeight = height;
		this.genTileArray();
		
	}
	
	/**
	 * Creates an empty, doorless room of default size.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 */
	public GUIRoom(String name) {
		
		this.roomName = name;
		this.internalWidth = DEFAULT_ROOM_SIZE;
		this.internalHeight = DEFAULT_ROOM_SIZE;
		this.genTileArray();
		
	}
	
	/**
	 * Creates an empty room of set size.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 */
	public GUIRoom(String name, int width, int height) {
		
		this.internalWidth = width;
		this.internalHeight = height;
		this.genTileArray();
		
	}
	
	/**
	 * Creates an empty room of default size, with specified door points.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 * @param doors
	 *            A java.awt.Point array of door co-ordinates. Acceptable points lie
	 *            in a range of (0, 0) to (width+2, height+2) to accommodate walls,
	 *            with (0, 0) as the top-left.
	 */
	public GUIRoom(String name, Point[] doors) {
		
		this.internalWidth = DEFAULT_ROOM_SIZE;
		this.internalHeight = DEFAULT_ROOM_SIZE;
		this.genTileArray();
		
		this.placeDoors(doors);
		
	}
	
	/**
	 * Creates an empty room of specified size, with specified door points.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 * @param doors
	 *            A java.awt.Point array of door co-ordinates. Acceptable points lie
	 *            in a range of (0, 0) to (width+2, height+2) to accommodate walls,
	 *            with (0, 0) as the top-left.
	 */
	public GUIRoom(String name, int width, int height, Point[] doors) {
		
		this.internalWidth = width;
		this.internalHeight = height;
		this.genTileArray();
		
		this.placeDoors(doors);
		
	}
	
	/**
	 * Creates an empty room of specified size, with specified door points, and
	 * places a player.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 * @param doors
	 *            A java.awt.Point array of door co-ordinates. Acceptable points lie
	 *            in a range of (0, 0) to (width+2, height+2) to accommodate walls,
	 *            with (0, 0) as the top-left.
	 */
	public GUIRoom(String name, int width, int height, Point[] doors, Point playerPosition) {
		
		this.internalWidth = width;
		this.internalHeight = height;
		
		this.placeEntity(playerPosition, new Player());
		this.genTileArray();
		
		this.placeDoors(doors);
		
	}
	
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
	
	/*
	 * PRIVATE METHODS
	 */
	
	/**
	 * Generates and appends a tile array to the instance. Makes the tile array 2
	 * units larger in both the x and y direction from the internal size to
	 * accommodate walls. Also automatically fills the edges with the correct wall
	 * tiles.
	 */
	private void genTileArray() {
		
		// Generate 2D arrays to fill...
		this.tileArray = new Tile[this.internalWidth + 2][this.internalHeight + 2];
		this.entityArray = new Entity[this.internalWidth + 2][this.internalHeight + 2];
		this.itemArray = new Item[this.internalWidth + 2][this.internalHeight + 2];
		
		// For each position...
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				/*
				 * Put the appropriate wall tiles along the edges.
				 */
				
				if (x == 0 && y == 0) { // Top Left
					
					this.tileArray[x][y] = new Wall_TL();
					
				} else if (x == this.internalWidth + 1 && y == 0) { // Top Right
					
					this.tileArray[x][y] = new Wall_TR();
					
				} else if (x == 0 && y == this.internalHeight + 1) { // Bottom Left
					
					this.tileArray[x][y] = new Wall_BL();
					
				} else if (x == this.internalWidth + 1 && y == this.internalHeight + 1) { // Bottom Right
					
					this.tileArray[x][y] = new Wall_BR();
					
				} else if (x == 0) { // Left Wall
					
					this.tileArray[x][y] = new Wall_L();
					
				} else if (x == this.internalWidth + 1){ //Right Wall
					
					this.tileArray[x][y] = new Wall_R();
					
				} else if (y == 0) { // Top Wall
					
					this.tileArray[x][y] = new Wall_T();
					
				} else if (y == this.internalHeight + 1) { //Bottom Wall
					
					this.tileArray[x][y] = new Wall_B();
					
				} else { // Floor Tiles
					
					this.tileArray[x][y] = new Floor();
					
				}
				
			}
			
		}
		
	}
	
	/*
	 * PUBLIC METHODS
	 */
	
	public Point checkForBattlePoint() {
		
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				if (this.entityArray[x][y] != null && this.entityArray[x][y].type.equals("ENEMY")) {
					Point enemyPoint = new Point(x, y);
					if (this.playerPoint.distance(enemyPoint) < 1.5) {
						return enemyPoint;
					}
				}
				
			}
		}
		
		return null;
		
	}
	
	/**
	 * Draws the room to the console
	 */
	public void draw_Tiles(GraphicsContext gc) {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				//Get the tile position
				int[] position = new int[] {x*64, y*64};
				
				// Print the tile
				this.tileArray[x][y].drawTile(gc, position);
			}
			
		}
		
	}
	
	/**
	 * Gets the tile at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The tile at position.
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
	 * message, if the ending tile cannot be moved to (walls).
	 * 
	 * @param toMove
	 *            Point of the entity that should be moved.
	 * @param moveTo
	 *            Point of the entity that will be moved to.
	 */
	public void moveEntity(Point toMove, Point moveTo) {
		
		// If the player can walk on the tile...
		if (this.tileAt(moveTo).CAN_WALK_ON) {
			
			// Move the entity
			this.placeEntity(moveTo, this.entityAt(toMove));
			this.placeEntity(toMove, null);
			
			if (this.entityAt(moveTo).type.equals("PLAYER")) {
				this.playerPoint = moveTo;
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
				
				if(pos.x == 0) { // Left Door
					
					this.setTile(pos, new Door("Left"));
					
				}else if(pos.x == this.internalWidth + 1) { // Right Door
					
					this.setTile(pos, new Door("Right"));
					
				}else if(pos.y == 0) { // Top Door
					
					this.setTile(pos, new Door("Top"));
					
				}else { // Bottom Door
					
					this.setTile(pos, new Door("Bottom"));
					
				}
			}
		}
	}//End of placeDoors
	
	/**
	 * Sets a position at a point in the entity array to a specific entity.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to change.
	 * @param entity
	 *            The entity to set the position to.
	 */
	public void placeEntity(Point position, Entity entity) {
		
		this.entityArray[position.x][position.y] = entity;
		
		if (entity != null && entity.type.equals("PLAYER")) {
			this.playerPoint = position;
		}
		
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
				temp += this.tileArray[x][y].FILE_CHAR + ",";
				
			}
			
			temp = temp.substring(0, temp.length() - 1);
			lines.add(temp);
			
		}
		
		// Write the line
		Utilities.writeFile(fileName, lines);
		
	}
	
	public void scatterEnemies() {
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				if (_random.nextInt(100) < this.enemySpawnChance) {
					Point point = new Point(x, y);
					this.placeEntity(point, new Enemy());
				}
				
			}
		}
		
	}
	
	public void scatterItems() {
		
		int numItems = 3, randomItemNumber;
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				randomItemNumber = _random.nextInt(numItems);
				Item selectedItem;
				
				switch(randomItemNumber) {
					
					case 0:
						selectedItem = new Fish();
						break;
						
					case 1:
						selectedItem = new Money();
						break;
						
					case 2:
						selectedItem = new Potion();
						break;
					
					default:
						selectedItem = new Item();
						break;
					
				}
				
				if (_random.nextInt(100) < selectedItem.SPAWN_CHANCE) {
					this.placeItem(new Point(x, y), selectedItem);
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