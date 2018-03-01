package mattEntities;

public class Player extends Entity {
	
	public Player() {
		super(" @ ");
		this.type 			= "PLAYER";
		this.DIRECTION 		= "Right";
		this.DIRECTORY[0] 	= "Sprites/Duck/Duck-";
		this.DIRECTORY[1]   = ".png";
		this.newImage();
	}
}
