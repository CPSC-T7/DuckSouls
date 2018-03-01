package items;

public class Item {
	
	protected String			stringRepr		= "IER";
	protected String			name;
	protected int				spawnChance		= 0;																// 0-99
	protected int				price;
	protected int				health;
	protected int				mana;
	protected int				attack;
	protected int				speed;
	protected int				accuracy;
	protected int				critChance;
	protected int				defense;
	private int                  x;
	private int                  y;
	private int                  id;
	
	// TODO: Make this better
	public static final Item[]	allItems	= { new Bugs(), new Crouton(), new Food(), new Goo(), new Knife(),
			new Sword(), new ClothArmour(), new LeatherArmour(), new MetalArmour() };
	
	/**
	 * Creates an item.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the tile.
	 */
	protected Item(String name, String stringRepr, int price, int spawnChance) {
		
		this.name = name;
		this.stringRepr = stringRepr;
		this.price = price;
		this.spawnChance = spawnChance;
		
	}
	
	protected Item(String name, String stringRepr, int price, int spawnChance, int x, int y, int id) {
		
		this.name = name;
		this.stringRepr = stringRepr;
		this.price = price;
		this.spawnChance = spawnChance;
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	
	
	/**
	 * Copies an item.
	 * 
	 * @param item
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
		this.x = item.x;
		this.y = item.y;
		this.id = item.id;
	}
	
	public Item(Item item, int x, int y, int id) {
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
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public Item() {
		
	}
	
	public String getStringRepr() {
		return new String(this.stringRepr);
	}
	
	public String getName() {
		return new String(this.name);
	}
	
	public int getSpawnChance() {
		return new Integer(this.spawnChance);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getID() {
		return this.id;
	}
}
