package items;

/**
 * This class defines the consumable item bugs.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Bugs extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "De Bugs";
	private static final String	stringRepr	= " B ";
	private static final int	price		= 5;
	private static final int	spawnChance	= 50;
	private static final int	health		= 5;
	private static final int	mana		= 5;
	
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
	
	public Bugs(int x, int y, int id) {
		super(name, stringRepr, price, spawnChance, health, mana, x, y, id);
	}
	
}
