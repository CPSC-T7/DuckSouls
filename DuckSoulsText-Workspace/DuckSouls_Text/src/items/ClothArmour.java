package items;

/**
 * This class represents the armour item cloth armour.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class ClothArmour extends Armour {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "A Torn Sack";
	private static String	stringRepr	= " CA";
	private static int		price		= 10;
	private static int		spawnChance	= 8;
	private static int		defense		= 5;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new cloth armour item.
	 */
	public ClothArmour() {
		super(name, stringRepr, price, spawnChance, defense);
	}
	
}
