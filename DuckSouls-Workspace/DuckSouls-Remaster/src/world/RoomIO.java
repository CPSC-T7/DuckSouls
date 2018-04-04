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

public class RoomIO {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String WORLD_FOLDER_PATH = "../Levels/";
	
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
	public static Room loadRoomFromTextFile(String fileName, int levelNum) {
		
		// Read the lines from the file
		String[] fileLines = Utilities.readLines(fileName);
		
		// Container variables
		String[] lineBits;
		String line;
		String str;
		int width = 0;
		int height = 0;
		int y = 0;
		
		// Arrays to fill
		Tile[][] tileArray = null;
		Item[][] itemArray = null;
		
		// Entity containers
		Player player = null;
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		
		/*
		 * Sections:
		 * 0 --> Room Data
		 * 1 --> Tiles
		 * 2 --> Items
		 * 3 --> Entities
		 */
		int section = 0;
		
		// For each file in the line
		for (int lineNum = 0; lineNum < fileLines.length; lineNum++) {
			
			// Remove the spaces
			line = fileLines[lineNum].replaceAll(" ", "");
			
			/*
			 * WE HAVE COMMENTS INTEGRATED! WOO!
			 * (But only on whole lines...)
			 * (Also, blank line are ignored!)
			 */
			if (line.length() == 0 || line.charAt(0) == ';') {
				continue;
			}
			
			/*
			 * Breaks the array into 3 sections. The separator is 12 '=' on a line.
			 */
			if (line.equals("============")) {
				System.out.println("------ SECTION ADVANCE ------");
				section++;
				y = 0;
				continue;
			}
			
			System.out.println("Parsing line #" + y + " : [" + line + "]");
			
			// Depending on the section...
			switch (section) {
				
				/*
				 * Room Data
				 */
				
				case 0:
					
					lineBits = line.split(",");
					width = Integer.parseInt(lineBits[0]);
					height = Integer.parseInt(lineBits[1]);
					
					tileArray = new Tile[width][height];
					itemArray = new Item[width][height];
					
					break;
				
				/*
				 * Tiles
				 */
				case 1:
					
					lineBits = line.split(",", -1);
					
					for (int x = 0; x < width; x++) {
						
						str = lineBits[x];
						
						if (str == null || str.equals("")) {
							continue;
						}
						
						// Search for walls
						for (Wall wall : Wall.values()) {
							if (str.equals(wall.getFileString())) {
								tileArray[x][y] = wall;
								break;
							}
						}
						
						// Search for general tiles
						for (GeneralTile tile : GeneralTile.values()) {
							if (str.equals(tile.getFileString())) {
								tileArray[x][y] = tile;
								break;
							}
						}
						
						// Search for doors
						for (Door door : Door.values()) {
							if (str.equals(door.getFileString())) {
								tileArray[x][y] = door;
								break;
							}
						}
						
					}
					
					break;
				
				/*
				 * Items
				 */
				case 2:
					
					lineBits = line.split(",", -1);
					
					for (int x = 0; x < width; x++) {
						
						str = lineBits[x];
						
						System.out.println("'" + str + "'");
						
						if (str == null || str.equals("")) {
							continue;
						}
						
						// Search for consumables
						for (Consumable consumable : Consumable.values()) {
							if (consumable.getFileString() != null && str.equals(consumable.getFileString())) {
								itemArray[x][y] = consumable;
								break;
							}
						}
						
						// Search for weapons
						for (Weapon weapon : Weapon.values()) {
							if (weapon.getFileString() != null && str.equals(weapon.getFileString())) {
								itemArray[x][y] = weapon;
								break;
							}
						}
						
						// Search for armour
						for (Armour armour : Armour.values()) {
							if (armour.getFileString() != null && str.equals(armour.getFileString())) {
								itemArray[x][y] = armour;
								break;
							}
						}
						
					}
					
					break;
				
				/*
				 * Entities
				 */
				case 3:
					
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
			
			y++;
			
		}
		
		System.out.println(width);
		System.out.println(height);
		System.out.println(tileArray);
		System.out.println(itemArray);
		System.out.println(player);
		System.out.println(enemyList);
		System.out.println(levelNum);
		
		return new Room(width, height, tileArray, itemArray, player, enemyList, levelNum);
		
	}
	
	/**
	 * Loads a story room from the specific folder/file.
	 * 
	 * @param levelNum
	 *            The level number.
	 * @param roomPoint
	 *            The point in the level that the room is located with.
	 * @return The specified room.
	 */
	public static Room loadStoryRoom(int levelNum, Point roomPoint) {
		
		String dir = WORLD_FOLDER_PATH + "Story/Level-" + levelNum + "/";
		String fileName = "Room-" + roomPoint.x + "-" + roomPoint.y + ".txt";
		
		return loadRoomFromTextFile(dir + fileName, levelNum);
		
	}
	
	public static void saveRoomToTextFile(String fileName, Room room) {
		
		ArrayList<String> tilesLines = new ArrayList<String>();
		ArrayList<String> itemsLines = new ArrayList<String>();
		ArrayList<String> entitiesLines = new ArrayList<String>();
		ArrayList<String> totalLines = new ArrayList<String>();
		String tilesLine = "";
		String itemsLine = "";
		Point position = new Point();
		
		for (int y = 0; y < room.getInternalHeight() + 2; y++) {
			position.y = y;
			for (int x = 0; x < room.getInternalWidth() + 2; x++) {
				position.x = x;
				tilesLine = tilesLine + room.tileAt(position).getFileString() + ",";
				itemsLine = itemsLine + room.itemAt(position).getFileString() + ",";
			}
			tilesLines.add(tilesLine.substring(0, tilesLine.length() - 1));
			itemsLines.add(itemsLine.substring(0, itemsLine.length() - 1));
			tilesLine = "";
			itemsLine = "";
		}
		
		Player player = room.getPlayer();
		if (player != null) {
			entitiesLines.add("P:" + player.getPosition().x + "," + player.getPosition().y);
		}
		for (Enemy enemy : room.getEnemyList()) {
			entitiesLines.add("E:" + enemy.getPosition().x + "," + enemy.getPosition().y + "," + enemy.getLevel());
		}
		
		totalLines.addAll(tilesLines);
		totalLines.add("============");
		totalLines.addAll(itemsLines);
		totalLines.add("============");
		totalLines.addAll(entitiesLines);
		
		Utilities.writeFile(fileName, totalLines);
		
	}
	
	public static void saveStoryRoom(Room room, int levelNum, Point roomPoint) {
		
		String dir = WORLD_FOLDER_PATH + "Saves/Level-" + levelNum + "/";
		String fileName = "Room-" + roomPoint.x + "-" + roomPoint.y + ".txt";
		
		saveRoomToTextFile(dir + fileName, room);
		
	}
	
}
