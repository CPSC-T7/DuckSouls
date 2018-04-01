package world;

import java.awt.Point;
import java.util.ArrayList;

import entities.Enemy;
import entities.Player;
import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;
import tiles.Door;
import tiles.GeneralTile;
import tiles.Tile;
import tiles.Wall;
import utils.Utilities;

public class RoomReader {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Builds a room from a text file.
	 * 
	 * @param fileName
	 *            The name of the file which contains the room data.
	 * @param levelNum
	 *            The level number to scale the entities with.
	 * @return A room built from the data.
	 */
	public Room loadRoomFromTextFile(String fileName, int levelNum) {
		
		// Read the lines from the file
		String[] fileLines = Utilities.readLines(fileName);
		
		// Container variables
		String[] lineBits;
		String line;
		String str;
		
		// Arrays to fill
		ArrayList<ArrayList<Tile>> tileList = new ArrayList<ArrayList<Tile>>();
		Tile[][] tileArray = null;
		Item[][] itemArray = null;
		
		// Entity containers
		Player player = null;
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		
		/*
		 * Sections:
		 * 0 --> Tiles
		 * 1 --> Items
		 * 2 --> Entities
		 */
		int section = 0;
		
		// For each file in the line
		for (int y = 0; y < fileLines.length; y++) {
			
			/*
			 * WE HAVE COMMENTS INTEGRATED! WOO!
			 * (But only on whole lines...)
			 * (Also, blank line are ignored!)
			 */
			if (fileLines[y].charAt(0) == ';' || fileLines[y].equals("")) {
				continue;
			}
			
			/*
			 * Breaks the array into 3 sections. The separator is 12 '=' on a line.
			 */
			if (fileLines[y].equals("============")) {
				
				/*
				 * When moving from the tile section to the item section, finalize the tile
				 * list into an array.
				 */
				if (section == 0) {
					tileArray = tileList.toArray(tileArray);
					itemArray = new Item[tileArray.length][tileArray[0].length];
				}
				
				section++;
				continue;
				
			}
			
			// Remove the spaces
			line = fileLines[y].replaceAll(" ", "");
			
			// Depending on the section...
			switch (section) {
				
				/*
				 * Tiles
				 */
				case 0:
					
					lineBits = line.split(",");
					tileList.add(new ArrayList<Tile>());
					
					for (int x = 0; x < lineBits.length; x++) {
						
						str = lineBits[x];
						
						// Search for walls
						for (Wall wall : Wall.values()) {
							if (str.equals(wall.getFileString())) {
								tileList.get(x).add(wall);
								break;
							}
						}
						
						// Search for general tiles
						for (GeneralTile tile : GeneralTile.values()) {
							if (str.equals(tile.getFileString())) {
								tileList.get(x).add(tile);
								break;
							}
						}
						
						// Search for doors
						for (Door door : Door.values()) {
							if (str.equals(door.getFileString())) {
								tileList.get(x).add(door);
								break;
							}
						}
						
					}
					
					break;
				
				/*
				 * Items
				 */
				case 1:
					
					lineBits = line.split(",");
					
					for (int x = 0; x < lineBits.length; x++) {
						
						str = lineBits[x];
						
						// Search for consumables
						for (Consumable consumable : Consumable.values()) {
							if (str.equals(consumable.getFileString())) {
								itemArray[x][y] = consumable;
								break;
							}
						}
						
						// Search for weapons
						for (Weapon weapon : Weapon.values()) {
							if (str.equals(weapon.getFileString())) {
								itemArray[x][y] = weapon;
								break;
							}
						}
						
						// Search for armour
						for (Armour armour : Armour.values()) {
							if (str.equals(armour.getFileString())) {
								itemArray[x][y] = armour;
								break;
							}
						}
						
					}
					
					break;
				
				/*
				 * Entities
				 */
				case 2:
					
					String[] params = line.split(":")[1].split(",");
					
					Point position = new Point();
					position.x = Integer.parseInt(params[0]);
					position.y = Integer.parseInt(params[1]);
					
					if (line.charAt(0) == 'P') {
						
						player = new Player(position);
						
					} else if (line.charAt(0) == 'E') {
						
						if (params.length == 2) {
							
							enemyList.add(new Enemy(position, levelNum));
							
						} else if (params.length == 3) {
							
							enemyList.add(new Enemy(position, Integer.parseInt(params[2])));
							
						}
						
					}
					
					break;
				
			}
			
		}
		
		return new Room(tileArray, itemArray, player, enemyList, levelNum);
		
	}
	
}
