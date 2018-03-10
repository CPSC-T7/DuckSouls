package levels;

import java.awt.Point;

import entities.*;
import rooms.TextRoom;

import utils.Orientation;

/**
 * This class represents a text level; A 2D grid of text rooms.
 * 
 * @author Matthew Allwright
 * @version 1.6
 */
public class TextLevel extends Level {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final boolean	IS_GUI	= false;
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private char[][]			minimapArray;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public TextLevel(Player player, Point playerPosition, Point roomPoint, int enemySpawnChance) {
		
		super(player, playerPosition, roomPoint, enemySpawnChance);
		
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
	protected void genRoomArray() {
		
		this.roomArray = new TextRoom[this.levelWidth][this.levelHeight];
		
		// For each room space...
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				// Generate a square, random room
				this.roomArray[x][y] = new TextRoom(this.getRoomSize(), enemySpawnChance);
				
			}
		}
		
		super.genRoomArray_STUB(IS_GUI);
		
	}
	
	/**
	 * Returns the room at a certain point in the room array.
	 * 
	 * @param position
	 *            The position of the room to get.
	 * @return The room at that position.
	 */
	public TextRoom roomAt(Point position) {
		return (TextRoom) this.roomArray[position.x][position.y];
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
	
}
