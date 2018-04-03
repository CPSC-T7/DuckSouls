package controllers;

import java.awt.Point;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import entities.Enemy;
import entities.Player;
import items.Item;
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
	
	// The current BattleWorld
	private BattleWorld		battleWorld;
	
	// The title screen
	private TitleScreen		titleScreen;
	
	// Overworld Scene
	private Scene scene;
	
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
		
	}
	
	
	/**
	 * Update the world in which the player fights an enemy.
	 */
	public void updateBattle() {
		
		inBattle = battleWorld.update();
	}
	
	/**
	 * Update the world with the title screen
	 */
	public void updateTitle() {
		
		inTitle = titleScreen.update();
		
	}

	/**
	 * Update the world in which the player moves around
	 * and collects items.
	 */
	public void updateWorld() {
		mainLoop();
	}

	/**
	 * Game's main loop
	 */
	@Override
	public void mainLoop() {

		// Draw the room
		//RoomDrawer.drawRoom(currentLevel.currentRoom, isGUI);
		
		this.drawMap(gc, world.getImageSprites);
		
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
				
				case I:
					System.out.println("Player Inventory:\n");
					player.getInventory().forEach((item, quantity) -> {
						System.out.println(quantity + "x : " + item.getName());
					});

					break;
				
				case E:
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
			
			// Move the player
			if (plyrMoveDirection != null) {
				currentLevel.movePlayer(plyrMoveDirection);
			}
			
			// Handle the events
			handleAllEvents();
						
		
		});
		
		// Move the player
		if (plyrMoveDirection != null) {
			currentLevel.movePlayer(plyrMoveDirection);
		}
		
		// Handle the events
		handleAllEvents();
	}

	/**
	 * Draw the sprite to the screen at a position (x,y)
	 * 
	 * @param gc
	 *            Graphics Context
	 * @param position
	 *            Entity Position
	 * @param image
	 *            the path for the image to be drawn
	 *
	 */
	public void drawSprite(GraphicsContext gc, int[] position, Image image) {
		
		gc.drawImage(image, position[0], position[1]);
		
	}// End of drawSprite
	
	/**
	 * Draw all the sprite in the map to the screen at a position (x,y)
	 * 
	 * @param gc
	 *            Graphics Context
	 * @param Map
	 *            A 3D arraylist containing sprites
	 *
	 * 
	 */
	public void drawMap(GraphicsContext gc, ArrayList<ArrayList<ArrayList<Image>>> Map) {
		
		// For each column...
		for (int y = 0; y < Map.size(); y++) {
			
			// For each row...
			for (int x = 0; x < Map.get(y).size(); x++) {
				
				// Set the current position to that row and column
				int[] position = new int[] { x * 64, y * 64 };
				
				// Draw each sprite in that row and column on top of each other
				for (int i = 0; i < Map.get(y).get(x).size(); i++) {
					this.drawSprite(gc, position, Map.get(y).get(x).get(i));
				}
			}
		}
	}// End of drawMap
	
	
	@Override
	public void handleBattleEvent(Enemy enemyToBattle) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void handleLevelChangeEvent() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void handleAllEvents() {
		// TODO Auto-generated method stub
		
	}
}
