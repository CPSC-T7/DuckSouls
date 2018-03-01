package items;

public class Sword extends Weapon {

	public Sword() {
		super("A Cool Sword", " S ", 40, 1, 20, 85, 80, 15);
	}
	
	public Sword(int x, int y, int id) {
		super("A Cool Sword", " S ", 40, 1, 20, 85, 80, 15, x, y, id);
	}

}
