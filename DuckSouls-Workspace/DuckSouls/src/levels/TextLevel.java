package levels;

import java.awt.Point;
import java.util.Scanner;

import entities.*;
import rooms.TextRoom;
import tiles.Stairs;

import java.util.Random;

import utils.Orientation;

/**
 * This class represents a text level; A 2D grid of text rooms.
 * 
 * @author Matthew Allwright
 * @version 1.6
 */
public class TextLevel {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Scanner	_scanner			= new Scanner(System.in);
	private static Random	_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_LEVEL_SIZE	= 3;
	private final int		DEFAULT_ROOM_SIZE	= 5;
	
	private TextRoom[][]	roomArray;
	private char[][]		minimapArray;
	
	private Point			currentRoomPoint;
	private int				levelWidth, levelHeight;
	private int				roomSize;
	private int				enemySpawnChance;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a text level with all default parameters. This will result in a room
	 * matrix of 3x3, with each room being 5x5 on the inside. The top left room is
	 * set as the current room, and a new player is placed in the middle of the
	 * room.
	 */
	public TextLevel() {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(new Point(0, 0));
		
		// Place the player
		this.roomAt(this.getCurrentRoomPoint())
				.placeEntity(new Point(this.getRoomSize() / 2 + 1, this.getRoomSize() / 2 + 1), new Player());
		
		this.genMinimapArray();
		
	}
	
	/**
	 * Creates a text level with some default parameters, but specific player
	 * position. This will result in a room matrix of 3x3, with each room being 5x5
	 * on the inside. The player object, position, and current room point are all
	 * defined with arguments.
	 * 
	 * @param player
	 *            The player object for the game.
	 * @param playerPosition
	 *            A point defining the position of the player in the room.
	 * @param roomPoint
	 *            A point defining the room the player is placed in.
	 * @param enemySpawnChance
	 *            The spawn chance of enemies for a level. Must be from 0 to 100.
	 */
	public TextLevel(Player player, Point playerPosition, Point roomPoint, int enemySpawnChance) {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		this.enemySpawnChance = enemySpawnChance;
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(roomPoint);
		
		// Place the player
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(playerPosition, player);
		
		this.genMinimapArray();
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Draws the text minimap of the level. Unvisited rooms are filled with ' ',
	 * visited rooms are filled with '.', and the player's room is filled with '@'.
	 * <br>
	 * Looks Like: <br>
	 * ┌─┬─┐<br>
	 * │. @│<br>
	 * ├ ┼ ┤<br>
	 * │ │<br>
	 * └─┴─┘<br>
	 * (Aligns with monospace font)
	 */
	public void drawMinimap() {
		
		// Width and height of the minimap text.
		// This accommodates a text border around each room.
		int yMax = 2 * this.levelHeight + 1, xMax = 2 * this.levelWidth + 1;
		
		// For each character...
		for (int y = 0; y < yMax; y++) {
			for (int x = 0; x < xMax; x++) {
				
				if (y == 0) { // Top Row
					
					if (x == 0) {
						System.out.print("┌"); // Left
					} else if (x == xMax - 1) {
						System.out.print("┐"); // Right
					} else if (x % 2 == 0) {
						System.out.print("┬"); // Above Dividers
					} else {
						System.out.print("─"); // Above Rooms
					}
					
				} else if (y == yMax - 1) { // Bottom row
					
					if (x == 0) {
						System.out.print("└"); // Left
					} else if (x == xMax - 1) {
						System.out.print("┘"); // Right
					} else if (x % 2 == 0) {
						System.out.print("┴"); // Below Dividers
					} else {
						System.out.print("─"); // Below Rooms
					}
					
				} else if (y % 2 == 0) { // Rows Between Rooms
					
					if (x == 0) {
						System.out.print("├"); // Left
					} else if (x == xMax - 1) {
						System.out.print("┤"); // Right
					} else if (x % 2 == 0) {
						System.out.print("┼"); // Above / Below Dividers
					} else {
						System.out.print(" "); // Above / Below Rooms
					}
					
				} else { // Rows With Rooms
					
					if (x == 0) {
						System.out.print("│"); // Left
					} else if (x == xMax - 1) {
						System.out.print("│"); // Right
					} else if (x % 2 == 0) {
						System.out.print(" "); // Between Rooms
					} else {
						System.out.print(this.minimapArray[(x - 1) / 2][(y - 1) / 2]); // The Room's Character
					}
					
				}
				
			}
			
			// Row has been printed, wrap the line
			System.out.println();
		}
		
	}
	
	/**
	 * Generate's a level's minimap array. This array stores a character for each
	 * room, indicating the room's status. Unvisited rooms are filled with ' ',
	 * visited rooms are filled with '.', and the player's room is filled with '@'.
	 */
	private void genMinimapArray() {
		
		this.minimapArray = new char[this.levelWidth][this.levelHeight];
		
		// For each position...
		for (int x = 0; x < this.levelWidth; x++) {
			for (int y = 0; y < this.levelHeight; y++) {
				
				// If the room is the first room the player is in...
				if (x == this.getCurrentRoomPoint().x && y == this.getCurrentRoomPoint().y) {
					
					this.minimapArray[x][y] = '@';
					
				} else { // If the room hasn't been visited yet...
					
					this.minimapArray[x][y] = ' ';
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Generates an array of text rooms for the level to use.
	 */
	private void genRoomArray() {
		
		this.roomArray = new TextRoom[this.levelWidth][this.levelHeight];
		
		// For each room space...
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				// Generate a square, random room
				this.roomArray[x][y] = new TextRoom(this.getRoomSize(), enemySpawnChance);
				
			}
		}
		
		// Link all the rooms with doors
		this.placeAllConnectingDoors();
		
		// Randomly pick a room and tile for the stairs to be
		int stairsRoomX = _random.nextInt(levelWidth);
		int stairsRoomY = _random.nextInt(levelHeight);
		int stairsTileX = _random.nextInt(this.getRoomSize()) + 1;
		int stairsTileY = _random.nextInt(this.getRoomSize()) + 1;
		Point stairsPoint = new Point(stairsTileX, stairsTileY);
		TextRoom stairsRoom = this.roomArray[stairsRoomX][stairsRoomY];
		
		// Place the stairs, and remove anything of top of them
		stairsRoom.setTile(stairsPoint, new Stairs(false));
		stairsRoom.removeEntity(stairsPoint);
		stairsRoom.removeItem(stairsPoint);
		
	}
	
	/**
	 * Places doors in the middle of each room-dividing wall in the room array.
	 */
	private void placeAllConnectingDoors() {
		
		// Array container
		Point[] doors;
		
		// For each room...
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				// Create an array with room for a point on each wall
				doors = new Point[4];
				
				/*
				 * For each wall... See if there is a room in that direction. If so, mark a
				 * point in the door array where a door should be. (Middle of the wall). Try /
				 * Catches are just to prevent errors from reaching out of the array for edge
				 * rooms
				 */
				
				try { // Check on the Top
					if (this.roomArray[x][y - 1] != null) {
						doors[0] = new Point((this.getRoomSize() + 2) / 2, 0);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Bottom
					if (this.roomArray[x][y + 1] != null) {
						doors[1] = new Point((this.getRoomSize() + 2) / 2, (this.getRoomSize() + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Left
					if (this.roomArray[x - 1][y] != null) {
						doors[2] = new Point(0, (this.getRoomSize() + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Right
					if (this.roomArray[x + 1][y] != null) {
						doors[3] = new Point((this.getRoomSize() + 1), (this.getRoomSize() + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				// Place the doors in the room
				this.roomArray[x][y].placeDoors(doors);
				
			}
		}
		
	}
	
	/**
	 * Returns the room at a certain point in the room array.
	 * 
	 * @param position
	 *            The position of the room to get.
	 * @return The room at that position.
	 */
	public TextRoom roomAt(Point position) {
		return this.roomArray[position.x][position.y];
	}
	
	/**
	 * Cleans up the static resources
	 */
	public static void cleanup() {
		
		_scanner.close();
		
	}
	
	/**
	 * Moves the player and current room in a certain direction.
	 * 
	 * @param direction
	 *            An Orientation designating the direction to move in the room
	 *            array.
	 */
	public void moveRoom_Direction(Orientation direction) {
		
		// Get the player and their position
		Point newPlayerPoint = new Point(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		Player player = (Player) this.roomAt(this.getCurrentRoomPoint()).entityAt(newPlayerPoint);
		
		// Remove the player from the old room, and set the room's minimap character
		this.roomAt(this.getCurrentRoomPoint()).removeEntity(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		this.minimapArray[this.getCurrentRoomPoint().x][this.getCurrentRoomPoint().y] = '.';
		
		// Depending on the direction...
		switch (direction) {
			
			case NORTH:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y - 1));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.y = this.getRoomSize() + 1;
				break;
			
			case SOUTH:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y + 1));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.y = 0;
				break;
			
			case WEST:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x - 1, this.getCurrentRoomPoint().y));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.x = this.getRoomSize() + 1;
				break;
			
			case EAST:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x + 1, this.getCurrentRoomPoint().y));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.x = 0;
				break;
			
		}
		
		// Place the player at the new position in the new room
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(newPlayerPoint, player);
		// Update the room's minimap character
		this.minimapArray[this.getCurrentRoomPoint().x][this.getCurrentRoomPoint().y] = '@';
		
	}
	
	/**
	 * Returns the point of the current room.
	 * 
	 * @return The point of the current room.
	 */
	public Point getCurrentRoomPoint() {
		
		return currentRoomPoint;
		
	}
	
	/**
	 * Sets the point of the current room.
	 * 
	 * @param currentRoomPoint
	 *            The point of the new current room.
	 */
	public void setCurrentRoomPoint(Point currentRoomPoint) {
		
		this.currentRoomPoint = currentRoomPoint;
		
	}
	
	/**
	 * Returns the size of all the rooms.
	 * 
	 * @return The size of all the rooms.
	 */
	public int getRoomSize() {
		
		return roomSize;
		
	}
	
}
