package mattTiles;

import javafx.scene.image.Image;

public class Door extends Tile {
	
	public Door(String direction) {
		super(" D ", true, new Image("Tiles/Sewer/Door-" + direction + ".png"));
	}

}
