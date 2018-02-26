package mattEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {
	
	public Player() {
		super(" @ ");
		this.type 		= "PLAYER";
		this.DIRECTION 	= "Right";
		this.IMAGE 		= new Image("Sprites/Duck/Duck-" + DIRECTION + ".png");
	}
}
