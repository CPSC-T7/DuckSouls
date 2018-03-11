package rooms;

/**
 * This class represents a text-based room in DuckSouls. Each room is a
 * rectangle of a desired width and height, and can hold one item and entity in
 * each inner tile.
 * 
 * @author Matthew Allwright
 * @author Nadhif Satriana
 * @version 1.11
 */
public class TextRoom extends Room {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final boolean	IS_GUI		= false;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public TextRoom(int size, int enemySpawnChance) {
		
		super(IS_GUI, size, enemySpawnChance);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Draws the room to the console.
	 */
	public void draw() {
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				if (this.entityArray[x][y] != null) { // If there is a enemy...
					
					// Print the entity
					System.out.print(this.entityArray[x][y].getStringRepr());
					
				} else if (this.itemArray[x][y] != null) { // Or if there is an item...
					
					// Print the item
					System.out.print(this.itemArray[x][y].getStringRepr());
					
				} else { // Otherwise just print the tile
					
					// Print the tile
					System.out.print(this.tileArray[x][y].getStringRepr());
					
				}
				
			}
			
			// Row has been printed, wrap the line
			System.out.println();
			
		}
		
	}
	
}
