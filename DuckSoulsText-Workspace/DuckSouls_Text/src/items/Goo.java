package items;

public class Goo extends Consumable {

	public Goo() {
		super("Some Weird Goo", " G ", 10, 15, 0, 20);
	}
	
	public Goo(int x, int y, int id) {
		super("Some Weird Goo", " G ", 10, 15, 0, 20, x, y, id);
	}
	
}
