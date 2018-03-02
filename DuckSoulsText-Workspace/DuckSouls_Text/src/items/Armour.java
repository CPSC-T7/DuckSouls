package items;

/**
 * This class represents all armour items in DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.0.1
 */
public class Armour extends Item {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new peice of armour.
	 * 
	 * @param name
	 *            The name of the armour.
	 * @param stringRepr
	 *            The 3 character string representation of the armour.
	 * @param price
	 *            The price of the armour.
	 * @param spawnChance
	 *            The spawn chance of the armour. Must be from 0 to 100.
	 * @param defense
	 *            The defense that the armour provides.
	 */
	public Armour(String name, String stringRepr, int price, int spawnChance, int defense) {
		
		super(name, stringRepr, price, spawnChance);
		this.defense = defense;
		
	}
	
}
