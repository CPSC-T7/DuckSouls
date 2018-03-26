package entities;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;

import items.Armour;
import items.Item;
import items.Weapon;
import utils.Orientation;
import javafx.scene.image.Image;

public class Player extends Entity {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static final int					BASE_HEALTH			= 20;
	private static final int					BASE_DEFENSE		= 5;
	private static final int					BASE_NEEDED_XP		= 20;
	
	private static final int					HEALTH_PER_LEVEL	= 5;
	private static final int					DEFENSE_PER_LEVEL	= 5;
	private static final int					NEEDED_XP_PER_LEVEL	= 10;
	
	private static final String					STRING_REPR			= " @ ";
	
	private static HashMap<Orientation, Image>	IMAGE_MAP;
	
	/**
	 * Anonymous inner class to fill the image map, and the make it "unmodifiable".
	 */
	static {
		
		// Temporary map to fill
		HashMap<Orientation, Image> fillerMap = new HashMap<Orientation, Image>();
		
		// Fill the map with the desired image for each orientation
		for (Orientation orientation : Orientation.values()) {
			fillerMap.put(orientation, new Image(ENTITY_SPRITE_FOLDER_PATH + "Duck/Duck-" + orientation.STR + ".png"));
		}
		
		// Set the static image map.
		// It's not final, but it is "unmodifiable"... so that's good enough I guess?
		Player.IMAGE_MAP = (HashMap<Orientation, Image>) Collections.unmodifiableMap(fillerMap);
		
	}
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Weapon					weapon;
	private Armour					armour;
	private HashMap<Item, Integer>	inventory	= new HashMap<Item, Integer>();
	
	private int						health;
	private int						defense;
	private int						level;
	private int						experience;
	private int						experienceForNextLevel;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Player(Point position) {
		
		super(STRING_REPR, IMAGE_MAP);
		
		this.health = BASE_HEALTH;
		this.defense = BASE_DEFENSE;
		this.experience = 0;
		this.level = 1;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public int sendAttack() {
		// TODO Actually do.
		return this.weapon.getDamage() * this.level; // Placeholder
	}
	
	@Override
	public void receiveAttack(int damage) {
		// TODO Actually do.
		this.health = this.health - damage - this.defense - this.armour.getDefense(); // Placeholder
	}
	
	public void addExperiece(int xp) {
		
		this.experience += xp;
		
		while (this.experience >= this.experienceForNextLevel) {
			this.levelUp();
		}
		
	}
	
	private void levelUp() {
		
		this.experience -= this.experienceForNextLevel;
		
		this.level++;
		
		this.health = BASE_HEALTH + (HEALTH_PER_LEVEL * this.level);
		this.defense = BASE_DEFENSE + (DEFENSE_PER_LEVEL * this.level);
		this.experienceForNextLevel = BASE_NEEDED_XP + (NEEDED_XP_PER_LEVEL * this.level);
		
	}
	
	public void pickupItem(Item item) {
		
		// TODO: IDEA! Have the user press a button to pick up an item!
		// TODO: IDEA! When the user picks up a weapon/armour, drop the other one!
		
		// Equip weapon if it's better than the current weapon
		if (item instanceof Weapon && ((Weapon) item).getDamage() > this.weapon.getDamage()) {
			this.weapon = (Weapon) item;
		}
		
		// Equip armour if it's better than the current armour
		if (item instanceof Armour && ((Armour) item).getDefense() > this.armour.getDefense()) {
			this.armour = (Armour) item;
		}
		
		// Add the item to the inventory
		if (this.inventory.containsKey(item)) {
			this.inventory.put(item, this.inventory.get(item) + 1);
		} else {
			this.inventory.put(item, 1);
		}
		
	}
	
}
