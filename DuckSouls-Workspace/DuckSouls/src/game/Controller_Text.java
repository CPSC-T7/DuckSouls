package game;

import java.util.ArrayList;
import java.util.Scanner;

import arcade_world.Level;
import utils.Utilities;
import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import items.Item;
import story_world.Map;

public class Controller_Text {
	
	private static Scanner	_scanner	= new Scanner(System.in);
	
	private GameWorld		world;
	private Event			event		= new Event(Event_type.NOEVENT);
	DuckObject				player		= new DuckObject(20, 15, 5, 5, 5, 78, 16, 'T');
	EnemyObject				enemy		= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16, 'T');
	
	public Controller_Text(Boolean type) {
		if (type) {
			this.world = new Map();
		} else {
			this.world = new Level();
		}
	}
	
	public void mainloop() {
		while (true) {
			
			this.printMap();
			System.out.print("\nAction \t: ");
			String input = _scanner.nextLine().toUpperCase();
			
			switch (input) {
				
				case "I":
					System.out.println("Player Inventory:\n");
					for (Item item : world.getInventory()) {
						System.out.println(item.getName());
					}
					
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
				case "E":
					System.out.println("Player Equipment:\n");
					System.out.println(this.world.getPlayerWeapon().getName());
					System.out.println(this.world.getPlayerArmour().getName());
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
					
				//Displays the player's status and stats while on the map
				case "ST":
					System.out.println("Player Status:\n");
					System.out.println(("Moneys: " + player.getMoney()));
					System.out.println(("Experience: " + player.getXP()));
					System.out.println("\nPlayer Stats:\n");
					System.out.println(("Health Points: " + player.getStats("healthPoints")));
					System.out.println(("Mana Points: " + player.getStats("manaPoints")));
					System.out.println(("Attack Points: " + player.getStats("attackPoints")));
					System.out.println(("Defence Points: " + player.getStats("defencePoints")));
					System.out.println(("Speed Points: " + player.getStats("speedPoints")));
					System.out.println(("Accuracy Points: " + player.getStats("accuracyPoints")));
					System.out.println(("Critical Hit Points: " + player.getStats("criticalHitPoints")));
					
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
				default:
					event.setEvent(world.runTurn(input));
					break;
				
			}
			
			switch (event.getType()) {
				case BATTLE:
					Utilities.clearConsole();
					BattleWorldTest.battleLoop(player, enemy, event.getWeapon(), event.getArmour());
					break;
				case NEXTWORLD:
					world.nextWorld(event.getNextworld());
			}
			Utilities.clearConsole();
		}
	}
	
	public void printMap() {
		ArrayList<ArrayList<String>> map = world.getTextSprites();
		for (ArrayList<String> row : map) {
			for (String column : row) {
				System.out.print(column);
			}
			System.out.println();
		}
	}
}
