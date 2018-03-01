package items;

public class Knife extends Weapon {

	public Knife() {
		super("A Dull Butter Knife", " K ", 20, 3, 10, 90, 90, 10);
	}
	
	public Knife(int x, int y, int id) {
		super("A Dull Butter Knife", " K ", 20, 3, 10, 90, 90, 10, x, y, id);
	}
	
}
