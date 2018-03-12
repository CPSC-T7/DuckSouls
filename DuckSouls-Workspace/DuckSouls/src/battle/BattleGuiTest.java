package battle;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import map.GUILevel;
import tiles.Stairs;
import ui.MenuButton;
import utils.Orientation;
import utils.Utilities;

import java.awt.Point;

import animations.*; //sprite animations


/**
 * 
 * @author Wylee McAndrews
 * 
 * Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleGuiTest extends Application {
	
	//Keep track of the currently selected button
	private int menuButtonX = 0;
	private int menuButtonY = 0;
	
	//The size of the screen (squared)
	private final int windowSize = 64 * 9;
	
	/**
	 * Start the JavaFX stage
	 */
	public void start(Stage mainStage) throws Exception {
		
		
		Canvas canvas = new Canvas(windowSize, windowSize);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainStage.setTitle("DuckSouls");
		
		//Background image and viewer
		final Image battleBackgroundImage = new Image("Sprites/Backgrounds/Sewer-Battle.png");
	    final ImageView battleBackgroundImageView = new ImageView(battleBackgroundImage);
		
	    //Player image and viewer
		final Image playerImage = new Image("Sprites/Entities/Duck/Battle/Duck.png");
	    final ImageView playerImageView = new ImageView(playerImage);
	    
	    /*Battle menu buttons*/
	    //Attack button image and viewer
	    final Image attackButtonImage = new Image("Sprites/Menus/Battle/Attack.png");
	    final ImageView attackButtonImageView = new ImageView(attackButtonImage);
	    
	    //Taunt button image and viewer
	    final Image tauntButtonImage = new Image("Sprites/Menus/Battle/Taunt.png");
	    final ImageView tauntButtonImageView = new ImageView(tauntButtonImage);
	    
	    //Quack button image and viewer
	    final Image quackButtonImage = new Image("Sprites/Menus/Battle/Quack.png");
	    final ImageView quackButtonImageView = new ImageView(quackButtonImage);
	    
	    //Item button image and viewer
	    final Image itemButtonImage = new Image("Sprites/Menus/Battle/Item.png");
	    final ImageView itemButtonImageView = new ImageView(itemButtonImage);
	    
	    //Root group will separate all layers
	    final Group root = new Group();
	    
	    //Background, menu, and player layers (drawn in order of appearance)
	    final Pane backgroundLayer = new Pane();
	    final Pane menuLayer = new Pane(attackButtonImageView, tauntButtonImageView, quackButtonImageView, itemButtonImageView);    
	    final Pane playerLayer = new Pane();
	    
	    //Add all sprites to draw in order
	    final Pane groupPane = new Pane(backgroundLayer, menuLayer, playerLayer);
	    
	    //Add all layers to the main group
	    root.getChildren().add(groupPane);
	    
	    //Add nodes of the background and player to their respective layers
	    backgroundLayer.getChildren().add(battleBackgroundImageView);
	    BattlePlayer player = new BattlePlayer(playerImageView);
		playerLayer.getChildren().add(player);	
		
		//2D array of menu buttons [rows][columns]
		MenuButton[][] menuArray = new MenuButton[][] {	{new MenuButton(attackButtonImageView, "Attack", 10, 64*6),
												   		new MenuButton(tauntButtonImageView, "Taunt", 10, 64*6 + 50)},
			
														{new MenuButton(quackButtonImageView, "Quack", 160, 64*6),
												   		new MenuButton(itemButtonImageView, "Item", 160, 64*6 + 50)} };
		
		//Add 2d array of menu buttons to menuLayer
		menuLayer.getChildren().addAll(	menuArray[0][0], menuArray[0][1],
									   	menuArray[1][0], menuArray[1][1]);
		
		//Create the scene from the main group
		Scene scene = new Scene(root, windowSize, windowSize);
		
		
		//Start the player and button animations (background has none)
		player.animation.play();
		menuArray[menuButtonX][menuButtonY].animation.play();
		menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
		
		//Set and show the scene
		mainStage.setScene(scene);
		mainStage.show();
		
		//Update based on frame rate
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				update(scene, player, menuArray);
			}
		};
		timer.start();
		
	}	//End of start()
	
	
	/**
	 * Update the scene based on user input:
	 * 		-Select moves (attack, taunt, etc)
	 * 		-Play Animations
	 * 		
	 * @param scene
	 * 					The current scene.
	 * @param player
	 * 					The player reference (for sprites, animations, stats).
	 * @param menuArray
	 * 					A 2D array of menu buttons used to make moves against an enemy.
	 */
	public void update(Scene scene, BattlePlayer player, MenuButton[][] menuArray) {
		
		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {
			
			if (key.getCode() == KeyCode.W) { // W key
				
				//player.animation.play();
				//player.animation.setOffsetY(0);
				if(menuButtonY == 1) {
					
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(0);
					menuButtonY = 0;
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
					
				}
				
			} else if (key.getCode() == KeyCode.A) { // A key
				
				//player.animation.play();
				//player.animation.setOffsetY(256);
				//player.moveX(-8);
				
				if(menuButtonX == 1) {
					
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(0);
					menuButtonX = 0;
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
					
				}
				
			} else if (key.getCode() == KeyCode.S) { // S key
				
				if(menuButtonY == 0) {
					
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(0);
					menuButtonY = 1;
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
					
				}

				
			} else if (key.getCode() == KeyCode.D) { // D key
				
				//player.animation.play();
				//player.animation.setOffsetY(128);
				//player.moveX(8);
				
				if(menuButtonX == 0) {
					
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(0);
					menuButtonX = 1;
					menuArray[menuButtonX][menuButtonY].animation.play();
					menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
					
				}
				
			} else if (key.getCode() == KeyCode.ENTER) { // ENTER key
				
				String buttonType = menuArray[menuButtonX][menuButtonY].getButtonType();
				
				if (buttonType == "Attack") {
					player.animation.play();
					player.animation.setOffsetY(128);
					
				}else if (buttonType == "Taunt") {
					player.animation.play();
					player.animation.setOffsetY(0);
					
				}else if (buttonType == "Quack") {
					
				}else if (buttonType == "Item") {
					
				}
				
			}
		});
		
	}
    public static void main(String[] args) 
    {
        launch(args);
    }
}
