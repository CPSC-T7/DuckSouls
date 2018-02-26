package tiles;

public class Door extends Tile {
	
	private String	key;
	private boolean	locked	= false;
	
	public Door() {
		super(" D ", true);
	}
	
	public Door(String key) {
		super(" D ", false);
		this.locked = true;
		this.key = key;
	}
	
	public boolean tryUnlock(String key) {
		if (key.equals(this.key)) {
			this.locked = false;
			this.canWalkOn = true;
			return true;
		} else {
			return false;
		}
	}

	public boolean isLocked() {
		return new Boolean(locked);
	}
	
	public String getKey() {
		return new String(this.key);
	}
	
}
