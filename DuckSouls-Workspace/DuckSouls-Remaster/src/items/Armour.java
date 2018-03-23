package items;

import javafx.scene.image.Image;
import java.util.Random;
import tests.StatisticTests;

public enum Armour implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	CLOTH_ARMOUR("A Torn Sack", " CA", new Image(ITEM_SPRITE_FOLDER_PATH + "ClothArmour.png"), 8, 5),
	LEATHER_ARMOUR("An Old Leather Tunic", " LA", new Image(ITEM_SPRITE_FOLDER_PATH + "LeatherArmour.png"), 6, 10),
	METAL_ARMOUR("A Broken Washing Machine", " MA", new Image(ITEM_SPRITE_FOLDER_PATH + "MetalArmour.png"), 4, 20);

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
	private final int		DEFENSE;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new armour.
	 * 
	 * @param name
	 *            The name of the armour.
	 * @param stringRepr
	 *            The 3-character string used to draw the armour in the text version
	 *            of the game.
	 * @param image
	 *            The image used to draw the armour in the GUI version of the game.
	 * @param spawnChance
	 *            The spawn chance of the armour. Must be 0-100.
	 * @param defense
	 *            The defense statistic of the armour.
	 */
	private Armour(String name, String stringRepr, Image image, int spawnChance, int defense) {
		
		StatisticTests.testIntStatRange("Spawn Chance", spawnChance);
		
		this.NAME = name;
		this.STRING_REPR = stringRepr;
		this.IMAGE = image;
		this.SPAWN_CHANCE = spawnChance;
		this.DEFENSE = defense;
		
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
	 * Returns the armour's defense statistic.
	 * 
	 * @return The armour's defense statistic.
	 */
	public int getDefense() {
		return this.DEFENSE;
	}
	
}
