package items;

/**
 * This class represents the consumable item food.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Fish extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "A Half-Eaten Fish";
	private static final String	stringRepr	= " F ";
	private static final int	price		= 20;
	private static final int	spawnChance	= 8;
	private static final int	health		= 15;
	private static final int	mana		= 0;
	
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
