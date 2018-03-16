package items;

/**
 * This class represents the weapon item sword.
 * 
 * @author Rahmanta Satriana
 * @version 1.1.1
 */
public class Unarmed extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "My Handy-Dandy Wing";
	private static final String	stringRepr	= " U ";
	private static final int	price		= 0;
	private static final int	spawnChance	= 0;
	private static final int	attack		= 0;
	private static final int	accuracy	= 0;
	private static final int	speed		= 0;
	private static final int	critchance	= 0;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new sword item.
	 */
	public Unarmed() {
		super(name, null, stringRepr, price, spawnChance, attack, accuracy, speed, critchance);
	}
	
}