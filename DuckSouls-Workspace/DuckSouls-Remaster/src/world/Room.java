package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;
import javafx.scene.image.Image;
import tiles.Door;
import tiles.GeneralTile;
import tiles.Tile;
import tiles.Wall;
import utils.Utilities;

/**
 * This class represents a room object for DuckSouls. Each room has a 2D array
 * for tiles, and for items, a list of enemies, and a player.
 */
public class Room {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Random		_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private int					internalWidth;
	private int					internalHeight;
	private int					levelNum;
	private int					enemySpawnChance	= 1;
	
	private Tile[][]			tileArray;
	private Item[][]			itemArray;
	
	private Player				player;
	private ArrayList<Enemy>	enemyList			= new ArrayList<Enemy>();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a square room of set size with scattered items and enemies. Used for
	 * generating arcade rooms.
	 * 
	 * @param size
	 *            Width and height of the room.
	 * @param levelNum
	 *            The level number.
	 * @param enemySpawnChance
	 *            The spawn chance of enemies for a level. Must be from 0 to 100.
	 */
	public Room(int size, int levelNum, int enemySpawnChance) {
		
		this.internalWidth = size;
		this.internalHeight = size;
		this.levelNum = levelNum;
		this.enemySpawnChance = enemySpawnChance;
		
		this.genTileArray();
		this.scatterItems();
		this.scatterEnemies();
		
	}
	
	/**
	 * Creates a room with all set parameters. Used for reading story rooms.
	 * 
	 * @param width
	 *            Width of the room.
	 * @param height
	 *            Height of the room.
	 * @param tileArray
	 *            The 2D array of tiles for the room.
	 * @param itemArray
	 *            The 2D array of items for the room.
	 * @param player
	 *            The player for the room.
	 * @param enemyList
	 *            The list of enemies in the room.
	 * @param levelNum
	 *            The number of the level.
	 */
	public Room(int width, int height, Tile[][] tileArray, Item[][] itemArray, Player player,
			ArrayList<Enemy> enemyList, int levelNum) {
		
		this.internalWidth = width - 2;
		this.internalHeight = height - 2;
		this.levelNum = levelNum;
		this.enemySpawnChance = 0;
		
		this.tileArray = tileArray;
		this.itemArray = itemArray;
		this.enemyList = enemyList;
		
		if (player != null) {
			this.player = player;
		}
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns a 2D array of strings for printing the room in the text version of
	 * the game.
	 * 
	 * @return A 2D array of strings for printing the room in the text version of
	 *         the game.
	 */
	public String[][] getAllTextSprites() {
		
		String[][] textSprites = new String[this.internalHeight + 2][this.internalWidth + 2];
		
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				// Tiles
				textSprites[y][x] = this.tileArray[x][y].getStringRepr();
				
				// Items
				if (this.itemArray[x][y] != null) {
					textSprites[y][x] = this.itemArray[x][y].getStringRepr();
				}
				
			}
		}
		
		// Enemies
		for (Enemy enemy : this.enemyList) {
			Point enemyPos = enemy.getPosition();
			textSprites[enemyPos.y][enemyPos.x] = enemy.getStringRepr();
		}
		
		// Player
		Point playerPos = this.player.getPosition();
		textSprites[playerPos.y][playerPos.x] = this.player.getStringRepr();
		
		return textSprites;
		
	}
	
	/**
	 * Returns a 3D array of images for drawing the room in the GUI version of the
	 * game. Has the player in the middle of the screen always.<br>
	 * <br>
	 * Third Array Indexing:
	 * <ul>
	 * <li>0 --> Tiles
	 * <li>1 --> Items
	 * <li>2 --> Entities (Player > Enemy)
	 * </ul>
	 * 
	 * @return A 3D array of images for drawing the room in the GUI version of the
	 *         game.
	 */
	public Image[][][] getAllImageSprites() {
		
		Image[][][] images = new Image[9][9][3];
		int x = 0;
		int y = 0;
		
		// For each position on within size/2 of the player in the x direction
		for (int i = this.player.getPosition().x - (9 / 2); i <= this.player.getPosition().x + (9 / 2); i++) {
			
			// For each position on within size/2 of the player in the y direction
			for (int j = this.player.getPosition().y - (9 / 2); j <= this.player.getPosition().y + (9 / 2); j++) {
				
				// This position is off the map add an empty image
				if (i < 0 || i >= this.getInternalWidth() + 2) {
					images[x][y][0] = new Image("file:///" + Utilities.parentDir + "/Sprites/Tiles/Sewer/Empty.png");
				}
				
				else if (i >= 0 && i < this.getInternalWidth() + 2) {
					
					// This position is off the map add an empty image
					if (j < 0 || j >= this.getInternalHeight() + 2) {
						images[x][y][0] = new Image(
								"file:///" + Utilities.parentDir + "/Sprites/Tiles/Sewer/Empty.png");
						
						// If the position is on the map, add the tile image
					} else if (j >= 0 && j < this.getInternalHeight() + 2) {
						images[x][y][0] = this.tileArray[i][j].getImage();
						
						// For each item
						if (this.itemArray[i][j] != null) {
							images[x][y][1] = this.itemArray[i][j].getImage();
						}
						
						// For each character
						for (Enemy enemy : this.enemyList) {
							if (enemy.getPosition().x == i && enemy.getPosition().y == j) {
								images[x][y][2] = enemy.getImage();
							}
						}
						
						// If the player is at the current position, add it's iamge
						if (this.player.getPosition().x == i && this.player.getPosition().y == j) {
							images[x][y][2] = this.player.getImage();
						}
						
					}
				}
				
				y += 1;
			}
			y = 0;
			x += 1;
		}
		
		return images;
	}
	
	/**
	 * Generates and appends a tile array to the instance. Makes the tile array 2
	 * units larger in both the x and y direction from the internal size to
	 * accommodate walls. Also automatically fills the edges with the correct wall
	 * tiles.
	 */
	protected void genTileArray() {
		
		// Generate 2D arrays to fill...
		this.tileArray = new Tile[this.internalWidth + 2][this.internalHeight + 2];
		this.itemArray = new Item[this.internalWidth + 2][this.internalHeight + 2];
		
		// For each position...
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				/*
				 * Put the appropriate wall tiles along the edges and floor tiles in the middle.
				 */
				
				if (x == 0 && y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_TL; // Top Left
					
				} else if (x == this.internalWidth + 1 && y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_TR; // Top Right
					
				} else if (x == 0 && y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_BL; // Bottom Left
					
				} else if (x == this.internalWidth + 1 && y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_BR; // Bottom Right
					
				} else if (x == 0) {
					
					this.tileArray[x][y] = Wall.WALL_L; // Left
					
				} else if (y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_T; // Top
					
				} else if (x == this.internalWidth + 1) {
					
					this.tileArray[x][y] = Wall.WALL_R; // Right
					
				} else if (y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_B; // Bottom
					
				} else {
					
					this.tileArray[x][y] = GeneralTile.FLOOR; // Centre Tiles
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Scatters items at random throughout the level.
	 */
	public void scatterItems() {
		
		// Random number container
		int itemTypeNumber;
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				Point currentPosition = new Point(x, y);
				
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
						
						Consumable consumable = Consumable.values()[_random.nextInt(Consumable.values().length)];
						if (consumable.tryToSpawn()) {
							this.placeItem(currentPosition, consumable);
						}
						
						break;
					
					// Weapons
					case 3:
					case 4:
						
						Weapon weapon = Weapon.values()[_random.nextInt(Weapon.values().length)];
						if (weapon.tryToSpawn()) {
							this.placeItem(currentPosition, weapon);
						}
						
						break;
					
					// Armour
					case 5:
						
						Armour armour = Armour.values()[_random.nextInt(Armour.values().length)];
						if (armour.tryToSpawn()) {
							this.placeItem(currentPosition, armour);
						}
						
						break;
					
				}
				
			}
		}
		
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
					this.addEntity(new Enemy(new Point(x, y), this.levelNum));
					
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
	public void placeDoors(Point[] doors) {
		
		for (Point pos : doors) {
			
			if (pos != null) {
				
				if (pos.x == 0) {
					
					this.setTile(pos, Door.DOOR_L); // Left Door
					
				} else if (pos.x == this.internalWidth + 1) {
					
					this.setTile(pos, Door.DOOR_R); // Right Door
					
				} else if (pos.y == 0) {
					
					this.setTile(pos, Door.DOOR_T); // Top Door
					
				} else {
					
					this.setTile(pos, Door.DOOR_B); // Bottom Door
					
				}
			}
		}
	}
	
	/**
	 * Adds an entity to the room.
	 * 
	 * @param entity
	 *            The entity to add.
	 */
	public void addEntity(Entity entity) {
		
		if (entity instanceof Player) {
			this.player = (Player) entity;
		} else {
			this.enemyList.add((Enemy) entity);
		}
		
	}
	
	/**
	 * Removes an entity from the room.
	 * 
	 * @param entity
	 *            The entity to remove.
	 */
	public void removeEntity(Entity entity) {
		
		if (entity instanceof Player) {
			this.player = null;
		} else if (this.enemyList.contains(entity)) {
			this.enemyList.remove((Enemy) entity);
		}
		
	}
	
	/**
	 * Removes an enemy at a point in the entity list.
	 * 
	 * @param position
	 *            The position of the enemy to remove.
	 */
	public void removeEnemy(Point position) {
		
		try {
			for (Entity enemy : this.enemyList) {
				if (enemy.getPosition().equals(position)) {
					this.enemyList.remove(enemy);
				}
			}
		} catch (ConcurrentModificationException e) {
			// Do nothing, this error does not matter!
		}
	}
	
	/**
	 * Gets an enemy at a point in the entity list.
	 * 
	 * @param position
	 *            The position of the enemy to get.
	 */
	public Enemy enemyAt(Point position) {
		
		for (Enemy enemy : this.enemyList) {
			if (enemy.getPosition().equals(position)) {
				return enemy;
			}
		}
		return null;
		
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
	 * Removes an item at a point in the item array.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to remove.
	 */
	public void removeItem(Point position) {
		
		this.itemArray[position.x][position.y] = null;
		
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
	 * Returns the internal width of the room.
	 * 
	 * @return The internal width of the room.
	 */
	public int getInternalWidth() {
		
		return this.internalWidth;
		
	}
	
	/**
	 * Returns the internal height of the room.
	 * 
	 * @return The internal height of the room.
	 */
	public int getInternalHeight() {
		
		return this.internalHeight;
		
	}
	
	/**
	 * Returns the enemy list of the room.
	 * 
	 * @return The enemy list of the room.
	 */
	public ArrayList<Enemy> getEnemyList() {
		
		return this.enemyList;
		
	}
	
	/**
	 * Returns the player of the room.
	 * 
	 * @return The player of the room.
	 */
	public Player getPlayer() {
		
		return this.player;
		
	}
	
}
