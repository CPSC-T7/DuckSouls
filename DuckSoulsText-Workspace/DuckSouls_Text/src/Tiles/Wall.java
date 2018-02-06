package Tiles;
public class Wall extends Tile {

	private String orientation;

	public Wall(int x, int y, String orientation) {
		super(x, y);
		this.setMovable(false);
		switch (orientation) {
		case "V":
			this.setRep("║");
			break;
		case "H":
			this.setRep("═══");
			break;
		case "TR":
			this.setRep("╗");
			break;
		case "TL":
			this.setRep("╔");
			break;
		case "BL":
			this.setRep("╚");
			break;
		case "BR":
			this.setRep("╝");
			break;
		}
		this.orientation = orientation;
	}

	public String getType() {
		return "Wall";
	}

}
