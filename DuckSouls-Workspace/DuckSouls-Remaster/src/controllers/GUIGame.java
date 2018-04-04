package controllers;

import java.awt.Point;
import java.util.ArrayList;

import battle.Loop;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import entities.Enemy;
import entities.Player;
import items.Item;
import ui.InventoryDrawer;
import ui.RoomDrawer;

import utils.GameEventQue;
import utils.Orientation;
import utils.Utilities;

import world.Level;
import world.LevelBuilder;

import scenes.TitleScreen;
import scenes.BattleWorld;

public class GUIGame extends Application implements Controller {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final boolean	isGUI		= true;
	public static final int		mapsize		= 7;
	public static final int		tileSize	= 64;


	/*
	 * 
	 * VARIABLES
	 * 
	 */

	private static boolean		isStory		= false;	
	
	private static int			levelNum	= 1;
	private static Player		player;
	private static Level		currentLevel;
	
	private static String		usrInput;
	private static Orientation	plyrMoveDirection;
	//GameWorld

	private static boolean	inBattle		= false;
	private static boolean	inTitle			= true;
	private static boolean 	inInventory		= false;
	
	// The current BattleWorld
	private BattleWorld		battleWorld;
	
	// The title screen
	private TitleScreen		titleScreen;
	
	// Overworld Scene
	private Scene scene;
	
	private GraphicsContext gc;
	
	@Override
	public void start(Stage window) throws Exception {
		
		// Game setup
		player = new Player(new Point(1, 1));
		
		if (GUIGame.isStory) {
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, new Point(0, 0));
		} else {
			currentLevel = new Level(levelNum, player, new Point(0, 0));
		}
		
		
		// JavaFx setup
		final int windowSize = 64 * 9;
		Group root = new Group();
		scene = new Scene(root);
		Canvas canvas = new Canvas(windowSize, windowSize);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.gc = gc;
		
		titleScreen = new TitleScreen(window);
		window.setTitle("DuckSouls");
		window.setScene(scene);
		window.show();
		
		// Update based on frame rate
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				// Update depending on the selected screen
				if (inTitle) {
					updateTitle();
					
				} else if (inBattle) {
					updateBattle();
					
				} else {
					updateWorld();
					window.setScene(scene);
				}
			}
		};
		timer.start();
		
	}// End of start
	
	
	/**
	 * Update the world in which the player fights an enemy.
	 */
	public void updateBattle() {
		
		inBattle = battleWorld.update();
		
	}// End of updateBattle
	
	/**
	 * Update the world with the title screen
	 */
	public void updateTitle() {
		
		inTitle = titleScreen.update();
		
	}// End of updateTitle

	/**
	 * Update the world in which the player moves around
	 * and collects items.
	 */
	public void updateWorld() {
		mainLoop();
		
	}// End of updateWorld

	/**
	 * Game's main loop
	 */
	@Override
	public void mainLoop() {
		
		if (inInventory) {
			//Draw the inventory
			RoomDrawer.drawGUIRoom(currentLevel.currentRoom, gc);
			InventoryDrawer.drawInventory(gc);
		}else {
			// Draw the room
			RoomDrawer.drawGUIRoom(currentLevel.currentRoom, gc);
		}
		
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
				
				case W:
					plyrMoveDirection = Orientation.NORTH;
					break;
				case A:
					plyrMoveDirection = Orientation.WEST;
					break;
				case S:
					plyrMoveDirection = Orientation.SOUTH;
					break;
				case D:
					plyrMoveDirection = Orientation.EAST;
					break;
				
				/*
				 * STATS
				 */
				
				case E:
					if(inInventory) {
						inInventory = false;
					}else {
						inInventory = true;
					}
					
					System.out.println("Player Inventory:\n");
					player.getInventory().forEach((item, quantity) -> {
						System.out.println(quantity + "x : " + item.getName());
					});
					System.out.println("Player Equipment:\n");
					System.out.println("Weapon : " + player.getWeapon().getName());
					System.out.println("Armour : " + player.getArmour().getName());
					
					break;
				
				case C:
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
					
					break;
			}			
		
		});
		
		// Move the player
		if (plyrMoveDirection != null) {
			currentLevel.movePlayer(plyrMoveDirection);
			plyrMoveDirection = null;
		}
		
		// Handle the events
		handleAllEvents();
		
	}// End of mainLoop
	
	@Override
	public void handleBattleEvent(Enemy enemyToBattle) {
		Loop.battleLoop(player, enemyToBattle, isGUI); // Still under works
		
	}// End of handleBattleEvent


	@Override
	public void handleLevelChangeEvent() {
		
		levelNum++;
		
		if (isStory) {
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, currentLevel.getCurrentRoomPoint());
		} else {
			currentLevel = new Level(levelNum, player, currentLevel.getCurrentRoomPoint());
		}
		
	}// End of handleLevelChangeEvent


	@Override
	public void handleAllEvents() {

		while (GameEventQue.hasEvent()) {
			switch (GameEventQue.handleNextEvent()) {
				case BATTLE:
					handleBattleEvent(currentLevel.currentRoom.enemyAt(player.getPosition()));
					break;
				case LEVEL_CHANGE:
					handleLevelChangeEvent();
					break;
			}
		}
		
	}// End of handleAllEvents
	
	
}
