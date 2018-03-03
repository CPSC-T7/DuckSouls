package items;

/**
 * This class represents the consumable item food.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Fish extends Consumable {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "A Half-Eaten Fish";
	private static String	stringRepr	= " F ";
	private static int		price		= 20;
	private static int		spawnChance	= 8;
	private static int		health		= 15;
	private static int		mana		= 0;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new fish item.
	 */
	public Fish() {
		super(name, stringRepr, price, spawnChance, health, mana);
	}
	
}
