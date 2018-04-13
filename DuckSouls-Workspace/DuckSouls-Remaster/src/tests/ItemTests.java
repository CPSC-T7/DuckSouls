package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;

/**
 * This class holds a bunch of tests to make sure that all items have values
 * within spec.
 */
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
	
	/**
	 * Anonymous inner class to fill the allItems list.
	 */
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
	
	/**
	 * Tests that all items have a string representation.
	 */
	@Test
	public static final void testAll_HasStringRepr() {
		
		for (Item item : allItems) {
			assertNotNull("Item must have non-null string representation. Failed on " + item, item.getStringRepr());
		}
		
	}
	
	/**
	 * Tests that all items' string representation is 3 characters long.
	 */
	@Test
	public static final void testAll_StringReprLength() {
		
		for (Item item : allItems) {
			assertEquals("Item file string must be 3 characters long. Failed on " + item, 3,
					item.getStringRepr().length());
		}
		
	}
	
	/**
	 * Tests that all items have a file string.
	 */
	@Test
	public static final void testAll_HasFileString() {
		
		for (Item item : allItems) {
			assertNotNull("Item must have non-null file string. Failed on " + item, item.getFileString());
		}
		
	}
	
	/**
	 * Tests that all items have a name.
	 */
	@Test
	public static final void testAll_HasName() {
		
		for (Item item : allItems) {
			assertNotNull("Item must have name. Failed on " + item, item.getName());
			assertTrue("Item must have non-empty name. Failed on " + item, item.getName() != "");
		}
		
	}
	
	/**
	 * Tests that all items have a spawn chance between 0 and 100 inclusive.
	 */
	@Test
	public static final void testAll_SpawnChanceWithinRange() {
		
		for (Item item : allItems) {
			assertTrue("Item must have spawn chance from 0 to 100. Failed on " + item,
					item.getSpawnChance() >= 0 && item.getSpawnChance() <= 100);
		}
		
	}
	
	/**
	 * Tests that all weapons have a speed statistic between 0 and 100 inclusive.
	 */
	@Test
	public static final void testWeapons_SpeedWithinRange() {
		
		for (Weapon weapon : allWeapons) {
			assertTrue("Weapon must have speed value from 0 to 100. Failed on " + weapon,
					weapon.getSpeed() >= 0 && weapon.getSpeed() <= 100);
		}
		
	}
	
}
