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
	private static final String	pathToImage	= "../Sprites/Entities/Duck/Duck-";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new player.
	 */
	public Player() {
		super(stringRepr, pathToImage);
	}
	
}
