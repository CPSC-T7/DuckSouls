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

import ui.RoomDrawer;

import utils.GameEventQue;
import utils.Orientation;
import utils.Utilities;

import world.Level;
import world.LevelBuilder;

import scenes.TitleScreen;
import scenes.OverWorld;
import scenes.BattleWorld;

import battle.Loop;


public class GUIGame extends Application implements Controller {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final boolean	isGUI		= true;
	public static final int		tileSize	= 64;
	public static final int		windowSize 	= tileSize * 9;
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	private static boolean		isStory;
	
	private static int			levelNum	= 1;
	private static Player		player;
	private static Level		currentLevel;
	
	private static String		usrInput;
	private static Orientation	plyrMoveDirection;

	private static boolean	inBattle		= false;
	private static boolean	inTitle			= true;
	private boolean 		inInventory 	= false;
	
	
	// The title screen
	private TitleScreen		titleScreen;
	
	// The current BattleWorld
	private BattleWorld		battleWorldScreen;
	
	// The current OverWorld
	private OverWorld 		overWorldScreen;
	
	/**
	 * The GUIGame constructor.
	 * Sets the game mode, creates the player, and
	 * builds the starting level.
	 * 
	 * @param isStory
	 */
	public GUIGame(boolean isStory) {
		
		GUIGame.isStory = isStory;
		player = new Player(new Point(1, 1));
		
		if (GUIGame.isStory) {
			currentLevel = LevelBuilder.buildStoryLevel(levelNum, player, new Point(0, 0));
		} else {
			currentLevel = new Level(levelNum, player, new Point(0, 0));
		}
		
	}// End of class constructor

	@Override
	public void start(Stage window) throws Exception {
		
		//Setup JavaFX
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(windowSize, windowSize);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//Create and show the title scene
		titleScreen = new TitleScreen(window);
		window.setTitle("DuckSouls");
		window.setScene(scene);
		window.show();
		
		// Update on a frame timer
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				// Update every frame
				mainLoop();
			}
		};
		timer.start();
		
	}// End of start

	@Override
	public void mainLoop() {

		if (inTitle) {
			updateTitle();
		}else if (inBattle) {
			this.inBattle = battleWorldScreen.update();
		}else {
			
		}
		
	}// End of mainLoop
	
	/**
	 * 
	 */
	public void updateTitle() {
		
		this.inTitle = this.titleScreen.update();
	}

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
