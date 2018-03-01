package items;

public class Crouton extends Consumable {

	public Crouton() {
		super("A Soggy Crouton", " C ", 1, 30, 1, 0);
	}
	
	public Crouton(int x, int y, int id) {
		super("A Soggy Crouton", " C ", 1, 30, 1, 0, x, y, id);
	}
	
}
