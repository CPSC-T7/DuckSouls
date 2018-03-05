package items;

/**
 * This class represents the armour item leather armour.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class LeatherArmour extends Armour {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "An Old Leather Tunic";
	private static final String	stringRepr	= " LA";
	private static final int	price		= 2;
	private static final int	spawnChance	= 6;
	private static final int	defense		= 7;
	private static final char	type		= 'a';
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new leather armour item.
	 */
	public LeatherArmour() {
		super(name, stringRepr, price, spawnChance, defense, type);
	}
	
}
