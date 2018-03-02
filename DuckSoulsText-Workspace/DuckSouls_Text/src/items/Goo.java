package items;

/**
 * This class represents the consumable item goo.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Goo extends Consumable {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "Some Weird Goo";
	private static String	stringRepr	= " G ";
	private static int		price		= 10;
	private static int		spawnChance	= 15;
	private static int		health		= 0;
	private static int		mana		= 20;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new goo item.
	 */
	public Goo() {
		super(name, stringRepr, price, spawnChance, health, mana);
	}
	
}
