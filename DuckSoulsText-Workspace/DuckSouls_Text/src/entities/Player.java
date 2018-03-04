package entities;

/**
 * This class represents a player entity.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Player extends Entity {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String stringRepr = " @ ";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new player.
	 */
	public Player() {
		super(stringRepr);
	}
	
}
