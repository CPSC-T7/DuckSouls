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
	
	private static final String	name		= "This is just my hand!";
	private static final String	stringRepr	= " U ";
	private static final int	price		= 0;
	private static final int	spawnChance	= 0;
	private static final int	attack		= 0;
	private static final int	accuracy	= 0;
	private static final int	speed		= 0;
	private static final int	critchance	= 0;
	private static final char	type		= 'w';
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new sword item.
	 */
	public Unarmed() {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance, type);
	}
	
}
