package rooms;

import java.awt.Point;
import java.util.Random;

import entities.Entity;
import items.Item;
import tiles.Tile;

public abstract class Room {
	
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
	
	protected Tile[][]		tileArray;
	protected Item[][]		itemArray;
	protected Entity[][]	entityArray;
	
	private Point			playerPoint;
	
	protected String		roomName;

	public Point getPlayerPoint() {
		return playerPoint;
	}

	public void setPlayerPoint(Point playerPoint) {
		this.playerPoint = playerPoint;
	}
	
}
