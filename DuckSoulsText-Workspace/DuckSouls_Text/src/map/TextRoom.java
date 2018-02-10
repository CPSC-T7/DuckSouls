package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	private static Scanner _scanner = new Scanner(System.in);
	
	/**
	 * Temporary enumerator for tiles whilst tilea re in development.
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
		
		// Not-Walls
		
		PLAYER(" @ ", false),
		PATH(" . ", true),
		EMPTY("   ", true),
		FISH(" F ", true),
		CURRENCY(" $ ", true);
		
		private String	STR, FILE_CHAR;
		private boolean	CAN_WALK_ON;
		
		/**
		 * Creates a tile.
		 * 
		 * @param STR
		 *            The 3-character string used to print the tile.
		 * @param CAN_WALK_ON
		 *            Whether or not a player can walk on the tile.
		 */
		private Tile(String STR, boolean CAN_WALK_ON) {
			
			this.STR = STR;
			this.FILE_CHAR = Character.toString(this.STR.charAt(1)); // Middle char
			this.CAN_WALK_ON = CAN_WALK_ON;
			
		}
		
	}
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int	DEFAULT_WIDTH	= 8, DEFAULT_HEIGHT = 8;
	
	private int			internalWidth, internalHeight;
	private Tile[][]	tileArray;
	private Point		playerPosition;
	
	public String		roomName;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates an empty, doorless room of default size.
	 * 
	 * @param name
	 *            The name of the room. (Used for file i/o)
	 */
	public TextRoom(String name) {
		
		this.roomName = name;
		this.internalWidth = DEFAULT_WIDTH;
		this.internalHeight = DEFAULT_HEIGHT;
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
		
		this.internalWidth = DEFAULT_WIDTH;
		this.internalHeight = DEFAULT_HEIGHT;
		this.genTileArray();
		
		this.addDoors(doors);
		
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
		
		this.addDoors(doors);
		
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
		this.playerPosition = playerPosition;
		this.genTileArray();
		
		this.addDoors(doors);
		this.setTile(this.playerPosition, Tile.PLAYER);
		
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
						
						// Record where the player was placed
						if (tile == Tile.PLAYER) {
							this.playerPosition = new Point(x, y);
						}
						
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
		
		// Generate a 2D tile array to fill...
		this.tileArray = new Tile[this.internalWidth + 2][this.internalHeight + 2];
		
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
				
			}
			
		}
		
	}
	
	/**
	 * Takes an array of points and places a door at every point.
	 * 
	 * @param doors
	 *            The array of points to place doors at.
	 */
	private void addDoors(Point[] doors) {
		
		for (Point pos : doors) {
			
			this.setTile(pos, Tile.DOOR);
			
		}
		
	}
	
	/*
	 * PUBLIC METHODS
	 */
	
	/**
	 * Sets a tile at a point in the tile array to a specific character.
	 * 
	 * @param pos
	 *            A java.awt.Point specifying the tile to change.
	 * @param ch
	 *            The character to set the tile to.
	 */
	public void setTile(Point pos, Tile tile) {
		
		this.tileArray[pos.x][pos.y] = tile;
		
	}
	
	/**
	 * Draws the room to the console
	 */
	public void draw() {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				// Print the tile
				System.out.print(this.tileArray[x][y].STR);
				
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
	 * @return The tile at pos.
	 */
	/*
	 * TODO: Privacy leak.
	 */
	public Tile tileAt(Point pos) {
		
		return this.tileArray[pos.x][pos.y];
		
	}
	
	/**
	 * Moves a tile from one point to another, throwing a tantrum, I mean error, if
	 * the ending tile cannot be moved to (walls or player).
	 * 
	 * @param toMove
	 *            Point of the tile that should be moved.
	 * @param moveTo
	 *            Point of the tile that will be moved to.
	 */
	public void moveTile(Point toMove, Point moveTo) {
		
		// Placeholder for swapping tiles
		Tile temp = this.tileAt(toMove); // , landedOnTile = this.tileAt(moveTo);
		
		// If the player can walk on the tile...
		if (this.tileAt(moveTo).CAN_WALK_ON) {
			
			// Move the tile from one position to the other
			this.setTile(toMove, Tile.PATH); // Path tile indicates it has been stepped on
			this.setTile(moveTo, temp);
			
			// TODO: Add step-on methods/actions.
			// https://www.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html
			// Section 2.2
			
			// Keep track of if the player was moved
			if (temp.equals(Tile.PLAYER)) {
				this.playerPosition = moveTo;
			}
			
		} else {
			
			/*
			 * System.out.println("Cannot move tile " + this.tileAt(toMove).toString() +
			 * " from point " + toMove.toString() + " to point " + moveTo.toString() +
			 * ". There's a " + this.tileAt(moveTo).toString() + " there!");
			 */
			
			System.out.println("You can't walk there!");
			
			Utilities.waitMilliseconds(1000);
			
		}
		
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void moveLoop() {
		
		String input;
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		while (true) {
			
			// Draw the room and ask the user for input
			Utilities.clearConsole();
			this.draw();
			System.out.print("\nAction \t: ");
			input = _scanner.nextLine().toUpperCase();
			
			// Do the action inputed by the user
			switch (input) {
				
				// Moving...
				
				case "W":
				case "NORTH":
					this.moveTile(this.playerPosition, new Point(this.playerPosition.x, this.playerPosition.y - 1));
					break;
				
				case "S":
				case "SOUTH":
					this.moveTile(this.playerPosition, new Point(this.playerPosition.x, this.playerPosition.y + 1));
					break;
				
				case "A":
				case "WEST":
					this.moveTile(this.playerPosition, new Point(this.playerPosition.x - 1, this.playerPosition.y));
					break;
				
				case "D":
				case "EAST":
					this.moveTile(this.playerPosition, new Point(this.playerPosition.x + 1, this.playerPosition.y));
					break;
				
				// Room commands...
				
				case "SAVE":
					this.saveToTextFile();
					break;
				
				// Default
				
				default:
					System.out.println("...What?");
					Utilities.waitMilliseconds(500);
					break;
				
			}
			
			System.out.println("\n\n");
			
		}
		
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
	
	/**
	 * Cleans up the static resources
	 */
	public static void cleanup() {
		
		_scanner.close();
	
	}
	
}
