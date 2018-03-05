package items;

/**
 * This class represents the weapon item sword.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Sword extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "A Cool Sword";
	private static final String	stringRepr	= " S ";
	private static final int	price		= 40;
	private static final int	spawnChance	= 1;
	private static final int	attack		= 4;
	private static final int	accuracy	= 10;
	private static final int	speed		= 2;
	private static final int	critchance	= 1;
	private static final char	type		= 'w';
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new sword item.
	 */
	public Sword() {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance, type);
	}
	
}
