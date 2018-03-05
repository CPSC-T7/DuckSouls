package old_map;

import utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import items.Clothes;
import items.Unarmed;
import old_entities.*;
import old_tiles.*;

/**
 * This class represents the map of Duck Souls. It loads and manages MapFile
 * objects to work with by doing things such as printing them to the console for
 * the user, and determining if an enemy is close to the player.
 * 
 * @see map.MapFile.java
 * @author Colin Yeung
 */
public class Map {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private ArrayList<ArrayList<Tile>>	currentMap_2DArrayList	= new ArrayList<ArrayList<Tile>>(0);
	private ArrayList<Entity>			characters_ArrayList	= new ArrayList<Entity>(0);
	// private ArrayList<Item> items_ArrayList = new ArrayList<Item>(0);
	private HashMap<String, Mapfile>	maps_HashMap			= new HashMap<String, Mapfile>();
	private Player						player					= new Player();
	private String						currentMapID;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a map object and adds a player.
	 */
	public Map() {
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Loads the maps within the specified range to a hash map. Then sets the
	 * current map to be the first map.
	 * 
	 * @param firstMapNum
	 *            The number of the first map.
	 * @param lastMapNum
	 *            The number of the last map.
	 */
	public void loadAllMapFiles(int firstMapNum, int lastMapNum) {
		
		// Clear and set the first map ID to the current map ID with 3 digits
		this.maps_HashMap.clear();
		this.currentMapID = String.format("%03d", firstMapNum);
		
		// For each ID from the first map to the last map...
		for (int i = firstMapNum; i <= lastMapNum; i++) {
			
			// Find the 3-digit number if the i-th map and the appropriate text file
			String mapID = String.format("%03d", i);
			String mapName = "../TextRooms/" + "map" + mapID + ".txt";
			
			// Put the map file and number in a hash map for ease of use
			this.maps_HashMap.put(mapID, new Mapfile(mapName));
			
		}
		
	} // End of loadAllMapFiles
	
	/**
	 * Load and fill the current map with enemies and tiles.
	 */
	public void loadCurrentMap() {
		
		// Fetch the current map's 2D array list from the hash map
		ArrayList<ArrayList<String>> mapData = this.maps_HashMap.get(this.currentMapID).getMap();
		
		// For each column of the map...
		for (int y = 0; y < mapData.size(); y++) {
			
			// Add a new list to hold the tile rows
			this.currentMap_2DArrayList.add(new ArrayList<Tile>(0));
			
			// For each character in a row of the map...
			for (int x = 0; x < mapData.get(y).size(); x++) {
				
				// Find which character should be in this position
				char type = mapData.get(y).get(x).charAt(0);
				
				// Assign the current position to the appropriate character
				switch (type) {
					
					case '@': // Player
						this.currentMap_2DArrayList.get(y).add(new Floor(x, y));
						this.player.setPos(x, y, currentMap_2DArrayList);
						this.maps_HashMap.get(this.currentMapID).removePlayerpoint();
						break;
					
					case 'E': // Enemy
						this.characters_ArrayList.add(this.loadEnemy(mapData.get(y).get(x), x, y));
						// No break to make sure to add a floor underneath the enemy
						
					case 'F': // Floor
						this.currentMap_2DArrayList.get(y).add(new Floor(x, y));
						break;
					
					case ' ': // Empty Tile
						this.currentMap_2DArrayList.get(y).add(new Tile(x, y));
						break;
					
					case 'D': // Door
						this.currentMap_2DArrayList.get(y).add(loadDoor(mapData.get(y).get(x).substring(1), x, y));
						break;
					
					case 'W': // Wall
						this.currentMap_2DArrayList.get(y).add(loadWall(mapData.get(y).get(x).substring(1), x, y));
						break;
						
					// case 'I':
					// this.currentMap_2DArrayList.get(y).add(new Floor(x, y));
					// this.items_ArrayList.add(this.loadItem(mapData.get(y).get(x), x, y));
					// break;
					
				}
				
			}
			
		}
		
	} // End of loadCurrentMap
	
	/**
	 * Loads a new map in to replace the current one.
	 * 
	 * @param mapID
	 *            The map ID of the map to load in.
	 */
	public void loadNewMap(String mapID) {
		// If the given map ID is in the hash map of maps
		if (this.maps_HashMap.containsKey(mapID)) {
			
			// Set the new map ID and store the old one
			String previousMapID = this.currentMapID;
			this.currentMapID = mapID;
			
			// Load the new map
			this.loadCurrentMap();
			
			// For each tile in each column
			for (ArrayList<Tile> column : this.currentMap_2DArrayList) {
				for (Tile spot : column) {
					
					// If the tile is a door and the tile is from the previous map...
					if (spot.getType() == "Door" && spot.getMapID().equals(previousMapID)) {
						
						// Find the co-ordinates of the tile
						int x = spot.getX();
						int y = spot.getY();
						
						// Place the player at the same position on the new map
						this.player.setPos(x, y, this.currentMap_2DArrayList);
						
					}
					
				}
				
			}
			
		}
		
	} // End of loadNewMap
	
	/**
	 * Clears the current map
	 */
	public void clearMap() {
		
		this.currentMap_2DArrayList.clear();
		
	}
	
	/**
	 * Clears all characters from the map.
	 */
	public void resetCharacters() {
		
		this.characters_ArrayList.clear();
		
	}
	
	/**
	 * Loads in data and co-ordinates and returns a new door created from the
	 * formatted data.
	 * 
	 * @param data
	 *            A string formatted "AB-S1-S2".
	 *            <ul>
	 *            <li>'A' is a character that, if equal to 'L', means the door is
	 *            locked.
	 *            <li>'B' is a character that, if equal to 'V', mean the door is
	 *            vertical.
	 *            <li>"S1" is a string that represents the key ID.
	 *            <li>"S2" is a string that represents the map ID.
	 *            </ul>
	 * @param x
	 *            The X co-ordinate of the door.
	 * @param y
	 *            The Y co-ordinate of the door.
	 * @return A new door with the specific values.
	 */
	/*
	 * TODO: Make input more clear. "LV-3-002" is a string that has a very obscure
	 * meaning if you haven't delved into the code to see how it works. Probably
	 * split isLocked, isVerical, keyID, and mapID into passed arguments, not
	 * derived from a specifically formatted string, but at that point this method
	 * would probably be redundant because all it does compared to just saying "new
	 * Door(...);" is format the obscure string.. Could also split this into
	 * "loadLockedDoor" and "loadUnlockedDoor", albeit with the same issue.
	 */
	public Door loadDoor(String data, int x, int y) {
		
		// Split the data string into separate arguments
		String[] arguments = data.split("-");
		
		// Name each argument
		char lockArg = arguments[0].charAt(0);
		char orientationArg = arguments[0].charAt(1);
		String keyID = arguments[1];
		String mapID = arguments[2];
		
		// Determine if the door is locked / vertical
		boolean isLocked = (lockArg == 'L');
		
		// Return a door made with all of the values
		return new Door(x, y, isLocked, mapID, orientationArg, keyID);
		
	} // End of loadDoor
	
	/**
	 * Loads in data and co-ordinates and returns a new wall created from the
	 * formatted data.
	 * 
	 * @param orient
	 *            A string representing which unicode box characters to use. Must be
	 *            one of: H, V, TL, TR, BL, or BR.
	 * @param x
	 *            The X co-ordinate of the wall.
	 * @param y
	 *            The Y co-ordinate of the wall.
	 * @return A new wall with the specific value.
	 */
	/*
	 * TODO: Isn't this method also redundant, in the same way that loadDoor(...)
	 * would be if you didn't have to format that string?
	 * 
	 * Why don't you just run "new Wall(args)" instead of "Map.loadWall(args)"?
	 */
	public Wall loadWall(String orient, int x, int y) {
		
		// Return a wall made with all of the values
		return new Wall(x, y, orient);
		
	} // End of loadWall
	
	public Enemy loadEnemy(String code, int x, int y) {
		String[] codeparts = code.split("-");
		return new Enemy(x, y, Integer.parseInt(codeparts[1]));
	}
	
	// public Item loadItem(String code, int x, int y) {
	// String[] data = code.split("-");
	// for(String it: data) {
	// System.out.println(it);
	// }
	// return new Item(Item.allItems[Integer.parseInt(data[1])], x, y,
	// Integer.parseInt(data[2]));
	// }
	
	/**
	 * Prints the current map to the console.
	 */
	public void print() {
		
		// Boolean to keep track of printing each character
		boolean printed = false;
		
		// For each x any y position in the current map...
		for (int y = 0; y < this.currentMap_2DArrayList.size(); y++) {
			for (int x = 0; x < this.currentMap_2DArrayList.get(y).size(); x++) {
				
				// Nothing has been printed yet
				printed = false;
				
				// For each character in the current map...
				for (Entity character : this.characters_ArrayList) {
					
					// If nothing has been printed yet...
					if (!printed) {
						
						// If the character is at the current position...
						if (character.getX() == x && character.getY() == y) {
							
							// Print the character
							System.out.print(character.getStringRepr());
							
							// Something has now been printed
							printed = true;
							
						}
						
					}
					
				}
				
				// for(Item item: this.items_ArrayList) {
				// if(!printed) {
				// if(item.getX() == x && item.getY()== y) {
				// System.out.print(item.getStringRepr());
				// printed = true;
				// }
				// }
				// }
				
				// If nothing has been printed yet (i.e. no character was at the position)...
				if (!printed) {
					
					// If the current position is the position of the player..
					if (y == this.player.getY() && x == this.player.getX()) {
						
						// Print the player
						System.out.print(player.getStringRepr());
					}
					
					// else print the tile
					else {
						System.out.print(this.currentMap_2DArrayList.get(y).get(x).getStringRepr());
					}
					
				}
				
			}
			
			// The entire row has been printed, wrap to a new line.
			System.out.println();
			
		}
		
	} // End of print
	
	/**
	 * Gets the current map hash map.
	 * 
	 * @return The hash map of the current map.
	 */
	/*
	 * TODO: Privacy leak. This returns a reference to a private variable.
	 */
	public ArrayList<ArrayList<Tile>> getMap() {
		
		return this.currentMap_2DArrayList;
		
	} // End of getMap
	
	/**
	 * Returns a boolean stating whether or not a particular tile can be moved to.
	 * 
	 * @param x
	 *            The X co-ordinate of the tile.
	 * @param y
	 *            The Y co-ordinate of the tile.
	 * @return A boolean stating whether the tile can be moved to.
	 */
	public boolean canMoveTo(int x, int y) {
		
		return this.currentMap_2DArrayList.get(y).get(x).canMove();
		
	} // End of canMoveTo
	
	/**
	 * Runs a loop to play the game.
	 */
	public void mainloop() {
		
		DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16);
		EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16);
		
		// Load the first 3 map files, and then the current (first) map
		this.loadAllMapFiles(0, 2);
		// this.loadCurrentMap();
		
		/*
		 * Main Game Loop:
		 * 
		 * Runs each turn and formats the console accordingly.
		 * 
		 * Exits after turn #21. TODO: Why?
		 */
		while (true) {
			
			// Run the turn
			this.runTurn();
			
			// Clear the console
			Utilities.clearConsole();
			
			// If an enemy is at the same location as the player...
			if (this.isEnemyNear() != -1) {
				
				// Clear the console and enter battle
				Utilities.clearConsole();
				BattleWorldTest.battleLoop(Player, Enemy, new Unarmed(), new Clothes());
				
				// Remove the enemy that is defeated in battle from both characters_ArrayList
				// and mapfile object for the current map
				this.maps_HashMap.get(currentMapID)
						.removeEnemy(this.characters_ArrayList.get(this.isEnemyNear()).getID());
				this.characters_ArrayList.remove(this.isEnemyNear());
				
				// Clear the console
				Utilities.clearConsole();
			}
			// if(this.isItemNear() != -1) {
			// this.player.addToInventory(this.items_ArrayList.get(this.isItemNear()));
			// this.maps_HashMap.get(currentMapID).removeItem(this.items_ArrayList.get(this.isItemNear()).getID());
			// this.items_ArrayList.remove(this.isItemNear());
			//
			// }
		}
		
	} // End of mainLoop
	
	/**
	 * Loads all map files from the first map to the last map, and then loads the
	 * current map (the first map loaded).
	 * 
	 * @param firstMapNum
	 *            The number of the first map.
	 * @param lastMapNum
	 *            The number of the last map.
	 */
	public void initalization(int firstMapNum, int lastMapNum) {
		
		this.loadAllMapFiles(firstMapNum, lastMapNum);
		this.loadCurrentMap();
		
	} // End of initialization
	
	/**
	 * Runs methods and checks that need to be run every turn for the map.
	 */
	public void runTurn() {
		
		// To keep track of the maps
		String mapID = new String();
		
		// To keep track of whether the player has moved between maps in this turn
		boolean hasMovedMaps = false;
		/*
		 * This is used to prevent the player landing on a door, which teleports them to
		 * another door in another room, which then teleports them back and so on and so
		 * forth forever. Once this is set to true, a player cannot teleport with a door
		 * again the the same turn (1 map change per turn).
		 */
		
		// Print the map
		this.print();
		this.player.move(this.currentMap_2DArrayList);
		// For each position on the map...
		for (int y = 0; y < this.currentMap_2DArrayList.size(); y++) {
			for (int x = 0; x < this.currentMap_2DArrayList.get(y).size(); x++) {
				
				// If the player is at that position and the position is a door...
				if (x == player.getX() && y == player.getY()
						&& currentMap_2DArrayList.get(y).get(x).getType().equals("Door")) {
					
					// If the character hasn't already been moved to a different map in this turn...
					if (!hasMovedMaps) {
						
						// Set the map ID to the map the door the character is standing on links to
						mapID = currentMap_2DArrayList.get(y).get(x).getMapID();
						
						// The player has now moved maps in this turn
						hasMovedMaps = true;
						
					}
					
				}
				
			}
			
		}
		
		// If the player is on a different map than they started on...
		if (hasMovedMaps) {
			
			// Clear the old map and setup the new map
			this.clearMap();
			this.resetCharacters();
			this.loadNewMap(mapID);
		}
		
	} // End of runTurn
	
	public void runTurn(String input) {

		DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16);
		EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16);
		
		// To keep track of the maps
		String mapID = new String();
		
		// To keep track of whether the player has moved between maps in this turn
		boolean hasMovedMaps = false;
		/*
		 * This is used to prevent the player landing on a door, which teleports them to
		 * another door in another room, which then teleports them back and so on and so
		 * forth forever. Once this is set to true, a player cannot teleport with a door
		 * again the the same turn (1 map change per turn).
		 */
		
		// Print the map
		if (!input.equals("!"))
			this.player.move(this.currentMap_2DArrayList, input);
		// For each position on the map...
		for (int y = 0; y < this.currentMap_2DArrayList.size(); y++) {
			for (int x = 0; x < this.currentMap_2DArrayList.get(y).size(); x++) {
				
				// If the player is at that position and the position is a door...
				if (x == player.getX() && y == player.getY()
						&& currentMap_2DArrayList.get(y).get(x).getType().equals("Door")) {
					
					// If the character hasn't already been moved to a different map in this turn...
					if (!hasMovedMaps) {
						
						// Set the map ID to the map the door the character is standing on links to
						mapID = currentMap_2DArrayList.get(y).get(x).getMapID();
						
						// The player has now moved maps in this turn
						hasMovedMaps = true;
						
					}
					
				}
				
			}
			
		}
		
		// If an enemy is at the same location as the player...
		if (this.isEnemyNear() != -1) {
			
			// Clear the console and enter battle
			// Utilities.clearConsole();
			BattleWorldTest.battleLoop(Player, Enemy, new Unarmed(), new Clothes());
			
			// Remove the enemy that is defeated in battle from both characters_ArrayList
			// and mapfile object for the current map
			this.maps_HashMap.get(currentMapID).removeEnemy(this.characters_ArrayList.get(this.isEnemyNear()).getID());
			this.characters_ArrayList.remove(this.isEnemyNear());
		}
		// if(this.isItemNear() != -1) {
		// this.player.addToInventory(this.items_ArrayList.get(this.isItemNear()));
		// this.maps_HashMap.get(currentMapID).removeItem(this.items_ArrayList.get(this.isItemNear()).getID());
		// this.items_ArrayList.remove(this.isItemNear());
		//
		// }
		
		// If the player is on a different map than they started on...
		if (hasMovedMaps) {
			
			// Clear the old map and setup the new map
			this.clearMap();
			this.resetCharacters();
			this.loadNewMap(mapID);
		}
		
	} // End of runTurn
	
	/**
	 * Returns the index of an character that is in the same loaction of a player.
	 * If there is no such character return -1.
	 * 
	 * @return The index of an character on the same tile as the player, if there is
	 *         not such character returns -1
	 * 
	 */
	public int isEnemyNear() {
		
		// setup index
		int index = 0;
		
		// For each character on the map...
		for (Entity character : this.characters_ArrayList) {
			
			// If the character is at the same location as the player...
			if (character.getX() == player.getX() && character.getY() == player.getY()) {
				
				// Return the index in the character_ArrayList corresponding to the enemy
				return index;
				
			}
			// increment index
			index += 1;
		}
		
		// Otherwise return -1
		return -1;
		
	} // End of isEnemyNear
	
	/**
	 * Returns a 3D arraylist of strings containing the path information for the
	 * game sprites
	 * 
	 * @param size
	 *            the dimensions of map to display
	 * @return a 3D arraylist of strings containing paths to the game sprites
	 */
	public ArrayList<ArrayList<ArrayList<String>>> getImages(int size) {
		
		ArrayList<ArrayList<ArrayList<String>>> images = new ArrayList<ArrayList<ArrayList<String>>>();
		int x = 0;
		int y = 0;
		
		for (int i = this.player.getY() - (size / 2); i <= this.player.getY() + (size / 2); i++) {
			images.add(new ArrayList<ArrayList<String>>());
			for (int j = this.player.getX() - (size / 2); j <= this.player.getX() + (size / 2); j++) {
				images.get(y).add(new ArrayList<String>());
				
				if (i < 0 || i >= this.currentMap_2DArrayList.size()) {
					images.get(y).get(x).add("Sprites/Tiles/Sewer/Empty.png");
				}
				
				else if (i >= 0 && i < this.currentMap_2DArrayList.size()) {
					if (j < 0 || j >= this.currentMap_2DArrayList.get(i).size()) {
						images.get(y).get(x).add("Sprites/Tiles/Sewer/Empty.png");
					} else if (j >= 0 && j < this.currentMap_2DArrayList.get(i).size()) {
						images.get(y).get(x).add(this.currentMap_2DArrayList.get(i).get(j).getImage());
						if (this.currentMap_2DArrayList.get(i).get(j).getImage() == (null)) {
							System.out.println(this.currentMap_2DArrayList.get(i).get(j).getType());
						}
						
					}
				}
				
				for (Entity character : this.characters_ArrayList) {
					if (character.getY() == i && character.getX() == j) {
						images.get(y).get(x).add(character.getImage());
					}
				}
				
				if (this.player.getY() == i && this.player.getX() == j) {
					images.get(y).get(x).add(this.player.getImage());
				}
				x += 1;
			}
			x = 0;
			y += 1;
		}
		
		// y = 0;
		// x = 0;
		// for(ArrayList<ArrayList<String>> column: images){
		// for(ArrayList<String> row: column){
		// System.out.println("column " + x + " row " + y);
		// for(String item:row) {
		// System.out.println(item);
		// }
		// System.out.println();
		// x += 1;
		// }
		// x = 0;
		// y += 1;
		// }
		
		return images;
		
	}
	
	// public int isItemNear() {
	//
	// // For each character on the map...
	// int index = 0;
	// for (Item item: this.items_ArrayList) {
	// // If the character is an enemy and the player is next to the enemy...
	// if (item.getX() == player.getX() && item.getY() == player.getY()) {
	//
	// // Return true
	// return index;
	//
	// }
	// index += 1;
	// }
	//
	// // Otherwise return false
	// return -1;
	//
	// } // End of isEnemyNear
	
}