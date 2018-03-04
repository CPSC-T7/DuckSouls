package entities;

/**
 * The GUI player class.
 * 
 * @author Wylee
 * @author Matt
 */
public class Player extends Entity {
	
	public Player() {
		super(" @ ");
		this.type 			= "PLAYER";
		this.DIRECTION 		= "Right";
		this.DIRECTORY[0] 	= "../Sprites/Entities/Duck/Duck-";
		this.DIRECTORY[1]   = ".png";
		this.newImage();
	}
}
