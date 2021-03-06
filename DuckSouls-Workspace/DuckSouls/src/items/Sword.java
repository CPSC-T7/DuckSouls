package items;

/**
 * This class represents the weapon item sword.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Sword extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected static final String	pathToImage	= "/Sprites/Items/Sword";
	
	private static final String	name		= "A Cool Sword";
	private static final String	stringRepr	= " S ";
	private static final int	price		= 40;
	private static final int	spawnChance	= 1;
	private static final int	attack		= 20;
	private static final int	accuracy	= 85;
	private static final int	speed		= 80;
	private static final int	critchance	= 15;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new sword item.
	 */
	public Sword() {
		super(name, pathToImage, stringRepr, price, spawnChance, attack, accuracy, speed, critchance);
	}
	
	/**
	 * Creates a new sword item.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Sword(int x, int y, int id) {
		super(name, pathToImage, stringRepr, price, spawnChance, attack, accuracy, speed, critchance, x, y, id);
	}
	
}
