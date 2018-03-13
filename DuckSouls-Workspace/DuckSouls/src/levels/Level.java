package levels;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

import entities.Player;
import rooms.Room;
import tiles.Stairs;

public abstract class Level {
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	public abstract Room roomAt(Point position);
	protected abstract void genRoomArray();
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	protected static Random		_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected final int			DEFAULT_LEVEL_SIZE	= 3;
	protected final int			DEFAULT_ROOM_SIZE	= 5;
	
	protected Room[][]			roomArray;
	
	protected Point				currentRoomPoint;
	protected int				levelWidth, levelHeight;
	protected int				roomSize;
	protected int				enemySpawnChance;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
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
	public Level(Player player, Point playerPosition, Point roomPoint, int enemySpawnChance) {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		this.enemySpawnChance = enemySpawnChance;
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(roomPoint);
		
		// Place the player
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(playerPosition, player);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Places doors in the middle of each room-dividing wall in the room array.
	 */
	protected void placeAllConnectingDoors() {
		
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
	
	protected void genRoomArray_STUB(boolean isGUI){

		// Link all the rooms with doors
		this.placeAllConnectingDoors();
		
		// Randomly pick a room and tile for the stairs to be
		int stairsRoomX = _random.nextInt(levelWidth);
		int stairsRoomY = _random.nextInt(levelHeight);
		int stairsTileX = _random.nextInt(this.getRoomSize()) + 1;
		int stairsTileY = _random.nextInt(this.getRoomSize()) + 1;
		Point stairsPoint = new Point(stairsTileX, stairsTileY);
		Room stairsRoom = this.roomArray[stairsRoomX][stairsRoomY];
		
		// Place the stairs, and remove anything of top of them
		stairsRoom.setTile(stairsPoint, new Stairs(isGUI));
		stairsRoom.removeEntity(stairsPoint);
		stairsRoom.removeItem(stairsPoint);
		
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
