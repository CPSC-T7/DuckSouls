public class Items {

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


// CONSTRUCTOR

	public Items(String ITEM_NAME, boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM,
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
	} // End of constructor


// ENUMERATORS 

	//(name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)

	public enum Consumable {

		BUGS("Bugs",false,true,10,0,0,0,0,0,5),
		ET("Useless Game Cartridge",false,true,0,0,0,0,0,0,75),
		FISH("Smelly Fish",false,true,75,0,15),
		FOOD ("Rotten Food",false,true,25,-10,15),	
		ROIDS("Suspicious Liquid",false,true,0,0,0,20,20,5,10),
		WAX  ("Dr.Quakenstein's Wonderous Wing Wax!",false,true,75,0,15);
	}

	public enum Weapon {

		BARE_HANDS,

		DAGGERS,
		KNIVES,
		BOW,
		
		SWORD_SHIELD,
		BIG_SWORD,
		CLEAVERS,

		STAFF;
	}

	public enum Armor {

		PLACEHOLDER;
	}

// METHODS

	/** Create all game item objects
		Call once from main method during game initialization*/
	public void setupItems() {
		
		//loop through enumerators initializing each object
	}
	
	public void useItem(Item item) {

		//check character inventory number? maybe this step should be done before calling useItem --> method in inventory class
		
		//update HP/MP/Stats
		
		//call method from inventory class to update held item count
	}
}// End of Class