package mattEntities;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	
	public Enemy(Image img) {
		super(" E ");
		this.type = "ENEMY";
		this.IMAGE = img;
	}
}
