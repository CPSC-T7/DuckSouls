package rooms;

import java.awt.Point;
import java.util.Random;

import entities.Entity;
import items.Item;
import tiles.Floor;
import tiles.Tile;
import tiles.Wall_B;
import tiles.Wall_BL;
import tiles.Wall_BR;
import tiles.Wall_L;
import tiles.Wall_R;
import tiles.Wall_T;
import tiles.Wall_TL;
import tiles.Wall_TR;

public abstract class Room {
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */

	protected abstract void scatterItems();
	protected abstract void scatterEnemies();
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	protected static Random	_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected int			internalWidth;
	protected int			internalHeight;
	protected int			enemySpawnChance	= 1;
	protected boolean		IS_GUI;
	
	protected Tile[][]		tileArray;
	protected Item[][]		itemArray;
	protected Entity[][]	entityArray;
	
	protected Point			playerPoint;
	
	protected String		roomName;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a square room of set size with scattered items and enemies. <br>
	 * This is the constructor used by TextLevel to create rooms.
	 * 
	 * @param size
	 *            Width and height of the room.
	 * @param enemySpawnChance
	 *            The spawn chance of enemies for a level. Must be from 0 to 100.
	 */
	public Room(boolean isGUI, int size, int enemySpawnChance) {
		
		this.IS_GUI = isGUI;
		this.internalWidth = size;
		this.internalHeight = size;
		this.enemySpawnChance = enemySpawnChance;
		
		this.genTileArray();
		this.scatterItems();
		this.scatterEnemies();
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Generates and appends a tile array to the instance. Makes the tile array 2
	 * units larger in both the x and y direction from the internal size to
	 * accommodate walls. Also automatically fills the edges with the correct wall
	 * tiles.
	 */
	protected void genTileArray() {
		
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
					
					this.tileArray[x][y] = new Wall_TL(IS_GUI);
					
				} else if (x == this.internalWidth + 1 && y == 0) { // Top Right
					
					this.tileArray[x][y] = new Wall_TR(IS_GUI);
					
				} else if (x == 0 && y == this.internalHeight + 1) { // Bottom Left
					
					this.tileArray[x][y] = new Wall_BL(IS_GUI);
					
				} else if (x == this.internalWidth + 1 && y == this.internalHeight + 1) { // Bottom Right
					
					this.tileArray[x][y] = new Wall_BR(IS_GUI);
					
				} else if (x == 0) { // Left
					
					this.tileArray[x][y] = new Wall_L(IS_GUI);
					
				} else if (y == 0) { // Top
					
					this.tileArray[x][y] = new Wall_T(IS_GUI);
					
				} else if (x == this.internalWidth + 1) { // Right
					
					this.tileArray[x][y] = new Wall_R(IS_GUI);
					
				} else if (y == this.internalHeight + 1) { // Bottom
					
					this.tileArray[x][y] = new Wall_B(IS_GUI);
					
				} else { // Centre Tiles
					
					this.tileArray[x][y] = new Floor(IS_GUI);
					
				}
				
			}
			
		}
		
	}

	public Point getPlayerPoint() {
		
		return playerPoint;
		
	}

	public void setPlayerPoint(Point playerPoint) {
		
		this.playerPoint = playerPoint;
		
	}
	
}
