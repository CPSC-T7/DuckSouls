package items;

/**
 * This class represents the armour item metal armour.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class MetalArmour extends Armour {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "An Old Washing Machine";
	private static String	stringRepr	= " MA";
	private static int		price		= 35;
	private static int		spawnChance	= 4;
	private static int		defense		= 20;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new metal armour item.
	 */
	public MetalArmour() {
		super(name, stringRepr, price, spawnChance, defense);
	}
	
}
