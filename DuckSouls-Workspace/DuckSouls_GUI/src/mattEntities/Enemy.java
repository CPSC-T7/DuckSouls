package mattEntities;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	
	public Enemy() {
		super(" E ");
		this.type = "ENEMY";
		this.DIRECTION = "Down";
		this.DIRECTORY[0] = "Sprites/Rat/Rat-";
		this.DIRECTORY[1] = ".png";
		this.newImage();
	}
}
