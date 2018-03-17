package items;

/**
 * This class defines the consumable item bugs.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Bugs extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected static final String	pathToImage	= "/Sprites/Items/Bugs";
	
	private static final String	name		= "De Bugs";
	private static final String	stringRepr	= " B ";
	private static final int	price		= 5;
	private static final int	spawnChance	= 50;
	private static final int	health		= 5;
	private static final int	mana		= 5;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new bugs item.
	 */
	public Bugs() {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana);
	}
	
	/**
	 * Creates a new bugs item.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Bugs(int x, int y, int id) {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana, x, y, id);
	}
	
}
