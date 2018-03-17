package items;

/**
 * This class represents the consumable item food.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Fish extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected static final String	pathToImage	= "/Sprites/Items/Fish";
	
	private static final String	name		= "A Half-Eaten Fish";
	private static final String	stringRepr	= " F ";
	private static final int	price		= 20;
	private static final int	spawnChance	= 10;
	private static final int	health		= 15;
	private static final int	mana		= 0;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new fish item.
	 */
	public Fish() {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana);
	}
	
	/**
	 * Creates a new fish item.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Fish(int x, int y, int id) {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana, x ,y, id);
	}
	
}
