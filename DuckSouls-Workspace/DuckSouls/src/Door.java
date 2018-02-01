import java.util.ArrayList;

public class Door extends Tile {
	private boolean locked = false;
	private String mapID = new String();
	private String keyID = new String();

	public Door(int x, int y, boolean locked, String mapID, boolean vertical, String keyID) {
		super(x, y, false, " D ");
		this.locked = locked;
		this.setMovable(!this.locked);
		this.mapID = mapID;
		this.keyID = keyID;
		if (vertical) {
			this.setRep("D");
		}
	}

	public String getType() {
		return "Door";
	}

	public void unlock() {
		this.locked = false;
		this.setMovable(!this.locked);
	}

	public String getMapID() {
		return mapID;
	}

	public boolean canUnlock(ArrayList<String> keyIDS) {
		for (int i = 0; i < keyIDS.size(); i++) {
			if (keyIDS.get(i) == keyID)
				return true;
		}
		return false;
	}

}
