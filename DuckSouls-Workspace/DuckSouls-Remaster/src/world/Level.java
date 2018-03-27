package world;

import java.awt.Point;
import java.util.Random;

import arcade_world.Room;
import entities.Player;
import tiles.GeneralTile;
import tiles.Stairs;

public class Level {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Random	_random							= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_LEVEL_SIZE				= 3;
	private final int		DEFAULT_ROOM_SIZE				= 7;
	private final int		DEFAULT_DIFFICULTY_PER_LEVEL	= 5;
	
	private Room[][]		roomArray;
	
	private Point			currentRoomPoint;
	private Player			player;
	private int				levelWidth, levelHeight;
	private int				roomSize;
	private int				enemySpawnChance;
	private int				levelNum;
	private int				difficultyPerLevel;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Level(int levelNum) {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		this.levelNum = levelNum;
		this.enemySpawnChance = levelNum * DEFAULT_DIFFICULTY_PER_LEVEL - DEFAULT_DIFFICULTY_PER_LEVEL;
		
		this.genRoomArray();
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	private void genRoomArray() {
		
		this.roomArray = new Room[this.levelWidth][this.levelHeight];
		
		// For each room space...
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				// Generate a square, random room
				this.roomArray[x][y] = new Room(this.roomSize, this.levelNum, this.enemySpawnChance);
				
			}
		}
		
		// Link all the rooms with doors
		this.placeAllConnectingDoors();
		
		// Randomly pick a room and tile for the stairs to be
		int stairsRoomX = _random.nextInt(levelWidth);
		int stairsRoomY = _random.nextInt(levelHeight);
		int stairsTileX = _random.nextInt(this.roomSize) + 1;
		int stairsTileY = _random.nextInt(this.roomSize) + 1;
		Point stairsPoint = new Point(stairsTileX, stairsTileY);
		Room stairsRoom = this.roomArray[stairsRoomX][stairsRoomY];
		
		// Place the stairs, and remove anything of top of them
		stairsRoom.setTile(stairsPoint, GeneralTile.STAIRS);
		stairsRoom.removeEnemy(stairsPoint);
		stairsRoom.removeItem(stairsPoint);
	}
	
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
						doors[0] = new Point((this.roomSize + 2) / 2, 0);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Bottom
					if (this.roomArray[x][y + 1] != null) {
						doors[1] = new Point((this.roomSize + 2) / 2, (this.roomSize + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Left
					if (this.roomArray[x - 1][y] != null) {
						doors[2] = new Point(0, (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try { // Check on the Right
					if (this.roomArray[x + 1][y] != null) {
						doors[3] = new Point((this.roomSize + 1), (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				// Place the doors in the room
				this.roomArray[x][y].placeDoors(doors);
				
			}
		}
		
	}
	
}
