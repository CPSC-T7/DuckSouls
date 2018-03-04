package entities;

import java.awt.Point;
import java.util.ArrayList;

import items.Item;
import utils.Orientation;

/**
 * This class is used to represent living entities within DuckSouls. Each entity
 * has a position, string representation, orientation, and inventory.
 * 
 * @author Matthew Allwright
 * @version 1.2.2
 */
public class Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Point			position	= new Point();
	private String			stringRepr	= "EER";
	private Orientation		orientation	= Orientation.SOUTH;
	private ArrayList<Item>	inventory	= new ArrayList<Item>();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates an entity.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the entity.
	 */
	protected Entity(String stringRepr) {
		
		this.stringRepr = stringRepr;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns a copy of the entity's string representation.
	 * 
	 * @return A copy of the entity's string representation.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	}
	
	/**
	 * Returns a copy of the entity's position.
	 * 
	 * @return A copy of the entity's position.
	 */
	public Point getPosition() {
		
		return new Point(this.position);
		
	}
	
	/**
	 * Returns the orientation of the entity.
	 * 
	 * @return The orientation of the entity.
	 */
	public Orientation getOrientation() {
		
		return this.orientation;
		
	}
	
	/**
	 * Adds an item to the entity's inventory.
	 * 
	 * @param item The item to add.
	 */
	public void addToInventory(Item item) {
		
		this.inventory.add(item);
		
	}
	
	/**
	 * Returns a copy of the entity's inventory.
	 * 
	 * @return A copy of the entity's inventory.
	 */
	public ArrayList<Item> getInventory() {
		
		return new ArrayList<Item>(this.inventory);
		
	}
	
}
