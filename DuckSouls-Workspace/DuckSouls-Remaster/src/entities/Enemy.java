package entities;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import utils.Orientation;
import javafx.scene.image.Image;

public class Enemy extends Entity {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static final int					BASE_HEALTH			= 20;
	private static final int					BASE_DEFENSE		= 5;
	private static final int					BASE_DAMAGE			= 10;
	
	private static final int					HEALTH_PER_LEVEL	= 5;
	private static final int					DEFENSE_PER_LEVEL	= 5;
	private static final int					DAMAGE_PER_LEVEL	= 5;
	
	private static final String					STRING_REPR			= " E ";
	
	private static HashMap<Orientation, Image>	IMAGE_MAP;
	
	/**
	 * Anonymous inner class to fill the image map, and the make it "unmodifiable".
	 */
	static {
		
		// Temporary map to fill
		HashMap<Orientation, Image> fillerMap = new HashMap<Orientation, Image>();
		
		// Fill the map with the desired image for each orientation
		for (Orientation orientation : Orientation.values()) {
			fillerMap.put(orientation, new Image(ENTITY_SPRITE_FOLDER_PATH + "Rat/Rat-" + orientation.STR + ".png"));
		}
		
		// Set the static image map.
		// It's not final, but it is "unmodifiable"... so that's good enough I guess?
		Enemy.IMAGE_MAP = (HashMap<Orientation, Image>) Collections.unmodifiableMap(fillerMap);
		
	}
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private int	health;
	private int	defense;
	private int	damage;
	private int	level;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Enemy(Point position, int level) {
		
		super(STRING_REPR, IMAGE_MAP);
		
		this.health = BASE_HEALTH + (HEALTH_PER_LEVEL * this.level);
		this.defense = BASE_DEFENSE + (DEFENSE_PER_LEVEL * this.level);
		this.damage = BASE_DAMAGE + (DAMAGE_PER_LEVEL * this.level);
		this.level = level;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public int sendAttack() {
		// TODO Actually do.
		return this.damage; // Placeholder
	}
	
	@Override
	public void receiveAttack(int damage) {
		// TODO Actually do.
		this.health = this.health - damage - this.defense; // Placeholder
	}
	
}
