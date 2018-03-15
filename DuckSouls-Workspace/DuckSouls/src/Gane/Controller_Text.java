package Gane;

import java.util.ArrayList;
import java.util.Scanner;
import utils.Utilities;
import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import items.Clothes;
import items.Unarmed;
import levels.Level;
import story_map.Map;

public class Controller_Text {
	
	private static Scanner _scanner = new Scanner(System.in);
	
	private GameWorld world;
	private Event event = new Event(Event_type.NOEVENT);
	DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16);
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
			event.setEvent(world.runTurn(input));
			switch(event.getType()) {
				case BATTLE: 
					Utilities.clearConsole();
					BattleWorldTest.battleLoop(Player, Enemy, event.getItem(), event.getItem2());
					break;
				case GETITEM:
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
