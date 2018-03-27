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
	
	protected Entity(String stringRepr, HashMap<Orientation, Image> imageMap, double health,
			double attack, double defence, double speed, double accuracy, double crit) {
		
		this.stringRepr = stringRepr;
		this.imageMap = imageMap;
		this.health = health;
		this.attack = attack;
		this.defence = defence;
		this.speed = speed;
		this.accuracy = accuracy;
		this.crit = crit;
		
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
	protected double						health;
	protected double						attack;
	protected double						defence;
	protected double						speed;
	protected double						accuracy;
	protected double						crit;
	
	
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
	
	public double getHealth() {
		return this.health;
	}
	
	public double getAttack() {
		return this.attack;
	}
	
	public double getDefence() {
		return this.defence;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getAccuracy() {
		return this.accuracy;
	}
	
	public double getCrit() {
		return this.crit;
	}
	
	public void setHealth(double newVal) {
		this.health = newVal;
	}
	
	public void setAttack(double newVal) {
		this.attack = newVal;
	}
	
	public void setDefence(double newVal) {
		this.defence = newVal;
	}
	
	public void setSpeed(double newVal) {
		this.speed = newVal;
	}
	
	public void setAccuracy(double newVal) {
		this.accuracy = newVal;
	}
	
	public void setCrit(double newVal) {
		this.crit = newVal;
	}
	
}
