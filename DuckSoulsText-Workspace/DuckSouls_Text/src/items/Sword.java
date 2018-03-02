package items;

/**
 * This class represents the weapon item sword.
 * 
 * @author Matthew Allwright
 * @version 1.1
 */
public class Sword extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static String	name		= "A Cool Sword";
	private static String	stringRepr	= " S ";
	private static int		price		= 40;
	private static int		spawnChance	= 1;
	private static int		attack		= 20;
	private static int		accuracy	= 85;
	private static int		speed		= 80;
	private static int		critchance	= 15;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new sword item.
	 */
	public Sword() {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance);
	}
	
}
