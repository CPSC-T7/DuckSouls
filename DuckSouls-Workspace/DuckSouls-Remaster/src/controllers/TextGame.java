package controllers;

import java.awt.Point;
import java.util.Scanner;

import entities.Enemy;
import entities.Player;
import events.GameEventQueue;
import scenes.BattleScene;
import ui.RoomDrawer;
import utils.Orientation;
import utils.Utilities;
import world.Level;
import world.LevelBuilder;
import world.LevelIO;
import battle.BattleLoop;

/**
 * This class is the controller for the text version of DuckSouls.
 */
public class TextGame implements Controller {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	private static Scanner		consoleIn	= new Scanner(System.in);
	
	private static int			levelNum	= 1;
	private static Player		player;
	private static Level		currentLevel;
	
	private static String		usrInput;
	private static Orientation	plyrMoveDirection;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new text game.
	 */
	public TextGame() {
		
		/*
		 * Build the first level.
		 */
		
		if (GameData.IS_STORY) {
			
			// Story mode
			if (GameData.LOAD_GAME) {
				// Load the saved game
				currentLevel = LevelBuilder.buildSavedStoryLevel();
			} else {
				// Load the first story level
				currentLevel = LevelBuilder.buildStoryLevel(levelNum, new Player(new Point(1, 1)), new Point(0, 0));
			}
			
		} else {
			
			// Arcade mode
			currentLevel = LevelBuilder.buildArcadeLevel(levelNum, new Player(new Point(4, 4)), new Point(0, 0));
			
		}
		
		// Grab the player
		player = currentLevel.currentRoom.getPlayer();
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public void mainLoop() {
		
		// Loop
		while (true) {
			
			Utilities.clearConsole();
			
			// Print out the level number
			System.out.println("Level : " + levelNum + "\n");
			
			// Draw the room
			RoomDrawer.drawTextRoom(currentLevel.currentRoom);
			
			// Prompt the user for input and clear the old value
			usrInput = consoleIn.nextLine();
			plyrMoveDirection = null;
			
			// Handle the input
			switch (usrInput.toUpperCase()) {
				
				/*
				 * MOVEMENT
				 */
				
				case "W":
				case "NORTH":
					plyrMoveDirection = Orientation.NORTH;
					break;
				
				case "S":
				case "SOUTH":
					plyrMoveDirection = Orientation.SOUTH;
					break;
				
				case "D":
				case "EAST":
					plyrMoveDirection = Orientation.EAST;
					break;
				
				case "A":
				case "WEST":
					plyrMoveDirection = Orientation.WEST;
					break;
				
				/*
				 * STATS
				 */
				
				case "I":
					System.out.println("Player Inventory:\n");
					player.getInventory().forEach((item, quantity) -> {
						System.out.println(quantity + "x : " + item.getName());
					});
					
					System.out.println("\nPress Enter To Exit.");
					consoleIn.nextLine();
					break;
				
				case "E":
					System.out.println("Player Equipment:\n");
					System.out.println("Weapon : " + player.getWeapon().getName());
					System.out.println("Armour : " + player.getArmour().getName());
					
					System.out.println("\nPress Enter To Exit.");
					consoleIn.nextLine();
					break;
				
				case "C":
					System.out.println("Player Status:\n");
					System.out.println(("Health Points : " + player.getHealth()));
					System.out.println(("Points : " + player.getScore()));
					System.out.println(("Experience : " + player.getExperience()));
					System.out.println("\nPlayer Stats:\n");
					System.out.println(("Attack : " + player.getAttack()));
					System.out.println(("Defence : " + player.getDefence()));
					System.out.println(("Speed : " + player.getSpeed()));
					System.out.println(("Accuracy : " + player.getAccuracy()));
					System.out.println(("Crit Chance : " + player.getCrit()));
					
					System.out.println("\nPress Enter To Exit.");
					consoleIn.nextLine();
					break;
				
				/*
				 * DATA
				 */
				
				case ";": // Save the game
					if (GameData.IS_STORY) {
						System.out.print("THIS WILL DELETE THE PREVIOUS SAVE. CONTINUE? (Y/N) : ");
						
						String input = consoleIn.nextLine();
						if (input.length() > 0 && input.toUpperCase().charAt(0) == 'Y') {
							LevelIO.saveStoryLevel(currentLevel);
							System.out.println("Level Saved!");
						} else {
							System.out.println("Aborting...");
						}
						Utilities.waitMilliseconds(1000);
					}
					
			}
			
			// Move the player
			if (plyrMoveDirection != null) {
				currentLevel.movePlayer(plyrMoveDirection);
				plyrMoveDirection = null;
			}
			
			// Handle the events
			handleAllEvents();
			
		}
	}
	
	@Override
	public void handleBattleEvent(Enemy enemyToBattle) {

		BattleLoop.battleLoop(player, enemyToBattle);
		currentLevel.currentRoom.removeEnemy(player.getPosition());
		
	}
	
	@Override
	public void handleLevelChangeEvent() {
		
		levelNum++;
		
		if (GameData.IS_STORY) {
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, currentLevel.getCurrentRoomPoint());
		} else {
			currentLevel = LevelBuilder.buildArcadeLevel(levelNum, player, currentLevel.getCurrentRoomPoint());
		}
		
	}
	
	@Override
	public void handleAllEvents() {
		
		while (GameEventQueue.hasEvent()) {
			switch (GameEventQueue.handleNextEvent()) {
				case BATTLE:
					handleBattleEvent(currentLevel.currentRoom.enemyAt(player.getPosition()));
					break;
				case LEVEL_CHANGE:
					handleLevelChangeEvent();
					break;
			}
		}
	}
}
