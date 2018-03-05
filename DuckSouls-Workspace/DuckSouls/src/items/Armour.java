package items;

/**
 * This class represents all armour items in DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Colin Yeung
 * @version 1.2
 */
public class Armour extends Item {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new piece of armour.
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
	protected Armour(String name, String stringRepr, int price, int spawnChance, int defense) {
		
		super(name, stringRepr, price, spawnChance);
		this.defense = defense;
		
	}
	
	/**
	 * Creates a new piece of armour. Colin Edition.
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
	 * @param x
	 *            The x co-ordinate of the item.
	 * @param y
	 *            The y co-ordinate of the item.
	 * @param id
	 *            The ID of the item.
	 */
	protected Armour(String name, String stringRepr, int price, int spawnChance, int defense, int x, int y, int id) {
		
		super(name, stringRepr, price, spawnChance, x, y, id);
		this.defense = defense;
		
	}
	
}
