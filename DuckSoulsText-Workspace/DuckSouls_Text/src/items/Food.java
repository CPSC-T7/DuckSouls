package items;

public class Food extends Consumable {

	public Food() {
		super("Some Half-Eaten Food", " F ", 20, 8, 15, 0);
	}
	
	public Food(int x, int y, int id) {
		super("Some Half-Eaten Food", " F ", 20, 8, 15, 0, x ,y, id);
	}
	
}