package entities;

import java.awt.Point;
import java.util.HashMap;

import items.Armour;
import items.Item;
import items.Weapon;
import javafx.scene.image.Image;

public class Player extends Entity {
	
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
	private static final int					BASE_NEEDED_XP		= 20;
	
	private static final int					HEALTH_PER_LEVEL	= 2;
	private static final int					ATTACK_PER_LEVEL	= 1;
	private static final int					DEFENCE_PER_LEVEL	= 2;
	private static final int					SPEED_PER_LEVEL		= 1;
	private static final int					ACCURACY_PER_LEVEL	= 2;
	private static final int					CRIT_PER_LEVEL		= 2;
	private static final int					NEEDED_XP_PER_LEVEL	= 10;
	
	private static final String					STRING_REPR			= " @ ";
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Weapon								weapon				= Weapon.NONE;
	private Armour								armour				= Armour.NONE;
	private HashMap<Item, Integer>				inventory			= new HashMap<Item, Integer>();
	
	private int									level;
	private int									experience;
	private int									experienceForNextLevel;
	private int									score;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Player(Point position) {
		
		super(STRING_REPR, BASE_HEALTH, BASE_ATTACK, BASE_DEFENCE, BASE_SPEED, BASE_ACCURACY, BASE_CRIT);
		
		this.experience = 0;
		this.level = 1;
		this.score = 0;
		this.experienceForNextLevel = BASE_NEEDED_XP;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return new Image(ENTITY_SPRITE_FOLDER_PATH + "Duck/Duck-" + this.orientation.STR + ".png");
	}
	
	@Override
	public double sendAttack() {
		double damage;
		
		damage = super.sendAttack();
		if (damage != 0) {
			damage = damage + this.weapon.getDamage();
		}
		return damage;
	}
	
	@Override
	public void receiveAttack(double damage) {
		super.receiveAttack(damage);
		this.health = this.health + this.armour.getDefense(); // Placeholder
	}
	
	@Override
	public int choice(int a) {
		
		//TODO: @Wylee can you do the code for choosing le gui options for battle
		//Give option to player
		int move = 0; //temporary, make it int move;
		//if attack, move == 0
		//if taunt, move == 1
		//if item, move == 4
		//if run away, move == 5
		
		int command = super.choice(move);
		return command;
	}
	
	@Override
	public void useItem() {
		//TODO
	}
	
	@Override
	public void run() {
		//TODO
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
		this.attack = BASE_ATTACK + (ATTACK_PER_LEVEL * this.level);
		this.defence = BASE_DEFENCE + (DEFENCE_PER_LEVEL * this.level);
		this.speed = BASE_SPEED + (SPEED_PER_LEVEL * this.level);
		this.accuracy = BASE_ACCURACY + (ACCURACY_PER_LEVEL * this.level);
		this.crit = BASE_CRIT + (CRIT_PER_LEVEL * this.level);
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
	
	public void setScore(int addScore) {
		this.score = this.score + addScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public HashMap<Item, Integer> getInventory() {
		return this.inventory;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public Armour getArmour() {
		return this.armour;
	}
	
	public int getExperience() {
		return this.experience;
	}
	
}
