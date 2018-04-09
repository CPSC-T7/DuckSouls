package entities;

import java.awt.Point;
import java.util.Random;

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
	
	public abstract int getLevel();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected String		stringRepr;
	protected Point			position;
	protected Orientation	orientation	= Orientation.SOUTH;
	protected double		health;
	protected double		attack;
	protected double		defence;
	protected double		speed;
	protected double		accuracy;
	protected double		crit;
	
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	protected Entity(String stringRepr, Point position, double health, double attack, double defence, double speed, double accuracy,
			double crit) {
		
		this.stringRepr = stringRepr;
		this.position = position;
		this.health = health;
		this.attack = attack;
		this.defence = defence;
		this.speed = speed;
		this.accuracy = accuracy;
		this.crit = crit;
		
	}
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
	public abstract Image getImage();
	
	@Override
	public double sendAttack() {
		Random rand = new Random();
		double damage;
		int accChance;
		int critChance;
		
		accChance = rand.nextInt(100) + 1;
		critChance = rand.nextInt(100) + 1;
		damage = this.attack * 2.5;
		damage = this.attackBonus(damage);
		if (critChance < this.crit) {
			damage = damage * 1.5;
		}
		if (accChance > this.accuracy) {
			return 0;
		} else {
			return damage;
		}
	}
	
	@Override
	public double receiveAttack(double damage) {
		if (damage == 0) {
			return 0;
		}
		else {
			damage = Math.round(damage - this.defence);
			if (damage < 1) {
				damage = 1;
			}
			this.health = this.health - damage;
			return (damage);
		}
	}
	
	@Override
	public void taunted() {
		this.attack = this.attack + 5;
		this.defence = this.defence - 5;
	}
	
	@Override
	public void useItem() {	
	}
	
	@Override
	public boolean run() {
		return false;
	}
	
	@Override
	public String choice(int move, boolean isGUI) {
				
		if (move == 0 || move == 2 || move == 3) {
			return "Attack";
		} 
		else if (move == 1) {
			return "Taunt";
		}
		else if (move == 4) {
			return "Item";
		}
		else {
			return "Run";
		}
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
		
		this.setOrientation(direction);
		
	}
	
	public double attackBonus(double damage) {
		Random rand = new Random();
		int bonus = rand.nextInt(11) - 5;
		damage += bonus;
		return damage;
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
