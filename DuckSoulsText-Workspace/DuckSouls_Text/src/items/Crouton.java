package items;

/**
 * This class represents the consumable item crouton.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Crouton extends Consumable {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "A Soggy Crouton";
	private static String	stringRepr	= " C ";
	private static int		price		= 1;
	private static int		spawnChance	= 30;
	private static int		health		= 1;
	private static int		mana		= 0;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new croutons item.
	 */
	public Crouton() {
		super(name, stringRepr, price, spawnChance, health, mana);
	}
	
}
