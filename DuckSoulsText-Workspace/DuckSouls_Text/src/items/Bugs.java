package items;

public class Bugs extends Consumable {

	public Bugs() {
		super("De Bugs", " B ", 5, 50, 5, 5);
	}
	
	public Bugs(int x, int y, int id) {
		super("De Bugs", " B ", 5, 50, 5, 5, x, y, id);
	}
}
