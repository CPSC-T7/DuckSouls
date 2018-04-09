package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;

public final class ItemTests {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	private static final Armour[]			allArmour		= Armour.values();
	private static final Consumable[]		allConsumables	= Consumable.values();
	private static final Weapon[]			allWeapons		= Weapon.values();
	private static final ArrayList<Item>	allItems		= new ArrayList<Item>();
	
	static {
		
		for (Armour armour : allArmour) {
			allItems.add(armour);
		}
		for (Consumable consumable : allConsumables) {
			allItems.add(consumable);
		}
		for (Weapon weapon : allWeapons) {
			allItems.add(weapon);
		}
		
	}
	
	/*
	 * 
	 * TESTS
	 * 
	 */
	
	@Test
	private static final void testStringReprLength() {
		
		for (Item item : allItems) {
			assertEquals("Item file string must be 3 characters long.", 3, item.getStringRepr().length());
		}
		
	}
	
}
