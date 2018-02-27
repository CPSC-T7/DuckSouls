package items;

public class Consumable extends Item {
	
	public Consumable(String name, String stringRepr, int price, int spawnChance, int health, int mana) {
		
		super(name, stringRepr, price, spawnChance);
		this.health = health;
		this.mana = mana;
		
	}
	
}
