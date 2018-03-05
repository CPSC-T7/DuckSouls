package items;

/**
 * This class represents the consumable item goo.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Goo extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "Some Weird Goo";
	private static final String	stringRepr	= " G ";
	private static final int	price		= 10;
	private static final int	spawnChance	= 15;
	private static final int	health		= 0;
	private static final int	mana		= 20;
	
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
	
	public Goo(int x, int y, int id) {
		super(name, stringRepr, price, spawnChance, health, mana, x, y, id);
	}
	
}
