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
	
	private static final String	name		= "A Soggy Crouton";
	private static final String	stringRepr	= " C ";
	private static final int	price		= 1;
	private static final int	spawnChance	= 30;
	private static final int	health		= 1;
	private static final int	mana		= 0;
	private static final char	type		= 'c';
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new croutons item.
	 */
	public Crouton() {
		super(name, stringRepr, price, spawnChance, health, mana, type);
	}
	
}
