package items;

/**
 * This class represents the consumable item crouton.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Crouton extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected static final String	pathToImage	= "/Sprites/Items/Crouton";
	
	private static final String	name		= "A Soggy Crouton";
	private static final String	stringRepr	= " C ";
	private static final int	price		= 1;
	private static final int	spawnChance	= 30;
	private static final int	health		= 1;
	private static final int	mana		= 0;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new croutons item.
	 */
	public Crouton() {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana);
	}
	
	/**
	 * Creates a new croutons item.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Crouton(int x, int y, int id) {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana, x, y, id);
	}
	
}
