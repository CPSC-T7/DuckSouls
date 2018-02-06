package tiles;
public class Floor extends Tile {

	public Floor(int x, int y) {
		super(x, y, true, " . ");
	}

	public String getType() {
		return "Floor";
	}

}
