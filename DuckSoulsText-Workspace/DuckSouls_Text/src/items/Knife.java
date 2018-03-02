package items;

/**
 * This class represents the weapon item knife.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Knife extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "A Dull Butter Knife";
	private static String	stringRepr	= " K ";
	private static int		price		= 20;
	private static int		spawnChance	= 3;
	private static int		attack		= 10;
	private static int		accuracy	= 95;
	private static int		speed		= 90;
	private static int		critchance	= 10;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new knife item.
	 */
	public Knife() {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance);
	}
	
}
