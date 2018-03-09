import java.awt.Point;
import java.util.Scanner;

import arcade_entities.Player;
import arcade_map.TextLevel;
import arcade_map.TextRoom;
import arcade_tiles.Stairs;
import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import items.Item;
import utils.Orientation;
import utils.Utilities;

/**
 * This class runs the move loop for DuckSouls. It creates a text level, allows
 * the player to move around the level picking up items and battling enemies,
 * the transfers the player to another level once the reach the stairs. This
 * repeats forever until the game is closed, or the player dies.
 * 
 * @author Matthew Allwright
 * @version 1.3
 */
public class MoveLoop_Text_Arcade {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Scanner _scanner = new Scanner(System.in);
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Runs the move loop.
	 */
	public static void play() {
		
		// Container variables
		
		String input;
		TextRoom currentRoom;
		TextLevel currentLevel;
		Point playerPoint = new Point(3, 3);
		Point roomPoint = new Point(0, 0);
		Player player = new Player();
		int levelNum = 1;
		int difficultyPerLevel = 2;
		DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16);
		EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16);
		
		/*
		 * Loop:
		 * 
		 * Makes a level, runs the level until the user exits, then moves the player to
		 * another level and runs that level.
		 */
		while (true) {
			
			// Set the level difficulty
			int enemySpawnChance = levelNum * difficultyPerLevel - difficultyPerLevel; // Starts at 0
			
			// Create the level
			currentLevel = new TextLevel(player, playerPoint, roomPoint, enemySpawnChance);
			
			/*
			 * Loop:
			 * 
			 * Draws the room and lets the user move.
			 * 
			 * Exits once the player steps on stairs.
			 */
			while (true) {
				
				// Update some variables
				currentRoom = currentLevel.roomAt(currentLevel.getCurrentRoomPoint());
				playerPoint = currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).playerPoint;
				
				// Exit the level if standing on stairs
				if (currentRoom.tileAt(playerPoint) instanceof Stairs) {
					roomPoint = currentLevel.getCurrentRoomPoint();
					break;
				}
				
				// Draw the room
				Utilities.clearConsole();
				System.out.println("Level: " + levelNum + "\n");
				currentLevel.drawMinimap();
				System.out.println('\n');
				currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).draw_Text();
				
				// Ask the user for input
				System.out.print("\nAction \t: ");
				input = _scanner.nextLine().toUpperCase();
				
				// Do the action inputed by the user
				switch (input) {
					
					// Moving...
					
					case "W":
					case "NORTH":
						if (playerPoint.y == 0) {
							currentLevel.moveRoom_Direction(Orientation.NORTH);
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y - 1));
						}
						break;
					
					case "S":
					case "SOUTH":
						if (playerPoint.y == currentLevel.getRoomSize() + 1) {
							currentLevel.moveRoom_Direction(Orientation.SOUTH);
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y + 1));
						}
						break;
					
					case "D":
					case "EAST":
						if (playerPoint.x == currentLevel.getRoomSize() + 1) {
							currentLevel.moveRoom_Direction(Orientation.EAST);
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x + 1, playerPoint.y));
						}
						break;
					
					case "A":
					case "WEST":
						if (playerPoint.x == 0) {
							currentLevel.moveRoom_Direction(Orientation.WEST);
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x - 1, playerPoint.y));
						}
						break;
					
					// Player commands...
					
					case "I":
						System.out.println("Player Inventory:\n");
						for (Item item : currentRoom.entityAt(playerPoint).getInventory()) {
							System.out.println(item.getName());
						}
						System.out.println("\nPress Enter To Exit.");
						_scanner.nextLine();
						break;
					
					case "E":
						System.out.println("Player Equipment:\n");
						System.out.println(currentRoom.entityAt(playerPoint).getWeapon().getName());
						System.out.println(currentRoom.entityAt(playerPoint).getArmour().getName());
						System.out.println("\nPress Enter To Exit.");
						_scanner.nextLine();
						break;
					
					// Room commands...
					
					case "SAVE":
						currentRoom.saveToTextFile();
						break;
					
					// Default
					
					default:
						System.out.println("...What?");
						Utilities.waitMilliseconds(300);
						break;
					
				}
				
				// Check for enemies
				Point battlePoint = currentRoom.checkForBattlePoint();
				if (battlePoint != null) {
					
					Utilities.clearConsole();
					BattleWorldTest.battleLoop(Player, Enemy, player.getWeapon(), player.getArmour());
					
					currentRoom.removeEntity(battlePoint);
					
				}
				
			} // End of inner while loop
			
			// Switch levels...
			Utilities.clearConsole();
			System.out.println("Loading new level...");
			levelNum++;
			Utilities.waitMilliseconds(1000);
			
		} // End of outer while loop
		
	}
	
}
