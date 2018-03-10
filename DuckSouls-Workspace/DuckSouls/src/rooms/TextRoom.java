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
	
	// TODO: Refurbish file reading constructor
	// /**
	// * Creates a room from a specified file.
	// *
	// * @param name
	// * The name of the room. (Used for file i/o)
	// * @param fileName
	// * The file containing the data for the room.
	// */
	// public TextRoom(String name, String fileName) {
	//
	// // Read the file
	// String[] lines = Utilities.readLines(fileName);
	//
	// // Width of the room is the number of characters in a row, minus the walls
	// this.internalWidth = lines[0].split(",").length - 2;
	//
	// // Height of the room is the number of lines, minus the walls
	// this.internalHeight = lines.length - 2;
	//
	// // Generate an array for reading the file
	// String[][] textTileArray = new String[this.internalWidth +
	// 2][this.internalHeight + 2];
	//
	// // Generate the room's tile array
	// this.genTileArray();
	//
	// // Split all the lines of the file by commas to get the tile strings
	// for (int i = 0; i < this.internalHeight + 2; i++) {
	//
	// textTileArray[i] = lines[i].split(",");
	//
	// }
	//
	// // For each position in the room...
	// for (int y = 0; y < this.internalHeight + 2; y++) {
	// for (int x = 0; x < this.internalWidth + 2; x++) {
	//
	// // For each type of tile possible...
	// for (Tile tile : Tile.values()) {
	//
	// // If the file says there should be said tile there...
	// if (textTileArray[x][y].equals(tile.FILE_CHAR)) {
	//
	// // Place said tile
	// this.tileArray[x][y] = tile;
	//
	// // TODO: Delete?
	// // Record where the player was placed
	// // if (tile == Tile.PLAYER) {
	// // this.playerPosition = new Point(x, y);
	// // }
	//
	// // Stop searching through tile types
	// break;
	//
	// }
	//
	// }
	//
	// }
	//
	// }
	//
	// }
	
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
