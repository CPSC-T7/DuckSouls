package items;

public class Armour extends Item {
	
	public Armour(String name, String stringRepr, int price, int spawnChance, int defense) {
		
		super(name, stringRepr, price, spawnChance);
		this.defense = defense;
		
	}
	
}
