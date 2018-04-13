package items;

import javafx.scene.image.Image;
import java.util.Random;

public enum Armour implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	CLOTH_ARMOUR("A Torn Sack", " CA", "ClothArmour.png", 8, 5),
	LEATHER_ARMOUR("An Old Leather Tunic", " LA", "LeatherArmour.png", 6, 10),
	METAL_ARMOUR("A Broken Washing Machine", " MA", "MetalArmour.png", 4, 20),
	NONE("My Soft Feathers", "   ", "", 0, 0);
	
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
	private final String	IMAGE_NAME;
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
	 * @param imageName
	 *            The name of the image used to draw the armour in the GUI version
	 *            of the game.
	 * @param spawnChance
	 *            The spawn chance of the armour. Must be 0-100.
	 * @param defense
	 *            The defense statistic of the armour.
	 */
	private Armour(String name, String stringRepr, String imageName, int spawnChance, int defense) {
		
		this.NAME = name;
		this.STRING_REPR = stringRepr;
		this.IMAGE_NAME = imageName;
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
		return new Image(ITEM_SPRITE_FOLDER_PATH + this.IMAGE_NAME);
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
	
	@Override
	public String getFileString() {
		return this.STRING_REPR.replaceAll(" ", "");
	}

	@Override
	public int getSpawnChance() {
		return this.SPAWN_CHANCE;
	}
	
}
