package items;

/**
 * This class represents all weapon items in DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.0.1
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
	 *            The speed of the weapon.
	 * @param critChance
	 *            The critical strike chance of the weapon. Must be from 0 to 100.
	 * @param type
	 * 			  The type of item it is
	 */
	public Weapon(String name, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed,
			int critChance, char type) {
		
		super(name, stringRepr, price, spawnChance, type);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
	
}
