package items;

import javafx.scene.image.Image;
import utils.Utilities;

/**
 * This class represents an item in DuckSouls. The item has a name, string
 * representation, price, and spawn chance by default. Items can also have stat
 * modifiers that are put into place when used/equipped.
 * 
 * @author Matthew Allwright
 * @author Cassondra Platel
 * @author Colin Yeung
 */
public class Item {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected String			stringRepr				= "IER";
	protected String			name;
	protected String			pathToImage;
	protected int				spawnChance				= 0;															// 0-99
	protected int				price;
	protected int				health;
	protected int				mana;
	protected int				attack;
	protected int				speed;
	protected int				accuracy;
	protected int				critChance;
	protected int				defense;
	private int					x;
	private int					y;
	private int					id;
	
	// TODO: Make this better, again...
	public static final Item[]	allSpawnableItems		= { new Bugs(), new Crouton(), new Fish(), new Goo(),
			new Knife(), new Sword(), new ClothArmour(), new LeatherArmour(), new MetalArmour() };
	public static final Item[]	allSpawnableConsumables	= { new Bugs(), new Crouton(), new Fish(), new Goo() };
	public static final Item[]	allSpawnableWeapons		= { new Knife(), new Sword() };
	public static final Item[]	allSpawnableArmour		= { new ClothArmour(), new LeatherArmour(), new MetalArmour() };
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new item with defined basic values.
	 * 
	 * @param name
	 *            The name of the item.
	 * @param pathToImage
	 *            The path to the image sprite.
	 * @param stringRepr
	 *            The 3 character string used to print the item.
	 * @param price
	 *            The price of the item.
	 * @param spawnChance
	 *            The spawn chance of the item. Must be from 0 to 100.
	 */
	protected Item(String name, String pathToImage, String stringRepr, int price, int spawnChance) {
		
		this.name = name;
		this.pathToImage = pathToImage;
		this.stringRepr = stringRepr;
		this.price = price;
		this.spawnChance = spawnChance;
		
	}
	
	/**
	 * Creates a new item with defined basic values.
	 * 
	 * @param name
	 *            The name of the item.
	 * @param pathToImage
	 *            The path to the image sprite.
	 * @param stringRepr
	 *            The 3 character string used to print the item.
	 * @param price
	 *            The price of the item.
	 * @param spawnChance
	 *            The spawn chance of the item. Must be from 0 to 100.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	protected Item(String name, String pathToImage, String stringRepr, int price, int spawnChance, int x, int y,
			int id) {
		
		this.name = name;
		this.pathToImage = pathToImage;
		this.stringRepr = stringRepr;
		this.price = price;
		this.spawnChance = spawnChance;
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	/**
	 * Copies an item.
	 * 
	 * @param item
	 *            The item to copy.
	 */
	public Item(Item item) {
		
		this.stringRepr = item.stringRepr;
		this.pathToImage = item.pathToImage;
		this.name = item.name;
		this.spawnChance = item.spawnChance;
		this.price = item.price;
		this.health = item.health;
		this.mana = item.mana;
		this.attack = item.attack;
		this.speed = item.speed;
		this.accuracy = item.accuracy;
		this.critChance = item.critChance;
		this.defense = item.defense;
		
	}
	
	/**
	 * Copies an item and adds some parameters.
	 * 
	 * @param item
	 *            The item to copy.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Item(Item item, int x, int y, int id) {
		this.stringRepr = item.stringRepr;
		this.name = item.name;
		this.spawnChance = item.spawnChance;
		this.price = item.price;
		this.health = item.health;
		this.mana = item.mana;
		this.attack = item.attack;
		this.speed = item.speed;
		this.accuracy = item.accuracy;
		this.critChance = item.critChance;
		this.defense = item.defense;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns a copy of the item's name.
	 * 
	 * @return A copy of the item's name.
	 */
	public String getName() {
		
		return new String(this.name);
		
	}
	
	/**
	 * Returns the item's spawn chance.
	 * 
	 * @return The item's spawn chance.
	 */
	public int getSpawnChance() {
		
		return this.spawnChance;
		
	}
	
	/**
	 * Returns a copy of the item's string representation.
	 * 
	 * @return A copy of the item's string representation.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	}
	
	/**
	 * Returns the x value of the item.
	 * @return The x value of the item.
	 */
	public int getX() {
		
		return this.x;
		
	}
	
	/**
	 * Returns the y value of the item.
	 * @return The y value of the item.
	 */
	public int getY() {
		
		return this.y;
		
	}
	
	/**
	 * Returns the id value of the item.
	 * @return The id value of the item.
	 */
	public int getID() {
		
		return this.id;
		
	}
	
	/**
	 * Gets a specified stat.
	 * 
	 * @param stat
	 *            The stat that will be returned
	 * @return the value of the stat
	 */
	public int getExtraStats(String stat) {
		
		switch (stat) {
			
			case "attack":
				return this.attack;
			case "accuracy":
				return this.accuracy;
			case "speed":
				return this.speed;
			case "critChance":
				return this.critChance;
			case "defense":
				return this.defense;
			default:
				return 0;
		}
		
	}
	
	/**
	 * Returns the image of the item.
	 * @return The image of the item.
	 */
	public Image getImage() {
		return new Image("file:///" + Utilities.getParentDir() + this.pathToImage + ".png");
	}
	
}
