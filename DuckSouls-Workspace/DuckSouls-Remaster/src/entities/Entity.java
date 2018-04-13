package entities;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

import genericInterfaces.Battleable;
import genericInterfaces.Drawable;
import genericInterfaces.Moveable;
import javafx.scene.image.Image;
import utils.Orientation;

/**
 * This abstract class represents an entity object.
 */
public abstract class Entity implements Drawable, Moveable, Battleable, Serializable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	/**
	 * Generated Serializable ID.
	 */
	private static final long	serialVersionUID			= 96600839716600250L;
	public static final String	ENTITY_SPRITE_FOLDER_PATH	= SPRITE_FOLDER_PATH + "Entities/";
	
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
	protected boolean	isDead              = false;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Entity() {
	}
	
	/**
	 * Creates a new entity.
	 * 
	 * @param stringRepr
	 *            The 3-character string to print the entity in the text version of
	 *            the game.
	 * @param position
	 *            The position of the entity.
	 * @param health
	 *            The health of the entity.
	 * @param attack
	 *            The attack value of the entity.
	 * @param defence
	 *            The defence stat of the entity.
	 * @param speed
	 *            The speed of the entity.
	 * @param accuracy
	 *            The accuracy of the entity.
	 * @param crit
	 *            The crit chance of the entity.
	 */
	protected Entity(String stringRepr, Point position, double health, double attack, double defence, double speed,
			double accuracy, double crit) {
		
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
		} else {
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
	public String chooseItem() {
		return "Bananas";
	}
	
	@Override
	public boolean run() {
		return false;
	}
	
	@Override
	public String choice(int move) {
		
		if (move == 0 || move == 2 || move == 3) {
			return "Attack";
		} else if (move == 1) {
			return "Taunt";
		} else if (move == 4) {
			return "Item";
		} else {
			return "Fly";
		}
	}
	
	/**
	 * Move the entity in a direction.
	 * 
	 * @param direction
	 *            The direction to move the entity.
	 */
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
	
	/**
	 * Returns the damage given, modified with a random value added or subtracted to
	 * it for variety.
	 * 
	 * @param damage
	 *            The base damage to modify.
	 * @return The damage with the value shifted slightly.
	 */
	public double attackBonus(double damage) {
		Random rand = new Random();
		int bonus = rand.nextInt(11) - 5;
		damage += bonus;
		return damage;
	}
	
	/**
	 * Returns the health of the entity.
	 * 
	 * @return The health of the entity.
	 */
	public double getHealth() {
		return this.health;
	}
	
	/**
	 * Returns the attack of the entity.
	 * 
	 * @return The attack of the entity.
	 */
	public double getAttack() {
		return this.attack;
	}
	
	/**
	 * Returns the defence of the entity.
	 * 
	 * @return The defence of the entity.
	 */
	public double getDefence() {
		return this.defence;
	}
	
	/**
	 * Returns the speed of the entity.
	 * 
	 * @return The speed of the entity.
	 */
	public double getSpeed() {
		return this.speed;
	}
	
	/**
	 * Returns the accuracy of the entity.
	 * 
	 * @return The accuracy of the entity.
	 */
	public double getAccuracy() {
		return this.accuracy;
	}
	
	/**
	 * Returns the crit chance of the entity.
	 * 
	 * @return The crit chance of the entity.
	 */
	public double getCrit() {
		return this.crit;
	}
	
	/**
	 * Sets the health of the player.
	 * 
	 * @param newVal
	 *            The new health of the player.
	 */
	public void setHealth(double newVal) {
		this.health = newVal;
	}
	
	/**
	 * Sets the attack of the player.
	 * 
	 * @param newVal
	 *            The new attack of the player.
	 */
	public void setAttack(double newVal) {
		this.attack = newVal;
	}
	
	/**
	 * Sets the defence of the player.
	 * 
	 * @param newVal
	 *            The new defence of the player.
	 */
	public void setDefence(double newVal) {
		this.defence = newVal;
	}
	
	/**
	 * Sets the speed of the player.
	 * 
	 * @param newVal
	 *            The new speed of the player.
	 */
	public void setSpeed(double newVal) {
		this.speed = newVal;
	}
	
	/**
	 * Sets the accuracy of the player.
	 * 
	 * @param newVal
	 *            The new accuracy of the player.
	 */
	public void setAccuracy(double newVal) {
		this.accuracy = newVal;
	}
	
	/**
	 * Sets the crit chance of the player.
	 * 
	 * @param newVal
	 *            The new crit chance of the player.
	 */
	public void setCrit(double newVal) {
		this.crit = newVal;
	}
	
	/**
	 * Returns if the player is dead or not
	 * 
	 * @return
	 * 		True if dead, else false
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * Set whether the player is dead or not
	 * 
	 * @param isDead, true if player is dead, else false
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
