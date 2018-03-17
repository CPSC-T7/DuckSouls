package game;

import java.awt.Point;
import java.util.ArrayList;

import arcade_world.Level;
import battle.BattleGuiTest;
import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import entities.Player;
import items.Item;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import story_world.Map;
import tiles.Stairs;
import ui.TitleScreen;
import utils.Orientation;
import utils.Utilities;

public class Controller_GUI_Arcade extends Application {
	
	private int				mapsize			= 7;
	private int				spritesize		= 64;
	private GameWorld		world			= new Level();
	private Event			event			= new Event(Event_type.NOEVENT);
	DuckObject				battlePlayer	= new DuckObject(20, 15, 5, 5, 5, 78, 16, 'G');
	EnemyObject				battleEnemy		= new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16, 'G');
	private static boolean	inBattle		= false;
	private static boolean	inTitle			= true;
	// The current BattleWorld
	private BattleGuiTest	battleWorld;
	
	// The title screen
	private TitleScreen		titleScreen;
	
	@Override
	public void start(Stage window) throws Exception {
		final int windowSize = 64 * 9;
		Group root = new Group();
		Scene scene = new Scene(root);
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
				if (inTitle == true) {
					updateTitle();
					
				} else if (inBattle == false) {
					mainloop(window, gc, scene);
					window.setScene(scene);
					
					// If in battle: update battleWorld
				} else {
					updateBattle(battlePlayer, battleEnemy);
				}
			}
		};
		timer.start();
		
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void mainloop(Stage window, GraphicsContext gc, Scene scene) {
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		
		// Draws the first iteration of the room
		this.drawMap(gc, world.getImageSprites(mapsize));
		
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
				
				case W:
				case A:
				case S:
				case D:
					// Runs the turn with this input
					event.setEvent(world.runTurn(key.getCode().toString()));
					// Redraws the map
					this.drawMap(gc, world.getImageSprites(mapsize));
					break;
				
				case I:
					Utilities.clearConsole();
					System.out.println("Player Inventory:\n");
					for (Item item : world.getInventory()) {
						System.out.println(item.getName());
					}
					break;
				
				case E:
					Utilities.clearConsole();
					System.out.println("Player Equipment:\n");
					System.out.println(this.world.getPlayerWeapon().getName());
					System.out.println(this.world.getPlayerArmour().getName());
					break;
				
				case C:
					Utilities.clearConsole();
					System.out.println("Player Status:\n");
					System.out.println(("Moneys: " + battlePlayer.getMoney()));
					System.out.println(("Experience: " + battlePlayer.getXP()));
					System.out.println("\nPlayer Stats:\n");
					System.out.println(("Health Points: " + battlePlayer.getStats("healthPoints")));
					System.out.println(("Mana Points: " + battlePlayer.getStats("manaPoints")));
					System.out.println(("Attack Points: " + battlePlayer.getStats("attackPoints")));
					System.out.println(("Defence Points: " + battlePlayer.getStats("defencePoints")));
					System.out.println(("Speed Points: " + battlePlayer.getStats("speedPoints")));
					System.out.println(("Accuracy Points: " + battlePlayer.getStats("accuracyPoints")));
					System.out.println(("Critical Hit Points: " + battlePlayer.getStats("criticalHitPoints")));
					break;
			}
			
			switch (event.getType()) {
				case BATTLE:
					inBattle = true;
					this.battleWorld = new BattleGuiTest(window);
					this.battleWorld.setScene();
					event = new Event(Event_type.NOEVENT);
					break;
				case NEXTWORLD:
					world.nextWorld(event.getNextworld());
					this.drawMap(gc, world.getImageSprites(mapsize));
					event = new Event(Event_type.NOEVENT);
			}
			
		});
		
	} // End of mainloop
	
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
	
	/**
	 * Update the world in which the player fights an enemy.
	 */
	public void updateBattle(DuckObject battlePlayer, EnemyObject battleEnemy) {
		this.inBattle = this.battleWorld.update(battlePlayer, battleEnemy, this.world.getPlayerWeapon(),
				this.world.getPlayerArmour());
	}
	
	public void updateTitle() {
		this.inTitle = this.titleScreen.update();
	}
}
