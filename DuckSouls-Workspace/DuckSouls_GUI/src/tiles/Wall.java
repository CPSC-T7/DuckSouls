package tiles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * The GUI wall-tile parent class.
 * 
 * @author Wylee
 * @author Matt
 */
public class Wall extends Tile {
	
	public Wall(String STR, Image img) {
		super(STR, false, img);
	}
}
