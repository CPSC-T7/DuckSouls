package items;

public class Weapon extends Item {
	
	public Weapon(String name, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed, int critChance) {
		
		super(name, stringRepr, price, spawnChance);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
public Weapon(String name, String stringRepr, int price, int spawnChance, int attack, int accuracy, int speed, int critChance, int x, int y, int id) {
		
		super(name, stringRepr, price, spawnChance, x, y, id);
		this.attack = attack;
		this.accuracy = accuracy;
		this.speed = speed;
		this.critChance = critChance;
		
	}
	
}
