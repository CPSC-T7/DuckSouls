package entities;

import java.awt.Point;
import java.util.HashMap;

import genericInterfaces.Battleable;
import genericInterfaces.Drawable;
import genericInterfaces.Moveable;
import javafx.scene.image.Image;
import utils.Orientation;

public abstract class Entity implements Drawable, Moveable, Battleable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String ENTITY_SPRITE_FOLDER_PATH = SPRITE_FOLDER_PATH + "Entities/";
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	protected Entity(String stringRepr, HashMap<Orientation, Image> imageMap) {
		
		this.stringRepr = stringRepr;
		this.imageMap = imageMap;
		
	}
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected String						stringRepr;
	protected HashMap<Orientation, Image>	imageMap;
	protected Point							position	= new Point(1, 1);
	protected Orientation					orientation	= Orientation.SOUTH;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public String getStringRepr() {
		return this.stringRepr;
	}
	
	@Override
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	@Override
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public Point getPosition() {
		return new Point(this.position);
	}
	
	@Override
	public void setPosition(Point position) {
		this.position = new Point(position);
	}
	
	@Override
	public Image getImage() {
		return this.imageMap.get(this.orientation);
	}
	
	public void move(Orientation direction) {
		
		switch (direction) {
			
			case NORTH:
				this.position.y -= 1;
				break;
			
			case SOUTH:
				this.position.y += 1;
				break;
			
			case EAST:
				this.position.x -= 1;
				break;
			
			case WEST:
				this.position.x += 1;
				break;
			
		}
		
	}
	
}
