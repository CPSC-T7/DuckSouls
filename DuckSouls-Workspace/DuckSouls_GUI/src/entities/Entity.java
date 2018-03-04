package entities;

import java.awt.Point;

//JavaFX
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;


/**
 * The GUI Entity class.
 * Creates interactive entities that spawn on top of
 * the room tiles.
 * 
 * @author Wylee
 * @author Matt
 */
public class Entity {
	
	protected Image			IMAGE;
	protected String		DIRECTION;
	protected String[] 		DIRECTORY = new String[2];
	protected String 		STRING_REPR = "EER";
	protected String 		type = "NUN";
	protected Point 		POS;
	
	
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
	public void drawEntity(GraphicsContext gc, Point position) {
		
		gc.drawImage(this.IMAGE, position.x, position.y);
		
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
	
	public String getType() {
		return(this.type);
	}
}
