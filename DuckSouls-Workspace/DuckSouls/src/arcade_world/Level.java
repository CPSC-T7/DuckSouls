package arcade_world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import battle.BattleWorldTest;
import entities.Player;
import game.Event;
import game.Event_type;
import game.GameWorld;
import items.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tiles.Stairs;
import utils.Orientation;
import utils.Utilities;

public class Level implements GameWorld{
	
	
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
	private int levelNum = 1;
	private Point playerPoint = new Point(3, 3);
	private Point roomPoint = new Point(0, 0);
	private Player player = new Player();
	private int difficultyPerLevel = 2;
	
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
	public Level() {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		this.enemySpawnChance = levelNum * difficultyPerLevel - difficultyPerLevel;
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(roomPoint);
		
		// Place the player
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(playerPoint, player);
		
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
	
	protected void genRoomArray(){

		this.roomArray = new Room[this.levelWidth][this.levelHeight];
			
			// For each room space...
			for (int y = 0; y < this.levelHeight; y++) {
				for (int x = 0; x < this.levelWidth; x++) {
					
					// Generate a square, random room
					this.roomArray[x][y] = new Room(this.getRoomSize(), enemySpawnChance);
					
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
			Room stairsRoom = this.roomArray[stairsRoomX][stairsRoomY];
			
			// Place the stairs, and remove anything of top of them
			stairsRoom.setTile(stairsPoint, new Stairs());
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
	
	public void moveRoom_Direction(Orientation direction) {
		
		// Get the player and their position
		Point newPlayerPoint = new Point(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		Player player = (Player) this.roomAt(this.getCurrentRoomPoint()).entityAt(newPlayerPoint);
		
		// Remove the player from the old room
		this.roomAt(this.getCurrentRoomPoint()).removeEntity(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		
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
		
		// Set the player direction
		this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).setOrientation(direction);
		
		
	}

	@Override
	public Event runTurn(String input) {
			// Do the action inputed by the user
			Event event = new Event(Event_type.NOEVENT);
			switch (input) {
				
				// Moving...
				
				case "W":
				case "NORTH":
					if (playerPoint.y == 0) {
						this.moveRoom_Direction(Orientation.NORTH);
					} else {
						
						if(!(Orientation.NORTH == this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getOrientation())) {
							this.roomAt(this.currentRoomPoint).entityAt(playerPoint).setOrientation(Orientation.NORTH);
						}
						else {
							this.roomAt(this.getCurrentRoomPoint()).moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y - 1));
						}
					}
					break;
				
				case "S":
				case "SOUTH":
					if (playerPoint.y == this.getRoomSize() + 1) {
						this.moveRoom_Direction(Orientation.SOUTH);
					} else {
						if(!(Orientation.SOUTH == this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getOrientation())) {
							this.roomAt(this.currentRoomPoint).entityAt(playerPoint).setOrientation(Orientation.SOUTH);
						}
						else {
							this.roomAt(this.getCurrentRoomPoint()).moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y + 1));
						}
					}
					break;
				
				case "D":
				case "EAST":
					if (playerPoint.x == this.getRoomSize() + 1) {
						this.moveRoom_Direction(Orientation.EAST);
					} else {
						if(!(Orientation.EAST == this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getOrientation())) {
							this.roomAt(this.currentRoomPoint).entityAt(playerPoint).setOrientation(Orientation.EAST);
						}
						else {
							this.roomAt(this.getCurrentRoomPoint()).moveEntity(playerPoint, new Point(playerPoint.x + 1, playerPoint.y));
						}
					}
					break;
				
				case "A":
				case "WEST":
					if (playerPoint.x == 0) {
						this.moveRoom_Direction(Orientation.WEST);
					} else {
						if(!(Orientation.WEST == this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getOrientation())) {
							this.roomAt(this.currentRoomPoint).entityAt(playerPoint).setOrientation(Orientation.WEST);
						}
						else {
							this.roomAt(this.getCurrentRoomPoint()).moveEntity(playerPoint, new Point(playerPoint.x - 1, playerPoint.y));
						}
					}
					break;
				
			}
			
			playerPoint = this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint();
			
			// Check for enemies
			if (this.roomAt(this.getCurrentRoomPoint()).isBattleReady()) {
				
				event = new Event(Event_type.BATTLE, player.getWeapon(), player.getArmour());
				
				this.roomAt(this.getCurrentRoomPoint()).setBattleReady(false);
				
			}
			
			if (this.roomAt(this.getCurrentRoomPoint()).tileAt(playerPoint) instanceof Stairs) {
				event = new Event(Event_type.NEXTWORLD, String.valueOf(this.levelNum + 1));
			}
			

			return event;
	}

	@Override
	public void nextWorld(String next) {
		this.levelNum = Integer.parseInt(next);
		this.enemySpawnChance = levelNum * difficultyPerLevel - difficultyPerLevel;
		this.genRoomArray();
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(playerPoint, player);
		
	}

	@Override
	public ArrayList<ArrayList<ArrayList<Image>>> getImages(int mapsize) {
		return this.roomAt(this.getCurrentRoomPoint()).getImages();
	}

	@Override
	public ArrayList<ArrayList<String>> getStrings() {
		return this.roomAt(this.getCurrentRoomPoint()).getStrings();
	}


	@Override
	public ArrayList<Item> getInventory() {
		return this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getInventory();
	}


	@Override
	public Item getPlayerWeapon() {
		return this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getWeapon();
	}


	@Override
	public Item getPlayerArmour() {
		return this.roomAt(this.currentRoomPoint).entityAt(playerPoint).getArmour();
	}
	
}
