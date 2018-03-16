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
	 */
	protected Consumable(String name, String pathToImage, String stringRepr, int price, int spawnChance, int health, int mana) {
		
		super(name, pathToImage, stringRepr, price, spawnChance);
		this.health = health;
		this.mana = mana;
		
	}
	
	protected Consumable(String name, String pathToImage, String stringRepr, int price, int spawnChance, int health, int mana, int x, int y, int id) {
		
		super(name, pathToImage, stringRepr, price, spawnChance, x ,y, id);
		this.health = health;
		this.mana = mana;
		
	}
	
}
