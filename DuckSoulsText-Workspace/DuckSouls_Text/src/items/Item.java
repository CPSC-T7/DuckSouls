package items;

public class Item {
	
	public String	STRING_REPR = "IER";
	public int		SPAWN_CHANCE = 0;	// 0-99
	
	/**
	 * Creates an item.
	 * 
	 * @param STRING_REPR
	 *            The 3-character string used to print the tile.
	 */
	protected Item(String STRING_REPR, int SPAWN_CHANCE) {
		
		this.STRING_REPR = STRING_REPR;
		this.SPAWN_CHANCE = SPAWN_CHANCE;
		
	}

	public Item() {
		
		
	}
	
}
