package items;

/**
 * This class represents the armour item leather armour.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class LeatherArmour extends Armour {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "An Old Leather Tunic";
	private static String	stringRepr	= " LA";
	private static int		price		= 2;
	private static int		spawnChance	= 6;
	private static int		defense		= 10;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new leather armour item.
	 */
	public LeatherArmour() {
		super(name, stringRepr, price, spawnChance, defense);
	}
	
}
