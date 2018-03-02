package items;

/**
 * This class represents an item in DuckSouls. The item has a name, string
 * representation, price, and spawn chance by default. Items can also have stat
 * modifiers that are put into place when used/equipped.
 * 
 * @author Matthew Allwright
 * @author Cassondra Platel
 * @version 2.2
 */
public class Item {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected String			stringRepr		= "IER";
	protected String			name;
	protected int				spawnChance		= 0;															// 0-99
	protected int				price;
	protected int				health;
	protected int				mana;
	protected int				attack;
	protected int				speed;
	protected int				accuracy;
	protected int				critChance;
	protected int				defense;
	
	// TODO: Make this better, again...
	public static final Item[]	allConsumables	= { new Bugs(), new Crouton(), new Food(), new Goo() };
	public static final Item[]	allWeapons		= { new Knife(), new Sword() };
	public static final Item[]	allArmour		= { new ClothArmour(), new LeatherArmour(), new MetalArmour() };
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new item with defined basic values.
	 * 
	 * @param name
	 *            The name of the item.
	 * @param stringRepr
	 *            The 3 character string used to print the item.
	 * @param price
	 *            The price of the item.
	 * @param spawnChance
	 *            The spawn chance of the item. Must be from 0 to 100
	 */
	protected Item(String name, String stringRepr, int price, int spawnChance) {
		
		this.name = name;
		this.stringRepr = stringRepr;
		this.price = price;
		this.spawnChance = spawnChance;
		
	}
	
	/**
	 * Copies an item.
	 * 
	 * @param item
	 *            The item to copy.
	 */
	public Item(Item item) {
		
		this.stringRepr = item.stringRepr;
		this.name = item.name;
		this.spawnChance = item.spawnChance;
		this.price = item.price;
		this.health = item.health;
		this.mana = item.mana;
		this.attack = item.attack;
		this.speed = item.speed;
		this.accuracy = item.accuracy;
		this.critChance = item.critChance;
		this.defense = item.defense;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns a copy of the item's name.
	 * 
	 * @return A copy of the item's name.
	 */
	public String getName() {
		
		return new String(this.name);
		
	}
	
	/**
	 * Returns the item's spawn chance.
	 * 
	 * @return The item's spawn chance.
	 */
	public int getSpawnChance() {
		
		return this.spawnChance;
		
	}
	
	/**
	 * Returns a copy of the item's string representation.
	 * 
	 * @return A copy of the item's string representation.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	}
	
}
