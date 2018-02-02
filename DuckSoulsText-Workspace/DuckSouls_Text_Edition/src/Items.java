public class Items {

// ATTRIBUTES




// CONSTRUCTOR

	public Items(){
			for (Consumable item : Consumable.values()){
			System.out.println(item);
	}
	}
// ENUMERATORS 

	//(name,can_equip,can_consume,hp_damage,mp_damage,attack,defense,speed,accuracy,crit_chance,length_of_effect,price)

	public enum Consumable {

		BUGS_0("Bugs",false,true,0,0,0,0,0,0,0,0,0),
		ET_1("Useless Game Cartridge",false,true,0,0,0,0,0,0,0,0,0),
		FISH_2("Smelly Fish",false,true,0,0,0,0,0,0,0,0,0),
		FOOD_3 ("Rotten Food",false,true,0,0,0,0,0,0,0,0,0),	
		CRUTON_4("Soggy Cruton",false,true,0,0,0,0,0,0,0,0,0),
		ROIDS_5("Suspicious Liquid",false,true,0,0,0,0,0,0,0,0,0),
		WAX_6("Dr.Quakenstein's Wonderous Wing Wax!",false,true,0,0,0,0,0,0,0,0,0);
	

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


	Consumable(String ITEM_NAME, boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM,
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

	public enum Weapon {

		BARE_HANDS,

		DAGGERS,
		KNIVES,
		BOW,
		
		SWORD_SHIELD,
		BIG_SWORD,
		DUAL_WIELD,

		STAFF;
	}

	public enum Armor {

		PLACEHOLDER;
	}

// METHODS
/*
	public static void main(String[] args) {

		Items test = new Items();

		}
	}
*/
	/** Create all game item objects
		Call once from main method during game initialization*/
	/*public static void setupItems() {
		
		int placeholder = 1;
	}
	
	public void useItem() {
		int placeholder = 2;
		//check character inventory number? maybe this step should be done before calling useItem --> method in inventory class
		
		//update HP/MP/Stats
		
		//call method from inventory class to update held item count
	}*/
}