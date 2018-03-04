package entities;

/**
 * This class represents an enemy entity.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Enemy extends Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " E ";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new enemy.
	 */
	public Enemy() {
		super(stringRepr);
	}
	
}
