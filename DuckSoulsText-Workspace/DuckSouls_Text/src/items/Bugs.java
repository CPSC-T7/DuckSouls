package items;

/**
 * This class defines the consumable item bugs.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Bugs extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "De Bugs";
	private static String	stringRepr	= " B ";
	private static int		price		= 5;
	private static int		spawnChance	= 50;
	private static int		health		= 5;
	private static int		mana		= 5;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new bugs item.
	 */
	public Bugs() {
		super(name, stringRepr, price, spawnChance, health, mana);
	}
	
}
