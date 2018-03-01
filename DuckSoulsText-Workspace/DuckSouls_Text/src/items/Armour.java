package items;

public class Armour extends Item {
	
	public Armour(String name, String stringRepr, int price, int spawnChance, int defense) {
		
		super(name, stringRepr, price, spawnChance);
		this.defense = defense;
		
	}
	
	public Armour(String name, String stringRepr, int price, int spawnChance, int defense, int x, int y, int id) {
		
		super(name, stringRepr, price, spawnChance, x, y, id);
		this.defense = defense;
		
	}
	
}
