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

/**
 * Controls the input and output of the game as while as the interaction 
 * between the battle engine and the overworld engines in text form
 * 
 * @author Colin Au Yeung
 *
 */
public class Controller_Text {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	private static Scanner	_scanner	= new Scanner(System.in);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	private GameWorld		world;
	private Event			event		= new Event(Event_type.NOEVENT);
	DuckObject				player		= new DuckObject(20, 15, 5, 5, 5, 78, 16, 'T');
	EnemyObject				enemy		= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16, 'T');
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Generic constructor. If passed true starts the game with story mode, else starts the game
	 * with arcade mode
	 * 
	 * @param type
	 * 		Boolean, true for story mode, false for arcade mode
	 */
	public Controller_Text(Boolean type) {
		if (type) {
			this.world = new Map();
		} else {
			this.world = new Level();
		}
	}//End of Constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/*
	 * Main game functions which asks player for input and shows the game state
	 */
	public void mainloop() {
		
		while (true) {
			
			//print the map and asks for action
			this.printMap();
			System.out.print("\nAction \t: ");
			String input = _scanner.nextLine().toUpperCase();
			
			//Based on the input...
			switch (input) {
				
				//If I, show the player the inventory
				case "I":
					System.out.println("Player Inventory:\n");
					for (Item item : world.getInventory()) {
						System.out.println(item.getName());
					}
					
					//Wait for the player to press enter
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
				//If E, show the player their equipment
				case "E":
					System.out.println("Player Equipment:\n");
					System.out.println(this.world.getPlayerWeapon().getName());
					System.out.println(this.world.getPlayerArmour().getName());
					
					//Wait for player to hit enter
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
					
					//Wait for player to hit enter
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
				default:
					
					//Moves the player based on the wasd
					event.setEvent(world.runTurn(input));
					break;
				
			}
			
			//Based on the event that the turn generates
			switch (event.getType()) {
			
				//If battle, run the battle loop
				case BATTLE:
					Utilities.clearConsole();
					BattleWorldTest.battleLoop(player, enemy, event.getWeapon(), event.getArmour());
					break;
				
				//If nextworld, generate a new level
				case NEXTWORLD:
					world.nextWorld(event.getNextworld());
			}
			Utilities.clearConsole();
		}
	}//End of mainloop
	
	
	/**
	 * Prints the game state to console
	 */
	public void printMap() {
		ArrayList<ArrayList<String>> map = world.getTextSprites();
		for (ArrayList<String> row : map) {
			for (String column : row) {
				System.out.print(column);
			}
			System.out.println();
		}
	}//End of printMap
}
