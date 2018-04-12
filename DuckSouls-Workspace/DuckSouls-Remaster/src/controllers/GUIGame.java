package controllers;

import java.awt.Point;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import entities.Enemy;
import entities.Player;
import ui.InventoryDrawer;
import ui.RoomDrawer;

import utils.GameEventQueue;
import utils.Orientation;

import world.Level;
import world.LevelBuilder;

import scenes.TitleScene;
import scenes.BattleScene;
import scenes.PauseScene;

/**
 * This class is the controller for the GUI version of DuckSouls.
 */
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
	
	private static int			levelNum	= 1;
	private static Player		player;
	private static Level		currentLevel;
	
	private static Orientation	plyrMoveDirection;
	// GameWorld
	
	private static boolean		inBattle	= false;
	private static boolean		inTitle		= true;
	private static boolean		inInventory	= false;
	private static boolean		paused		= false;
	
	// The current BattleWorld
	private BattleScene			battleScreen;
	
	// The title screen
	private TitleScene			titleScreen;
	
	// The pause screen
	private PauseScene			pauseScreen;
	
	// Overworld Scene
	private Scene				scene;
	
	// Game window
	private Stage				window;
	
	private GraphicsContext		gc;
	
	@Override
	public void start(Stage window) throws Exception {
		
		// Game setup
		this.window = window;
		
		if (GameData.IS_STORY) {
			player = new Player(new Point(1, 1));
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, new Point(0, 0));
		} else {
			player = new Player(new Point(mapsize / 2 + 1, mapsize / 2 + 1));
			currentLevel = LevelBuilder.buildArcadeLevel(levelNum, player, new Point(0, 0));
		}
		
		// JavaFx setup
		final int windowSize = 64 * 9;
		Group root = new Group();
		scene = new Scene(root);
		Canvas canvas = new Canvas(windowSize, windowSize);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.gc = gc;
		
		titleScreen = new TitleScene(this.window);
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
					
				} else if (paused){
					updatePause();
					
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
		
		inBattle = battleScreen.update();
		
	}// End of updateBattle
	
	/**
	 * Update the pause menu.
	 */
	public void updatePause() {
		
		paused = pauseScreen.update();
		
	}// End of updateBattle
	
	/**
	 * Update the world with the title screen
	 */
	public void updateTitle() {
		
		inTitle = titleScreen.update();
		
	}// End of updateTitle
	
	/**
	 * Update the world in which the player moves around and collects items.
	 */
	public void updateWorld() {
		
		mainLoop();
		
	}// End of updateWorld
	
	/**
	 * Game's main loop
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void mainLoop() {
		
		if (inInventory) {
			// Draw the inventory
			RoomDrawer.drawGUIRoom(currentLevel.currentRoom, gc);
			InventoryDrawer.drawInventory(gc, player);
		} else {
			// Draw the room
			RoomDrawer.drawGUIRoom(currentLevel.currentRoom, gc);
		}
		
		// Check for key input
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
				
				// Move the player in a direction
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
				
				case E: // Display or hide the player inventory
					
					if (inInventory) {
						inInventory = false;
					} else {
						inInventory = true;
					}
					
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
					
				// Enter the pause menu
				case ESCAPE:
					if (!paused) {
						paused = true;
						pauseScreen = new PauseScene(window);
					}	
					
			}// End of switch
			
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
	public void handleBattleEvent(Enemy enemyToBattle){
		
		inBattle = true;
		battleScreen = new BattleScene(window, player, enemyToBattle);
		currentLevel.currentRoom.removeEnemy(player.getPosition());

		
	}// End of handleBattleEvent
	
	@Override
	public void handleLevelChangeEvent() {
		
		levelNum++;
		
		if (GameData.IS_STORY) {
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, currentLevel.getCurrentRoomPoint());
		} else {
			currentLevel = LevelBuilder.buildArcadeLevel(levelNum, player, currentLevel.getCurrentRoomPoint());
		}
		
	}// End of handleLevelChangeEvent
	
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
		
	}// End of handleAllEvents
	
}
