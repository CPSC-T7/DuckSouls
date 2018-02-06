import java.awt.*;

public class Tile {

	private Point pos = new Point();
	private boolean moveable;
	private String rep;

	public Tile(int x, int y, boolean movehere, String rep) {
		this.pos.setLocation(x, y);
		this.moveable = movehere;
		this.rep = rep;
	}

	public Tile(int x, int y) {
		this.moveable = false;
		this.rep = "   ";
		this.pos.setLocation(x, y);
	}

	public boolean CanMove() {
		return this.moveable;
	}

	public int getX() {
		return (int) this.pos.getX();
	}

	public int getY() {
		return (int) this.pos.getY();
	}

	public void setMovable(boolean moveable) {
		this.moveable = moveable;
	}

	public String Print() {
		return this.rep;
	}

	public String getType() {
		return "Empty";
	}

	public void setRep(String newRep) {
		this.rep = newRep;
	}

	public String getRep() {
		return this.rep;
	}
}
