package tiles;

import javafx.scene.image.Image;

/**
 * The GUI door class.
 * 
 * @author Wylee
 * @author Matt
 */
public class Door extends Tile {
	
	public Door(String direction) {
		super(" D ", true, new Image("Tiles/Sewer/Door-" + direction + ".png"));
	}

}
