package items;

public class Weapon extends Item {
	
	public Weapon(String name, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed, int critChance) {
		
		super(name, stringRepr, price, spawnChance);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
}
