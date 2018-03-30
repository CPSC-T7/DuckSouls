package world;

import java.awt.Point;
import java.util.Random;

import entities.Enemy;
import entities.Player;
import javafx.scene.image.Image;
import tests.PositionTests;
import tiles.Door;
import tiles.GeneralTile;
import utils.GameEvent;
import utils.GameEventQue;
import utils.Orientation;

public class Level {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Random		_random					= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final int	DEFAULT_LEVEL_SIZE		= 3;
	private static final int	DEFAULT_ROOM_SIZE		= 7;
	private static final int	DIFFICULTY_PER_LEVEL	= 5;
	
	private Room[][]			roomArray;
	
	public Room					currentRoom;
	private Point				currentRoomPoint;
	private Player				player;
	private int					levelWidth, levelHeight;
	private int					roomSize;
	private int					enemySpawnChance;
	private int					levelNum;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Level(int levelNum, Player player, Point currentRoomPoint) {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		
		this.levelNum = levelNum;
		this.enemySpawnChance = levelNum * DIFFICULTY_PER_LEVEL - DIFFICULTY_PER_LEVEL;
		
		this.genRoomArray();
		
		this.player = player;
		this.currentRoomPoint = currentRoomPoint;
		this.currentRoom = this.roomAt(this.currentRoomPoint);
		this.currentRoom.addEntity(this.player);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public String[][] getCurrentTextSprites() {
		return this.currentRoom.getAllTextSprites();
	}
	
	/**
	 * IMAGES! <br>
	 * <br>
	 * Third Array Indexing:
	 * <ul>
	 * <li>0 --> Tiles
	 * <li>1 --> Items
	 * <li>2 --> Entities (Player > Enemy)
	 * </ul>
	 * 
	 * @return
	 */
	public Image[][][] getCurrentImageSprites() {
		return this.currentRoom.getAllImageSprites();
	}
	
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
	
	/**
	 * Moves the player to a different room in the room array.
	 * 
	 * @param direction
	 *            The direction in the room array to move.
	 */
	public void moveRoom_Direction(Orientation direction) {
		
		Point newPlayerPoint = new Point(this.player.getPosition());
		
		// Remove the player from the old room
		this.currentRoom.removeEntity(this.player);
		
		// Depending on the direction...
		switch (direction) {
			
			case NORTH:
				newPlayerPoint.y = this.roomSize + 1;
				break;
			
			case SOUTH:
				newPlayerPoint.y = 0;
				break;
			
			case EAST:
				newPlayerPoint.x = 0;
				break;
			
			case WEST:
				newPlayerPoint.x = this.roomSize + 1;
				break;
			
		}
		
		this.currentRoomPoint = Orientation.pointAtDirection(this.currentRoomPoint, direction);
		
		// Place the player at the new position in the new room
		this.player.setPosition(newPlayerPoint);
		this.player.setOrientation(direction);
		this.currentRoom.addEntity(this.player);
		
	}
	
	public void movePlayer(Orientation direction) {
		
		Point newPlayerPoint = Orientation.pointAtDirection(this.player.getPosition(), direction);
		this.player.setOrientation(direction);
		
		if (!(this.currentRoom.tileAt(this.player.getPosition()) instanceof Door)) {
			PositionTests.testPointInRoom(this.currentRoom, newPlayerPoint);
		}
		
		switch (direction) {
			
			case NORTH:
				if (newPlayerPoint.y == -1) {
					this.moveRoom_Direction(direction);
				}
				break;
			
			case SOUTH:
				if (newPlayerPoint.y == this.currentRoom.getInternalHeight() + 2) {
					this.moveRoom_Direction(direction);
				}
				break;
			
			case EAST:
				if (newPlayerPoint.x == -1) {
					this.moveRoom_Direction(direction);
				}
				break;
			
			case WEST:
				if (newPlayerPoint.x == this.currentRoom.getInternalWidth() + 2) {
					this.moveRoom_Direction(direction);
				}
				break;
			
		}
		
		if (this.currentRoom.tileAt(newPlayerPoint).getCanWalkOn()) {
			this.player.setPosition(newPlayerPoint);
		}
		
		if (this.currentRoom.itemAt(this.player.getPosition()) != null) {
			this.player.pickupItem(this.currentRoom.itemAt(this.player.getPosition()));
			this.currentRoom.removeItem(this.player.getPosition());
		}
		
		for (Enemy enemy : this.currentRoom.getEnemyList()) {
			if (enemy.getPosition().equals(this.player.getPosition())) {
				GameEventQue.addEvent(GameEvent.BATTLE);
			}
		}
		
		if (this.currentRoom.tileAt(this.player.getPosition()) == GeneralTile.STAIRS) {
			GameEventQue.addEvent(GameEvent.LEVEL_CHANGE);
		}
		
	}
	
	/**
	 * Returns the room at a certain point in the room array.
	 * 
	 * @param position
	 *            The position of the room to get.
	 * @return The room at that position.
	 */
	public Room roomAt(Point position) {
		return (Room) this.roomArray[position.x][position.y];
	}
	
	public Point getCurrentRoomPoint() {
		return new Point(this.currentRoomPoint);
	}
	
}
