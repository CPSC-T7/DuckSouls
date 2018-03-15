package game;

import java.util.ArrayList;
import java.util.Scanner;

import arcade_world.Level;
import utils.Utilities;
import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import items.Clothes;
import items.Item;
import items.Unarmed;
import story_world.Map;

public class Controller_Text {
	
	private static Scanner _scanner = new Scanner(System.in);
	
	private GameWorld world;
	private Event event = new Event(Event_type.NOEVENT);
	DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16, 'T');
	EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16);
	
	public Controller_Text(Boolean type) {
		if(type) {
			this.world = new Map();
		}
		else {
			this.world = new Level();
		}
	}
	
	public void mainloop() {
		while(true) {
			this.printMap();
			System.out.print("\nAction \t: ");
			String input = _scanner.nextLine().toUpperCase();
			if(input.equals("I")) {
				System.out.println("Player Inventory:\n");
				for (Item item : world.getInventory()) {
					System.out.println(item.getName());
				}
				System.out.println("\nPress Enter To Exit.");
				_scanner.nextLine();
			}
			else {
				event.setEvent(world.runTurn(input));
			}
			switch(event.getType()) {
				case BATTLE: 
					Utilities.clearConsole();
					BattleWorldTest.battleLoop(Player, Enemy, event.getWeapon(), event.getArmour());
					break;
				case NEXTWORLD:
					world.nextWorld(event.getNextworld());
			}
			Utilities.clearConsole();
		}
	}
	
	public void printMap() {
		ArrayList<ArrayList<String>> map = world.getStrings();
		for(ArrayList<String> row: map) {
			for(String column: row) {
				System.out.print(column);
			}
			System.out.println();
		}
	}
}
