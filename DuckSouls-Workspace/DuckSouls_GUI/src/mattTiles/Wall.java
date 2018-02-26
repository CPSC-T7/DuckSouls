package mattTiles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Wall extends Tile {
	
	public Wall(String STR, Image img) {
		super(STR, false, img);
	}
}
