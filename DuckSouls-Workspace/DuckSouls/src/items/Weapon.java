package items;

/**
 * This class represents all weapon items in DuckSouls.
 * 
 * @author Matthew Allwright
 */
public class Weapon extends Item {
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new weapon item.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param stringRepr
	 *            The 3 character string representation of the weapon.
	 * @param price
	 *            The price of the weapon.
	 * @param spawnChance
	 *            The spawn chance of the weapon. Must be from 0 to 100.
	 * @param attack
	 *            The attack value of the weapon.
	 * @param accuracy
	 *            The accuracy of the weapon. Must be from 0 to 100.
	 * @param speed
	 *            The speed of the weapon. Must be from 0 to 100.
	 * @param critChance
	 *            The critical strike chance of the weapon. Must be from 0 to 100.
	 */
	protected Weapon(String name, String pathToImage, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed,
			int critChance) {
		
		super(name, pathToImage, stringRepr, price, spawnChance);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
	protected Weapon(String name, String pathToImage, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed,
			int critChance, int x, int y, int id) {
		
		super(name, pathToImage, stringRepr, price, spawnChance, x, y, id);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
}
