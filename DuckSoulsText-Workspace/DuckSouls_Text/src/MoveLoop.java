import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

import battle.BattleWorldTest;
import entities.Player;
import items.Item;
import map.TextLevel;
import map.TextRoom;
import tiles.Stairs;
import utils.Utilities;

public class MoveLoop {
	
	private static Scanner _scanner = new Scanner(System.in);
	// private static Random _random = new Random();
	
	public static void main(String[] args) {
		
		String input;
		TextRoom currentRoom;
		TextLevel currentLevel;
		Point playerPoint = new Point(3, 3);
		Point roomPoint = new Point(0, 0);
		Player player = new Player();
		
		/*
		 * Loop:
		 * 
		 * Makes a level, runs the level until the user exits, then moves the player to
		 * another level and runs that level.
		 */
		while (true) {
			
			currentLevel = new TextLevel(player, playerPoint, roomPoint);
			
			/*
			 * Loop:
			 * 
			 * Draws the room and lets the user move.
			 */
			while (true) {
				
				// Update some variables
				currentRoom = currentLevel.roomAt(currentLevel.getCurrentRoomPoint());
				playerPoint = currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).playerPoint;
				
				// Draw the room and ask the user for input
				Utilities.clearConsole();
				currentLevel.drawMinimap();
				System.out.println('\n');
				currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).draw_Text();
				
				// Exit the level if standing on stairs
				if (currentRoom.tileAt(playerPoint) instanceof Stairs) {
					// playerPoint = currentRoom.playerPoint;
					roomPoint = currentLevel.getCurrentRoomPoint();
					break;
				}
				
				System.out.print("\nAction \t: ");
				input = _scanner.nextLine().toUpperCase();
				
				// Do the action inputed by the user
				switch (input) {
					
					// Moving...
					
					case "W":
					case "NORTH":
						if (playerPoint.y == 0) {
							currentLevel.moveRoom_Direction('u');
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y - 1));
						}
						break;
					
					case "S":
					case "SOUTH":
						if (playerPoint.y == currentLevel.getRoomSize() + 1) {
							currentLevel.moveRoom_Direction('d');
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y + 1));
						}
						break;
					
					case "D":
					case "EAST":
						if (playerPoint.x == currentLevel.getRoomSize() + 1) {
							currentLevel.moveRoom_Direction('r');
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x + 1, playerPoint.y));
						}
						break;
					
					case "A":
					case "WEST":
						if (playerPoint.x == 0) {
							currentLevel.moveRoom_Direction('l');
						} else {
							currentRoom.moveEntity(playerPoint, new Point(playerPoint.x - 1, playerPoint.y));
						}
						break;
					
					// Room commands...
					
					case "I":
						System.out.println("Player Inventory:\n");
						for (Item item : currentRoom.entityAt(playerPoint).getInventory()) {
							System.out.println(item.getName());
						}
						System.out.println("\nPress Enter To Exit.");
						_scanner.nextLine();
						break;
					
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
					BattleWorldTest.battleLoop();
					
					currentRoom.removeEntity(battlePoint);
					
				}
				
			} // End of inner while loop
			
			Utilities.clearConsole();
			System.out.println("Loading new level...");
			Utilities.waitMilliseconds(1000);
			
		} // End of outer while loop
		
	}
	
}
