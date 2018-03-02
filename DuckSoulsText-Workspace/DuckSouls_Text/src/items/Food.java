package items;

/**
 * This class represents the consumable item food.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Food extends Consumable {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "Some Half-Eaten Food";
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
	 * Creates a new food item.
	 */
	public Food() {
		super(name, stringRepr, price, spawnChance, health, mana);
	}
	
}
