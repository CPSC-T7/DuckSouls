package mattEntities;

import java.awt.Point;

//JavaFX
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Entity {
	
	public Image		IMAGE;
	protected String	DIRECTION;
	public String[] 	DIRECTORY = new String[2];
	public String 		STRING_REPR = "EER";
	public String 		type = "NUN";
	public Point 		POS;
	
	
	/**
	 * Creates an entity.
	 * 
	 * @param STRING_REPR
	 *            The 3-character string used to print the tile.
	 */
	protected Entity(String STRING_REPR) {
		
		this.STRING_REPR = STRING_REPR;
		
	}
	
	/**
	 * Draw the entity to the screen at a position (x,y)
	 * 
	 * @param gc
	 * 			Graphics Context
	 * @param position
	 * 			Entity Position
	 */
	public void drawEntity(GraphicsContext gc, int[] position) {
		
		gc.drawImage(this.IMAGE, position[0], position[1]);
		
	}
	
	
	/**
	 * Create a new image depending on the image directory and direction of the entity.
	 */
	public void newImage() {
		
		this.IMAGE = new Image(this.DIRECTORY[0] + this.DIRECTION + this.DIRECTORY[1]);
		
	}
	
	public void setDirection(String s) {
		this.DIRECTION = s;
	}
}
