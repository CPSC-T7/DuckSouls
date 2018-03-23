package items;

import javafx.scene.image.Image;
import tests.StatisticTests;
import java.util.Random;

public enum Weapon implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	SWORD("A Cool Sword", " S ", new Image(ITEM_FOLDER_PATH + "Sword.png"), 1, 20, 80),
	KNIFE("A Dull Butter Knife", " K ", new Image(ITEM_FOLDER_PATH + "Knife.png"), 3, 10, 90);
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private Random			_random	= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	NAME;
	private final String	STRING_REPR;
	private final Image		IMAGE;
	private final int		SPAWN_CHANCE;
	private final int		DAMAGE;
	private final int		SPEED;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new weapon.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param stringRepr
	 *            The 3-character string used to draw the weapon in the text version
	 *            of the game.
	 * @param image
	 *            The image used to draw the weapon in the GUI version of the game.
	 * @param spawnChance
	 *            The spawn chance of the weapon. Must be 0-100.
	 * @param damage
	 *            The damage the weapon deals.
	 * @param speed
	 *            The speed of the weapon's use. Must be 0-100.
	 */
	private Weapon(String name, String stringRepr, Image image, int spawnChance, int damage, int speed) {

		StatisticTests.testIntStatRange("Spawn Chance", spawnChance);
		StatisticTests.testIntStatRange("Speed", speed);
		
		this.NAME = name;
		this.STRING_REPR = stringRepr;
		this.IMAGE = image;
		this.SPAWN_CHANCE = spawnChance;
		this.DAMAGE = damage;
		this.SPEED = speed;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return this.IMAGE;
	}
	
	@Override
	public String getStringRepr() {
		return this.STRING_REPR;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
	
	@Override
	public boolean tryToSpawn() {
		return (_random.nextInt(100) + 1 < this.SPAWN_CHANCE);
	}
	
	/**
	 * Returns the weapon's damage statistic.
	 * 
	 * @return The weapon's damage statistic.
	 */
	public int getDamage() {
		return this.DAMAGE;
	}
	
	/**
	 * Returns the weapon's speed statistic.
	 * 
	 * @return The weapon's speed statistic.
	 */
	public int getSpeed() {
		return this.SPEED;
	}
	
}
