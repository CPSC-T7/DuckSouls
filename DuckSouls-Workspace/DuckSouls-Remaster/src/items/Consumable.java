package items;

import java.util.Random;

import javafx.scene.image.Image;
import tests.StatisticTests;

public enum Consumable implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	CROUTON("A Soggy Crouton", " C ", new Image(ITEM_SPRITE_FOLDER_PATH + "Crouton.png"), 30, 1),
	GOO("Some Weird Goo", " G ", new Image(ITEM_SPRITE_FOLDER_PATH + "Goo.png"), 15, -5),
	FISH("A Half-Eaten Fish", " F ", new Image(ITEM_SPRITE_FOLDER_PATH + "Fish.png"), 10, 15),
	BUGS("De Bugs", " B ", new Image(ITEM_SPRITE_FOLDER_PATH + "Bugs.png"), 50, 5);
	
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
	private final int		HEALTH_MOD;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new consumable.
	 * 
	 * @param name
	 *            The name of the consumable.
	 * @param stringRepr
	 *            The 3-character string used to draw the consumable in the text
	 *            version of the game.
	 * @param image
	 *            The image used to draw the consumable in the GUI version of the
	 *            game.
	 * @param spawnChance
	 *            The spawn chance of the consumable. Must be 0-100.
	 * @param healthMod
	 *            The health modifier of the consumable.
	 * @param manaMod
	 *            The mana modifier of the consumable.
	 */
	private Consumable(String name, String stringRepr, Image image, int spawnChance, int healthMod) {
		
		StatisticTests.testIntStatRange("Spawn Chance", spawnChance);
		
		this.NAME = name;
		this.STRING_REPR = stringRepr;
		this.IMAGE = image;
		this.SPAWN_CHANCE = spawnChance;
		this.HEALTH_MOD = healthMod;
		
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
		return (_random.nextInt(100) + 1 <= this.SPAWN_CHANCE);
	}
	
	/**
	 * Returns the consumable's health modifier.
	 * 
	 * @return The consumable's health modifier.
	 */
	public int getHealthMod() {
		return this.HEALTH_MOD;
	}
	
}
