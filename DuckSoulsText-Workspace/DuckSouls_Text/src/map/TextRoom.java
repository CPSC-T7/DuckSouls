package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import utils.Utilities;

//TODO: Fill in JavaDocs

/**
 * Text-based room class.
 * 
 * @author Matthew Allwright
 * @requires java.awt.Point
 * @version 1.6.2
 */
public class TextRoom {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	/**
	 * Temporary enumerator for tiles whilst tiles are in development.
	 * 
	 * @author Matthew Allwright
	 */
	public static enum Tile {
		
		// Doors
		
		DOOR(" D ", true),
		
		// Walls
		
		WALL_H("═══", false),
		WALL_V(" ║ ", false),
		WALL_TL(" ╔═", false),
		WALL_TR("═╗ ", false),
		WALL_BL(" ╚═", false),
		WALL_BR("═╝ ", false),
		
		// Floors
		
		PATH(" . ", true),
		EMPTY("   ", true);
		
		private String	STRING_REPR, FILE_CHAR;
		private boolean	CAN_WALK_ON;
		
		/**
		 * Creates a tile.
		 * 
		 * @param STRING_REPR
		 *            The 3-character string used to print the tile.
		 * @param CAN_WALK_ON
		 *            Whether or not a player can walk on the tile.
		 */
		private Tile(String STRING_REPR, boolean CAN_WALK_ON) {
			
			this.STRING_REPR = STRING_REPR;
			this.FILE_CHAR = Character.toString(this.STRING_REPR.charAt(1)); // Middle char
			this.CAN_WALK_ON = CAN_WALK_ON;
			
		}
		
	}
	
	/**
	 * Temporary enumerator for entities whilst tiles are in development.
	 * 
	 * @author Matthew Allwright
	 */
	public static enum Entity {
		
		PLAYER(" @ "),
		ENEMY(" E ");
		
		private String	STRING_REPR;
		
		public Point	POS;
		
		/**
		 * Creates an entity.
		 * 
		 * @param STRING_REPR
		 *            The 3-character string used to print the tile.
		 */
		private Entity(String STRING_REPR) {
			
			this.STRING_REPR = STRING_REPR;
			
		}
		
	}
	
	/**
	 * Temporary enumerator for items whilst tiles are in development.
	 * 
	 * @author Matthew Allwright
	 */
	public static enum Item {
		
		FISH(" F ", 15),
		MONEY(" $ ", 15),
		POTION(" P ", 15);
		
		private String	STRING_REPR;
		private int		SPAWN_CHANCE;	// 0-99
		
		/**
		 * Creates an entity.
		 * 
		 * @param STRING_REPR
		 *            The 3-character string used to print the tile.
		 */
		private Item(String STRING_REPR, int SPAWN_CHANCE) {
			
			this.STRING_REPR = STRING_REPR;
			this.SPAWN_CHANCE = SPAWN_CHANCE;
			
		}
		
	}
	
	public static Random	_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_ROOM_SIZE	= 5;
	
	private int				internalWidth, internalHeight;
	
	private String[][]		spriteArray;
	private Tile[][]		tileArray;
	private Item[][]		itemArray;
	private Entity[][]		entityArray;
	
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
	public TextRoom(int size) {
		
		this.internalWidth = size;
		this.internalHeight = size;
		this.genTileArray();
		this.scatterItems();
		
	}
	
	/**
	 * Creates an empty room of set size.
	 * 
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 */
	public TextRoom(int width, int height) {
		
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
	public TextRoom(String name) {
		
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
	public TextRoom(String name, int width, int height) {
		
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
	public TextRoom(String name, Point[] doors) {
		
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
	public TextRoom(String name, int width, int height, Point[] doors) {
		
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
	public TextRoom(String name, int width, int height, Point[] doors, Point playerPosition) {
		
		this.internalWidth = width;
		this.internalHeight = height;
		
		this.placeEntity(playerPosition, Entity.PLAYER);
		this.genTileArray();
		
		this.placeDoors(doors);
		
	}
	
	/**
	 * Creates a room from a specified file.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 * @param fileName
	 *            The file containing the data for the room.
	 */
	public TextRoom(String name, String fileName) {
		
		// Read the file
		String[] lines = Utilities.readLines(fileName);
		
		// Width of the room is the number of characters in a row, minus the walls
		this.internalWidth = lines[0].split(",").length - 2;
		
		// Height of the room is the number of lines, minus the walls
		this.internalHeight = lines.length - 2;
		
		// Generate an array for reading the file
		String[][] textTileArray = new String[this.internalWidth + 2][this.internalHeight + 2];
		
		// Generate the room's tile array
		this.genTileArray();
		
		// Split all the lines of the file by commas to get the tile strings
		for (int i = 0; i < this.internalHeight + 2; i++) {
			
			textTileArray[i] = lines[i].split(",");
			
		}
		
		// For each position in the room...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				// For each type of tile possible...
				for (Tile tile : Tile.values()) {
					
					// If the file says there should be said tile there...
					if (textTileArray[x][y].equals(tile.FILE_CHAR)) {
						
						// Place said tile
						this.tileArray[x][y] = tile;
						
						// TODO: Delete?
						// Record where the player was placed
						// if (tile == Tile.PLAYER) {
						// this.playerPosition = new Point(x, y);
						// }
						
						// Stop searching through tile types
						break;
						
					}
					
				}
				
			}
			
		}
		
	}
	
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
		this.spriteArray = new String[this.internalWidth + 2][this.internalHeight + 2];
		this.entityArray = new Entity[this.internalWidth + 2][this.internalHeight + 2];
		this.itemArray = new Item[this.internalWidth + 2][this.internalHeight + 2];
		
		// For each position...
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				/*
				 * Put the appropriate wall tiles along the edges.
				 */
				
				if (x == 0 && y == 0) { // Top Left
					
					this.tileArray[x][y] = Tile.WALL_TL;
					
				} else if (x == this.internalWidth + 1 && y == 0) { // Top Right
					
					this.tileArray[x][y] = Tile.WALL_TR;
					
				} else if (x == 0 && y == this.internalHeight + 1) { // Bottom Left
					
					this.tileArray[x][y] = Tile.WALL_BL;
					
				} else if (x == this.internalWidth + 1 && y == this.internalHeight + 1) { // Bottom Right
					
					this.tileArray[x][y] = Tile.WALL_BR;
					
				} else if (x == 0 || x == this.internalWidth + 1) { // Left & Right Walls
					
					this.tileArray[x][y] = Tile.WALL_V;
					
				} else if (y == 0 || y == this.internalHeight + 1) { // Top & Bottom Walls
					
					this.tileArray[x][y] = Tile.WALL_H;
					
				} else { // Centre Tiles
					
					this.tileArray[x][y] = Tile.EMPTY;
					
				}
				
				this.spriteArray[x][y] = this.tileArray[x][y].STRING_REPR;
				
			}
			
		}
		
	}
	
	/*
	 * PUBLIC METHODS
	 */
	
	/**
	 * Draws the room to the console
	 */
	public void draw() {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				if (this.entityArray[x][y] != null) {
					
					// Print the entity
					System.out.print(this.entityArray[x][y].STRING_REPR);
					
				} else if (this.itemArray[x][y] != null) {
					
					// Print the item
					System.out.print(this.itemArray[x][y].STRING_REPR);
					
				} else {
					
					// Print the tile
					System.out.print(this.spriteArray[x][y]);
					
				}
				
			}
			
			// Row has been printed, wrap the line
			System.out.println();
			
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
			
			if (this.entityAt(moveTo) == Entity.PLAYER) {
				Entity.PLAYER.POS = moveTo;
			}
			
			// Path tile indicates it has been stepped on
			this.setTile(toMove, Tile.PATH);
			
			// Deal with the stepped on item
			if (this.itemAt(moveTo) != null) {
				
				// TODO: Add step-on methods/actions.
				// https://www.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html
				// Section 2.2
				
				this.placeItem(moveTo, null);
				
			}
			
			// Path tile indicates it has been stepped on
			this.setTile(moveTo, Tile.PATH);
			
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
				this.setTile(pos, Tile.DOOR);
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
		
		this.entityArray[position.x][position.y] = entity;
		
		if (entity == Entity.PLAYER) {
			entity.POS = position;
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

	public void scatterItems() {
		
		int numItems = Item.values().length, randomItemNumber;
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				randomItemNumber = _random.nextInt(numItems);
				Item selectedItem = Item.values()[randomItemNumber];
				
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
		this.spriteArray[position.x][position.y] = tile.STRING_REPR;
		
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
