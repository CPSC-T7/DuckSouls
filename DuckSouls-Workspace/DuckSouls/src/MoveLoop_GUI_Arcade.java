import java.awt.Point;

import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import entities.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import map.GUILevel;
import map.GUIRoom;
import tiles.Stairs;
import utils.Orientation;
import utils.Utilities;

/**
 * This class runs the move loop for DuckSouls. It creates a GUI level, allows
 * the player to move around the level picking up items and battling enemies,
 * the transfers the player to another level once the reach the stairs. This
 * repeats forever until the game is closed, or the player dies.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 2.0
 */
public class MoveLoop_GUI_Arcade extends Application {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static GUIRoom	currentRoom;
	private static GUILevel	currentLevel;
	private static Point	playerPoint			= new Point(3, 3);
	private static Point	roomPoint			= new Point(0, 0);
	private static Player	player				= new Player();
	private static int		levelNum			= 1;
	private static int		difficultyPerLevel	= 2;
	private static int		enemySpawnChance;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public void start(Stage mainStage) throws Exception {
		
		// JavaFX
		final int windowSize = 64 * 7;
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(windowSize, windowSize);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainStage.setTitle("DuckSouls");
		mainStage.setScene(scene);
		mainStage.show();
		
		DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 78, 16);
		EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16);
		
		// Set the level difficulty
		enemySpawnChance = levelNum * difficultyPerLevel - difficultyPerLevel; // Starts at 0
		
		// Create the level
		currentLevel = new GUILevel(player, playerPoint, roomPoint, enemySpawnChance);
		
		// Update some variables
		currentRoom = currentLevel.roomAt(currentLevel.getCurrentRoomPoint());
		playerPoint = currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).playerPoint;
		
		currentRoom.draw_GUI(gc);
		
		/*
		 * Loop:
		 * 
		 * Makes a level, runs the level until the user exits, then moves the player to
		 * another level and runs that level.
		 */
		
		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {
			
			// Update some variables
			currentRoom = currentLevel.roomAt(currentLevel.getCurrentRoomPoint());
			playerPoint = currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).playerPoint;
			
			// Exit the level if standing on stairs
			if (currentRoom.tileAt(playerPoint) instanceof Stairs) {
				
				roomPoint = currentLevel.getCurrentRoomPoint();
				
				// Switch levels...
				
				// Set the level difficulty
				enemySpawnChance = levelNum * difficultyPerLevel - difficultyPerLevel; // Starts at 0
				
				// Create the level
				currentLevel = new GUILevel(player, playerPoint, roomPoint, enemySpawnChance);
				
				Utilities.clearConsole();
				System.out.println("Loading new level...");
				levelNum++;
				Utilities.waitMilliseconds(1000);
				
			}
			
			// Update some variables
			currentRoom = currentLevel.roomAt(currentLevel.getCurrentRoomPoint());
			playerPoint = currentLevel.roomAt(currentLevel.getCurrentRoomPoint()).playerPoint;
			
			// Draw the room
			Utilities.clearConsole();
			System.out.println("Level: " + levelNum + "\n");
			currentRoom.draw_GUI(gc);
			
			if (key.getCode() == KeyCode.W) { // NORTH
				
				if (currentRoom.playerPoint.y == 0) {
					currentLevel.moveRoom_Direction(Orientation.NORTH, gc);
				} else {
					
					// Point to move the player to (Up)
					Point newPlayerPoint = new Point(currentRoom.playerPoint.x, currentRoom.playerPoint.y - 1);
					
					// Run the method to move the player
					currentRoom.moveEntity(currentRoom.playerPoint, newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = currentRoom.playerPoint;
					currentRoom.entityAt(newPlayerPoint).setOrientation(Orientation.NORTH);
					currentRoom.entityAt(newPlayerPoint).updateImage();
					
					// Re-draw the room
					currentRoom.draw_GUI(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.A) { // WEST
				
				if (currentRoom.playerPoint.x == 0) {
					currentLevel.moveRoom_Direction(Orientation.WEST, gc);
				} else {
					
					// Point to move the player to (Left)
					Point newPlayerPoint = new Point(currentRoom.playerPoint.x - 1, currentRoom.playerPoint.y);
					
					// Run the method to move the player
					currentRoom.moveEntity(currentRoom.playerPoint, newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = currentRoom.playerPoint;
					currentRoom.entityAt(newPlayerPoint).setOrientation(Orientation.WEST);
					currentRoom.entityAt(newPlayerPoint).updateImage();
					
					// Re-draw the room
					currentRoom.draw_GUI(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.S) { // SOUTH
				
				if (currentRoom.playerPoint.y == currentLevel.roomSize + 1) {
					currentLevel.moveRoom_Direction(Orientation.SOUTH, gc);
				} else {
					
					// Point to move the player to (Down)
					Point newPlayerPoint = new Point(currentRoom.playerPoint.x, currentRoom.playerPoint.y + 1);
					
					// Run the method to move the player
					currentRoom.moveEntity(currentRoom.playerPoint, newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = currentRoom.playerPoint;
					currentRoom.entityAt(newPlayerPoint).setOrientation(Orientation.SOUTH);
					currentRoom.entityAt(newPlayerPoint).updateImage();
					
					// Re-draw the room
					currentRoom.draw_GUI(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.D) { // EAST
				
				if (currentRoom.playerPoint.x == currentLevel.roomSize + 1) {
					currentLevel.moveRoom_Direction(Orientation.EAST, gc);
				} else {
					
					// Point to move the player to (Right)
					Point newPlayerPoint = new Point(currentRoom.playerPoint.x + 1, currentRoom.playerPoint.y);
					
					// Run the method to move the player
					currentRoom.moveEntity(currentRoom.playerPoint, newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = currentRoom.playerPoint;
					currentRoom.entityAt(newPlayerPoint).setOrientation(Orientation.EAST);
					currentRoom.entityAt(newPlayerPoint).updateImage();
					
					// Re-draw the room
					currentRoom.draw_GUI(gc);
					
				}
				
			}
			
			// Check for enemies
			Point battlePoint = currentRoom.checkForBattlePoint();
			if (battlePoint != null) {
				
				System.out.println("Entering battle...");
				
				Utilities.clearConsole();
				BattleWorldTest.battleLoop(Player, Enemy, player.getWeapon(), player.getArmour());
				
				currentRoom.removeEntity(battlePoint);
				currentRoom.draw_GUI(gc);
				
			}
			
		});
		
	}
	
}
