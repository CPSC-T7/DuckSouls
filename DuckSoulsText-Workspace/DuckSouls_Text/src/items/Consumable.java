package items;

/**
 * This class represents all consumable items in DuckSouls.
 * 
 * @author Matthew Allwright.
 * @version 1.0.1
 */
public class Consumable extends Item {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new consumable item.
	 * 
	 * @param name
	 *            The name of the consumable.
	 * @param stringRepr
	 *            The 3 character string representation of the consumable.
	 * @param price
	 *            The price of the consumable.
	 * @param spawnChance
	 *            The spawn chance of the consumable. Must be from 0 to 100.
	 * @param heath
	 *            The health modifier of the item upon use.
	 * @param mana
	 *            The mana modifier of the item upon use.
	 * @param type
	 * 			  The type of item it is
	 */
	public Consumable(String name, String stringRepr, int price, int spawnChance, int health, int mana, char type) {
		
		super(name, stringRepr, price, spawnChance, type);
		this.health = health;
		this.mana = mana;
		
	}
	
}
