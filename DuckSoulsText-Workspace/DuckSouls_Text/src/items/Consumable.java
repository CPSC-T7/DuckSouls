package items;

public class Consumable extends Item {
	
	public Consumable(String name, String stringRepr, int price, int spawnChance, int health, int mana) {
		
		super(name, stringRepr, price, spawnChance);
		this.health = health;
		this.mana = mana;
		
	}
	
	public Consumable(String name, String stringRepr, int price, int spawnChance, int health, int mana, int x, int y, int id) {
		
		super(name, stringRepr, price, spawnChance, x ,y, id);
		this.health = health;
		this.mana = mana;
		
	}
	
}
