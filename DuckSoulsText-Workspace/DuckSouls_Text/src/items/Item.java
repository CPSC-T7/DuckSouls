package items;

import java.util.ArrayList;

/**
 * Defines in game items and allows user to use those items in DuckSouls.
 * 
 * @author Cassondra Platel
 * @version 2.0.1
 */
/*
 * TODO: set stats for each item add private updateStat() method that gets/sets
 * stats --> switch statement? should reduce code duplication found in useItem()
 * add public hasWeapon,weaponName,hasArmor,armorName to Duck_Object.java add
 * getStat() and setStat to Duck_Object.java --> switch statements Create
 * Inventory parent with Store and Player child classes --> add
 * updateInventory() to reflect using/buying a consumable item test useItem() at
 * runtime
 */
public class Item {
	
	private ArrayList<Item>	allItems	= new ArrayList<Item>();
	
	// ATTRIBUTES
	
	private String			itemName;
	
	private boolean			canConsume;
	private boolean			isWeapon;
	private boolean			isArmor;
	
	private int				healthDamage;
	private int				manaDamage;
	private int				attack;
	private int				defense;
	private int				speed;
	private int				accuracy;
	private int				critChance;
	private int				price;
	
	// ENUMERATOR
	
	// define all items and their associated attributes
	public enum Items {
		
		/*
		 * order of values: (name,can_consume,is_weapon,is_armor,
		 * hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,price)
		 */
		
		// Consumables
		BUGS("De Bug", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		ET("Useless Game Cartridge", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		FISH("Smelly Fish", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		FOOD("Rotten Food", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		CRUTON("Soggy Cruton", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		ROIDS("Suspicious Liquid", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		WAX("Dr.Quakenstein's Wonderous Wing Wax!", true, false, false, 0, 0, 0, 0, 0, 0, 0, 0),
		
		// Weapons
		DAGGERS("Pointy Sticks", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		KNIVES("Razor Blades", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		BOW("Pew Pew", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		SWORD_SHIELD("Meat Shield", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		BIG_SWORD("Toy Sword", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		DUAL_WIELD("Broken Toothpick", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		STAFF("Magic Stick", false, true, false, 0, 0, 0, 0, 0, 0, 0, 0),
		
		// Armor
		WARRIOR_MAIL("Full Metal Jacket", false, false, true, 0, 0, 0, 0, 0, 0, 0, 0),
		ROGUE_LEATHERS("Mooskin", false, false, true, 0, 0, 0, 0, 0, 0, 0, 0),
		MAGE_ROBE("Ugly Christmas Sweater", false, false, true, 0, 0, 0, 0, 0, 0, 0, 0);
		
		// Enum Instance Variables
		private String	ITEM_NAME;
		
		private boolean	CAN_CONSUME;
		private boolean	IS_WEAPON;
		private boolean	IS_ARMOR;
		
		private int		HEALTH_DAM;
		private int		MANA_DAM;
		private int		ATTACK;
		private int		DEFENSE;
		private int		SPEED;
		private int		ACCURACY;
		private int		CRIT_CHANCE;
		private int		PRICE;
		
		// Constructor for enum Items
		Items(String ITEM_NAME, boolean CAN_CONSUME, boolean IS_WEAPON, boolean IS_ARMOR, int HEALTH_DAM, int MANA_DAM,
				int ATTACK, int DEFENSE, int SPEED, int ACCURACY, int CRIT_CHANCE, int PRICE) {
			
			this.ITEM_NAME = ITEM_NAME;
			this.CAN_CONSUME = CAN_CONSUME;
			this.IS_WEAPON = IS_WEAPON;
			this.IS_ARMOR = IS_ARMOR;
			this.HEALTH_DAM = HEALTH_DAM;
			this.MANA_DAM = MANA_DAM;
			this.ATTACK = ATTACK;
			this.DEFENSE = DEFENSE;
			this.SPEED = SPEED;
			this.ACCURACY = ACCURACY;
			this.CRIT_CHANCE = CRIT_CHANCE;
			this.PRICE = PRICE;
		}
	}
	
	/*
	 * // TESTING METHOD
	 * 
	 * public static void main(String[] args) {
	 * 
	 * Item itemSetup = new Item();
	 * 
	 * itemSetup.setupItems(); }
	 */
	
	// METHODS
	
	/**
	 * Creates and stores all in game items
	 *
	 */
	private void setupItems() {
		
		// iterate through enumeration variables
		for (Items item : Items.values()) {
			
			// create a new object each iteration
			Item newItem = new Item();
			
			newItem.itemName = item.ITEM_NAME;
			newItem.canConsume = item.CAN_CONSUME;
			newItem.isWeapon = item.IS_WEAPON;
			newItem.isArmor = item.IS_ARMOR;
			
			newItem.healthDamage = item.HEALTH_DAM;
			newItem.manaDamage = item.MANA_DAM;
			newItem.attack = item.ATTACK;
			newItem.defense = item.DEFENSE;
			newItem.speed = item.SPEED;
			newItem.accuracy = item.ACCURACY;
			newItem.critChance = item.CRIT_CHANCE;
			newItem.price = item.PRICE;
			
			// store object for each iteration
			allItems.add(newItem);
		}
	}// End of setupItems method
	
	/**
	 * Returns the index of a given item
	 *
	 * @param name
	 *            Name of the item
	 * @return allItems.indexOf(item) Index of named item
	 * @return -1 Placeholder, Compiler complains without this return statement
	 */
	private int getIndex(String name) {
		String objectName;
		
		for (Item item : allItems) {
			
			// retrieve the Item object name for each iteration
			objectName = item.itemName;
			
			// check if current iteration is desired Item object
			if (objectName.equalsIgnoreCase(name)) {
				
				return allItems.indexOf(item);
			}
		}
		return -1; // compiler complains if this return statment is left out
	}// End of getIndex method
	
	/**
	 * Use named item and update game state
	 *
	 * @param name
	 *            Name of item being used
	 * @param player
	 *            Player instance
	 */
	public void useItem(/* Duck_Object player, */String name) {
		
		// determine correct Item object
		int index = getIndex(name);
		
		// retrieve correct Item object
		Item item = allItems.get(index);
		
		/*
		 * //Check if weapon or armor already equipped if (player.hasWeapon == true ||
		 * player.hasArmor == true){
		 * 
		 * //check if trying to replace weapon if(player.hasWeapon == true &&
		 * item.isWeapon == true){ int index = getIndex(player.weaponName); Item
		 * wearableItem = allItems.get(index);
		 * 
		 * //remove weapon player.hasWeapon = false; }
		 * 
		 * //check if trying to replace armor else if (player.hasArmor == true &&
		 * item.isArmor == true){ int index = getIndex(player.armorName); Item
		 * wearableItem = allItems.get(index);
		 * 
		 * //remove armor player.hasArmor = false; }
		 * 
		 * //not trying to replace current equipped items else { break; //check failed,
		 * do not update with removal }
		 * 
		 * //check passed, update stats with removal of current wearable item int
		 * currentValue = player.getStats(attack); int newValue = (currentValue -=
		 * wearableItem.attack); player.setStats(attack,newValue);
		 * 
		 * currentValue = player.getStats(defense); newValue = (currentValue -=
		 * wearableItem.defense); player.setStats(defense,newValue);
		 * 
		 * currentValue = player.getStats(speed); newValue = (currentValue -=
		 * wearableItem.speed); player.setStats(speed,newValue);
		 * 
		 * currentValue = player.getStats(accuracy); newValue = (currentValue -=
		 * wearableItem.accuracy); player.setStats(accuracy,newValue); }
		 * 
		 * //Update player stats, dependent on item type if (item.canConsume == true){
		 * 
		 * int currentValue = player.getStats("healthPoints"); int newValue =
		 * (currentValue += item.healthDamage); player.setStats(health,newValue);
		 * 
		 * int currentValue = player.getStats("manaPoints"); int newValue =
		 * (currentValue += item.manaDamage); player.setStats(mana,newValue);
		 * 
		 * int currentValue = player.getStats("attack"); int newValue = (currentValue +=
		 * item.attack); player.setStats(attack,newValue);
		 * 
		 * int currentValue = player.getStats("defense"); int newValue = (currentValue
		 * += item.defense); player.setStats(defense,newValue);
		 * 
		 * int currentValue = player.getStats("speed"); int newValue = (currentValue +=
		 * item.speed); player.setStats(speed,newValue);
		 * 
		 * int currentValue = player.getStats("accuracy"); int newValue = (currentValue
		 * += item.accuracy); player.setStats(accuracy,newValue);
		 * 
		 * int currentValue = player.getStats("crit"); int newValue = (currentValue +=
		 * item.critChance); player.setStats(crit,newValue);
		 * 
		 * updateBackpack(item);
		 * 
		 * } else if(item.isWeapon == true){ player.hasWeapon = true; player.weaponName
		 * = item.itemName; //need to make a new string object
		 * 
		 * int currentValue = player.getStats(attack); int newValue = (currentValue +=
		 * item.attack); player.setStats(attack,newValue);
		 * 
		 * int currentValue = player.getStats(defense); int newValue = (currentValue +=
		 * item.defense); player.setStats(defense,newValue);
		 * 
		 * int currentValue = player.getStats(crit); int newValue = (currentValue +=
		 * item.critChance); player.setStats(crit,newValue);
		 * 
		 * } else { player.hasArmor = true; player.armorName = item.itemName; //need to
		 * make a new string object
		 * 
		 * int currentValue = player.getStats(defense); int newValue = (currentValue +=
		 * item.defense); player.setStats(defense,newValue);
		 * 
		 * int currentValue = player.getStats(speed); int newValue = (currentValue +=
		 * item.speed); player.setStats(speed,newValue);
		 * 
		 * int currentValue = player.getStats(accuracy); int newValue = (currentValue +=
		 * item.accuracy); player.setStats(accuracy,newValue); }
		 */
	}// End of useItem method
	
}// End of Item class