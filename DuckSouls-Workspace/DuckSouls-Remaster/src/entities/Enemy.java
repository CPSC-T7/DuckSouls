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
	
	private static final double					BASE_HEALTH			= 20;
	private static final double					BASE_ATTACK			= 6;
	private static final double					BASE_DEFENCE		= 5;
	private static final double					BASE_SPEED			= 5;
	private static final double					BASE_ACCURACY		= 71;
	private static final double					BASE_CRIT			= 16;
	private static final int					BASE_GIVEN_XP		= 10;
	private static final int					SCORE				= 10;

	
	private static final int					HEALTH_PER_LEVEL	= 2;
	private static final int					ATTACK_PER_LEVEL	= 1;
	private static final int					DEFENCE_PER_LEVEL	= 2;
	private static final int					SPEED_PER_LEVEL		= 1;
	private static final int					ACCURACY_PER_LEVEL	= 2;
	private static final int					CRIT_PER_LEVEL		= 2;
	private static final int					GIVEN_XP_PER_LEVEL	= 2;
	
	
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
	
	private int						experienceGiven;
	
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Enemy(Point position, int level) {
		
		super(STRING_REPR, IMAGE_MAP, (BASE_HEALTH + (HEALTH_PER_LEVEL * (level - 1))), 
				(BASE_ATTACK + (ATTACK_PER_LEVEL * (level - 1))), (BASE_DEFENCE + (DEFENCE_PER_LEVEL * (level - 1))),
				(BASE_SPEED + (SPEED_PER_LEVEL * (level - 1))), (BASE_ACCURACY + (ACCURACY_PER_LEVEL * (level - 1))), 
				(BASE_CRIT + (CRIT_PER_LEVEL * (level - 1))));	
		
		this.experienceGiven = BASE_GIVEN_XP + (GIVEN_XP_PER_LEVEL + (level - 1));
	}
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public int getExperienceGiven() {
		return experienceGiven;
	}
	
	public int getScore() {
		return SCORE;
	}
	
}