/**
 * Defines in game items for use in DuckSouls
 * 
 * @author Cassondra Platel
 * @version 1.2.0
 */
public class Items {

	//total number of components in each enumeration category
	//for use in array initializations during setup methods
	private static int numConsumables = 7;
	private static int numWeapons = 8;
	private static int numArmorSets = 1;

// ATTRIBUTES

	private String ITEM_NAME;

	private boolean CAN_EQUIP;
	private boolean CAN_CONSUME;

	private int HEALTH_DAM;
	private int MANA_DAM;
	private int ATTACK;
	private int DEFENSE;
	private int SPEED;
	private int ACCURACY;
	private int CRIT_CHANCE;
	private int PRICE;
	private int EFFECT_LENGTH;


// ENUMERATORS 

	//(name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)

	//define all consumable items and their associated attributes
	public enum Consumables {

		//order of values: (name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)
		BUGS("Bugs",false,true,0,0,0,0,0,0,0,0,0),
		ET("Useless Game Cartridge",false,true,0,0,0,0,0,0,0,0,0),
		FISH("Smelly Fish",false,true,0,0,0,0,0,0,0,0,0),
		FOOD("Rotten Food",false,true,0,0,0,0,0,0,0,0,0),	
		CRUTON("Soggy Cruton",false,true,0,0,0,0,0,0,0,0,0),
		ROIDS("Suspicious Liquid",false,true,0,0,0,0,0,0,0,0,0),
		WAX("Dr.Quakenstein's Wonderous Wing Wax!",false,true,0,0,0,0,0,0,0,0,0);
	

		private String ITEM_NAME;

		private boolean CAN_EQUIP;
		private boolean CAN_CONSUME;

		private int HEALTH_DAM;
		private int MANA_DAM;
		private int ATTACK;
		private int DEFENSE;
		private int SPEED;
		private int ACCURACY;
		private int CRIT_CHANCE;
		private int PRICE;
		private int EFFECT_LENGTH;


		Consumables(String ITEM_NAME, boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM,
					int ATTACK, int DEFENSE, int SPEED, int ACCURACY, int CRIT_CHANCE, int EFFECT_LENGTH, int PRICE) {

			this.ITEM_NAME = ITEM_NAME;

			this.CAN_EQUIP = CAN_EQUIP;
			this.CAN_CONSUME = CAN_CONSUME;

			this.HEALTH_DAM = HEALTH_DAM;
			this.MANA_DAM = MANA_DAM;
			this.ATTACK = ATTACK;
			this.DEFENSE = DEFENSE;
			this.SPEED = SPEED;
			this.ACCURACY = ACCURACY;
			this.CRIT_CHANCE = CRIT_CHANCE;
			this.EFFECT_LENGTH = EFFECT_LENGTH;
			this.PRICE = PRICE;		
		}
	}

	//define all weapons and their associated attributes
	public enum Weapons {
		
		//order of values: (name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)
		BARE_HANDS("Unarmed",true,false,0,0,0,0,0,0,0,0,0),
		DAGGERS("Bugs",true,false,0,0,0,0,0,0,0,0,0),
		KNIVES("Bugs",true,false,0,0,0,0,0,0,0,0,0),
		BOW("Bugs",true,false,0,0,0,0,0,0,0,0,0),
		
		SWORD_SHIELD("Bugs",true,false,0,0,0,0,0,0,0,0,0),
		BIG_SWORD("Bugs",true,false,0,0,0,0,0,0,0,0,0),
		DUAL_WIELD("Bugs",true,false,0,0,0,0,0,0,0,0,0),

		STAFF("name",true,false,0,0,0,0,0,0,0,0,0);


		private String ITEM_NAME;

		private boolean CAN_EQUIP;
		private boolean CAN_CONSUME;

		private int HEALTH_DAM;
		private int MANA_DAM;
		private int ATTACK;
		private int DEFENSE;
		private int SPEED;
		private int ACCURACY;
		private int CRIT_CHANCE;
		private int PRICE;
		private int EFFECT_LENGTH;


		Weapons(String ITEM_NAME, boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM,
					int ATTACK, int DEFENSE, int SPEED, int ACCURACY, int CRIT_CHANCE, int EFFECT_LENGTH, int PRICE) {

			this.ITEM_NAME = ITEM_NAME;

			this.CAN_EQUIP = CAN_EQUIP;
			this.CAN_CONSUME = CAN_CONSUME;

			this.HEALTH_DAM = HEALTH_DAM;
			this.MANA_DAM = MANA_DAM;
			this.ATTACK = ATTACK;
			this.DEFENSE = DEFENSE;
			this.SPEED = SPEED;
			this.ACCURACY = ACCURACY;
			this.CRIT_CHANCE = CRIT_CHANCE;
			this.EFFECT_LENGTH = EFFECT_LENGTH;
			this.PRICE = PRICE;		
		}
	}

	//define all armor sets and their associated attributes
	public enum Armor {
		
		//order of values: (name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)
		MAGE_ROBE("Magic Threads",true,false,0,0,0,0,0,0,0,0,0);


		private String ITEM_NAME;

		private boolean CAN_EQUIP;
		private boolean CAN_CONSUME;

		private int HEALTH_DAM;
		private int MANA_DAM;
		private int ATTACK;
		private int DEFENSE;
		private int SPEED;
		private int ACCURACY;
		private int CRIT_CHANCE;
		private int PRICE;
		private int EFFECT_LENGTH;


		Armor(String ITEM_NAME, boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM,
					int ATTACK, int DEFENSE, int SPEED, int ACCURACY, int CRIT_CHANCE, int EFFECT_LENGTH, int PRICE) {

			this.ITEM_NAME = ITEM_NAME;

			this.CAN_EQUIP = CAN_EQUIP;
			this.CAN_CONSUME = CAN_CONSUME;

			this.HEALTH_DAM = HEALTH_DAM;
			this.MANA_DAM = MANA_DAM;
			this.ATTACK = ATTACK;
			this.DEFENSE = DEFENSE;
			this.SPEED = SPEED;
			this.ACCURACY = ACCURACY;
			this.CRIT_CHANCE = CRIT_CHANCE;
			this.EFFECT_LENGTH = EFFECT_LENGTH;
			this.PRICE = PRICE;		
		}
	}


// METHODS

	/*public static void main(String[] args) {

		Items test = new Items();

		Items[] allConsumableItems = test.setupConsumableItems();

		for(int index = 0; index < allConsumableItems.length;index++){

			Items testArray = allConsumableItems[index];
			System.out.println(testArray.ITEM_NAME);
			System.out.println(testArray.CAN_EQUIP);
			System.out.println(testArray.CAN_CONSUME);
		}
		
		Items[] allWeapons = test.setupWeapons();

		for(int index = 0; index < allWeapons.length;index++){

			Items testArray = allWeapons[index];
			System.out.println(testArray.ITEM_NAME);
			System.out.println(testArray.CAN_EQUIP);
			System.out.println(testArray.CAN_CONSUME);
		}
	
		Items[] allArmorSets = test.setupArmorSets();

		for(int index = 0; index < allArmorSets.length;index++){

			Items testArray = allArmorSets[index];
			System.out.println(testArray.ITEM_NAME);
			System.out.println(testArray.CAN_EQUIP);
			System.out.println(testArray.CAN_CONSUME);
		}
	}*/

	/**
	 * Returns all in game consumable items.
	 * (Only invoke once during game setup)
	 * 
	 * @return allConsumableItems
	 *            Array of all consumable items
	 */
	public Items[] setupConsumableItems() {
		
		Items[] allConsumableItems = new Items[numConsumables];

		int index = 0;

		for (Consumables item : Consumables.values()){
			
			Items newItem = new Items();

			newItem.ITEM_NAME = item.ITEM_NAME;
			newItem.CAN_EQUIP = item.CAN_EQUIP;
			newItem.CAN_CONSUME = item.CAN_CONSUME;

			newItem.HEALTH_DAM = item.HEALTH_DAM;
			newItem.MANA_DAM = item.MANA_DAM;
			newItem.ATTACK = item.ATTACK;
			newItem.DEFENSE = item.DEFENSE;
			newItem.SPEED = item.SPEED;
			newItem.ACCURACY = item.ACCURACY;
			newItem.CRIT_CHANCE = item.CRIT_CHANCE;
			newItem.PRICE = item.PRICE;
			newItem.EFFECT_LENGTH = item.EFFECT_LENGTH;

			allConsumableItems[index] = newItem;
			index++;
		}
		return allConsumableItems;
	}

	/**
	 * Returns all in game weapons.
	 * (Only invoke once during game setup)
	 * 
	 * @return allWeapons
	 *            Array of all weapons
	 */
	public Items[] setupWeapons() {
		
		Items[] allWeapons = new Items[numWeapons];

		int index = 0;

		for (Weapons item : Weapons.values()){
			
			Items newItem = new Items();

			newItem.ITEM_NAME = item.ITEM_NAME;
			newItem.CAN_EQUIP = item.CAN_EQUIP;
			newItem.CAN_CONSUME = item.CAN_CONSUME;

			newItem.HEALTH_DAM = item.HEALTH_DAM;
			newItem.MANA_DAM = item.MANA_DAM;
			newItem.ATTACK = item.ATTACK;
			newItem.DEFENSE = item.DEFENSE;
			newItem.SPEED = item.SPEED;
			newItem.ACCURACY = item.ACCURACY;
			newItem.CRIT_CHANCE = item.CRIT_CHANCE;
			newItem.PRICE = item.PRICE;
			newItem.EFFECT_LENGTH = item.EFFECT_LENGTH;

			allWeapons[index] = newItem;
			index++;
		}
		return allWeapons;
	}
	
	/**
	 * Returns all armor sets.
	 * (Only invoke once during game setup)
	 * 
	 * @return allArmorSets
	 *            Array of all armor sets
	 */
	public Items[] setupArmorSets() {
		
		Items[] allArmorSets = new Items[numArmorSets];

		int index = 0;

		for (Armor item : Armor.values()){
			
			Items newItem = new Items();

			newItem.ITEM_NAME = item.ITEM_NAME;
			newItem.CAN_EQUIP = item.CAN_EQUIP;
			newItem.CAN_CONSUME = item.CAN_CONSUME;

			newItem.HEALTH_DAM = item.HEALTH_DAM;
			newItem.MANA_DAM = item.MANA_DAM;
			newItem.ATTACK = item.ATTACK;
			newItem.DEFENSE = item.DEFENSE;
			newItem.SPEED = item.SPEED;
			newItem.ACCURACY = item.ACCURACY;
			newItem.CRIT_CHANCE = item.CRIT_CHANCE;
			newItem.PRICE = item.PRICE;
			newItem.EFFECT_LENGTH = item.EFFECT_LENGTH;

			allArmorSets[index] = newItem;
			index++;
		}
		return allArmorSets;
	}

	/**public void useItem(Items item) {
	
		//check character inventory number before calling useItem --> place method call from in inventory class?
		
		if (item.CAN_CONSUME == true){
			player.health += this.HEALTH_DAM;
			player.mana += this.MANA_DAM;
			player.attack += this.ATTACK;
			player.defense += this.DEFENSE;
			player.speed += this.SPEED;
			player.accuracy += this.ACCURACY;
			player.critChance += this.CRIT_CHANCE;
		} else {
			enemy.health += this.HEALTH_DAM;
			enemy.mana += MANA_DAM;
		}
		
		//call method from inventory class to update held item count
	}*/
}