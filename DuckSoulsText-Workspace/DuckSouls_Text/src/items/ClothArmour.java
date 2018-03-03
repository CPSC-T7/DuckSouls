package items;

/**
 * This class represents the armour item cloth armour.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class ClothArmour extends Armour {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "A Torn Sack";
	private static final String	stringRepr	= " CA";
	private static final int	price		= 10;
	private static final int	spawnChance	= 8;
	private static final int	defense		= 5;
	
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
