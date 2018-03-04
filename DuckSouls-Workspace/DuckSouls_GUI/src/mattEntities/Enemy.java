package mattEntities;

/**
 * The enemy class.
 * 
 * @author Wylee
 * @author Matt
 */
public class Enemy extends Entity {
	
	public Enemy() {
		super(" E ");
		this.type = "ENEMY";
		this.DIRECTION = "Down";
		this.DIRECTORY[0] = "../Sprites/Entities/Rat/Rat-";
		this.DIRECTORY[1] = ".png";
		this.newImage();
	}
}
