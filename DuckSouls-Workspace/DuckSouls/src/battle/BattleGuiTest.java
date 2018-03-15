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
import javafx.util.Duration;
import tiles.Stairs;
import ui.MenuButton;
import utils.Orientation;
import utils.Utilities;

import java.awt.Point;

import animations.*; //sprite animations
import items.Item;


/**
 * 
 * @author Wylee McAndrews
 * 
 * Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleGuiTest{
	
	//Keep track of the currently selected button
	private int menuButtonX = 0;
	private int menuButtonY = 0;
	private String menuButtonType;
	
	//The distance that the player runs to hit the enemy, and vice versa
	private int runDistance = 64*2;
	//The step size per frame
	private int stepSize = 4;
	//The current step of the player (range of 0-64*6)
	private int currentStep = 0;
	
	//Whether or not an animation is playing
	private boolean inAnimation = false;
	
	//If the battle has ended
	private boolean inBattle = true;
	
	//The size of the screen (squared)
	private int windowSize = 64 * 9;
	
	//Background image and viewer
	private Image battleBackgroundImage = new Image("Sprites/Backgrounds/Sewer-Battle.png");
	private ImageView battleBackgroundImageView = new ImageView(battleBackgroundImage);
	
    //Player image and viewer
	private Image playerImage = new Image("Sprites/Entities/Duck/Battle/Duck.png");
	private ImageView playerImageView = new ImageView(playerImage);
	
	//Player class
	private BattlePlayer playerAnimation = new BattlePlayer(playerImageView);
    
    /*Battle menu buttons*/
    //Attack button image and viewer
	private Image attackButtonImage = new Image("Sprites/Menus/Battle/Attack.png");
	private ImageView attackButtonImageView = new ImageView(attackButtonImage);
    
    //Taunt button image and viewer
	private Image tauntButtonImage = new Image("Sprites/Menus/Battle/Taunt.png");
	private ImageView tauntButtonImageView = new ImageView(tauntButtonImage);
    
    //Quack button image and viewer
	private Image quackButtonImage = new Image("Sprites/Menus/Battle/Fly.png");
	private ImageView quackButtonImageView = new ImageView(quackButtonImage);
    
    //Item button image and viewer
	private Image itemButtonImage = new Image("Sprites/Menus/Battle/Item.png");
	private ImageView itemButtonImageView = new ImageView(itemButtonImage);
    
    //Root group will separate all layers
	private Group root = new Group();
    
    //Background, menu, and player layers (drawn in order of appearance)
	private Pane backgroundLayer = new Pane();
	private Pane menuLayer = new Pane(attackButtonImageView, tauntButtonImageView, quackButtonImageView, itemButtonImageView);    
	private Pane playerLayer = new Pane();
    
    //Add all sprites to draw in order
	private Pane groupPane = new Pane(backgroundLayer, menuLayer, playerLayer);
	
	//2D array of menu buttons [rows][columns]
	MenuButton[][] menuArray = new MenuButton[][] {	{new MenuButton(attackButtonImageView, "Attack", 10, 64*6),
											   		new MenuButton(tauntButtonImageView, "Taunt", 10, 64*6 + 50)},
		
													{new MenuButton(quackButtonImageView, "Fly", 160, 64*6),
											   		new MenuButton(itemButtonImageView, "Item", 160, 64*6 + 50)} };
	
	//The BattleGUI Stage and Scene
	private Stage window;
	private Scene scene = new Scene(root);
    
	/**
	 * Start the JavaFX stage
	 */
	public BattleGuiTest(Stage window){
		
		this.window = window;
	    
	    //Add all layers to the main group
	    root.getChildren().add(groupPane);
	    
	    //Add nodes of the background and player to their respective layers
	    backgroundLayer.getChildren().add(battleBackgroundImageView);
		playerLayer.getChildren().add(this.playerAnimation);	
		

		
		//Add 2d array of menu buttons to menuLayer
		menuLayer.getChildren().addAll(	menuArray[0][0], menuArray[0][1],
									   	menuArray[1][0], menuArray[1][1]);
		
		//Start the player and button animations (background has none)
		playerAnimation.animation.play();
		menuArray[menuButtonX][menuButtonY].animation.play();
		menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
		
	}	//End of start()
	
	/**
	 * Set and show the scene on the window.
	 */
	public void setScene() {
		window.setScene(scene);
		window.show();
	}
	
	
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
	public boolean update(DuckObject Player, EnemyObject Enemy, Item wep, Item arm) {
		
		// System.out.print(wep);
		// System.out.print(arm);
		// Utilities.waitMilliseconds(5000);
		
		// Add stats from weapon and armour before the battle, it gets reset at the end
		Player.setStats("attackPoints", (Player.getStats("attackPoints") + wep.getExtraStats("attack")));
		Player.setStats("accuracyPoints", (Player.getStats("accuracyPoints") + wep.getExtraStats("accuracy")));
		Player.setStats("speedPoints", (Player.getStats("speedPoints") + wep.getExtraStats("speed")));
		Player.setStats("criticalHitPoints", (Player.getStats("criticalHitPoints") + wep.getExtraStats("critChance")));
		
		Player.setStats("defencePoints", (Player.getStats("defencePoints") + arm.getExtraStats("defense")));
		
		//If the battle has ended or not
		boolean inBattle = true;
		
		int startingPerson = 1;
		
		if (Player.getStats("speedPointsStatic") >= Enemy.getStats("speedPointsStatic")) {
			startingPerson = 1;
		} else {
			startingPerson = 2;
		}
	/*	
		while (inBattle) {
			
			if (startingPerson == 1) {
				inBattle = Player.playerMove(Enemy);
				startingPerson = 2;
			} else {
				inBattle = Enemy.enemyMove(Player);
				startingPerson = 1;
			}
			
		}
		
		*/
		
		
		//If an animation is playing, do not take key inputs.
		if (this.inAnimation == true) {
			this.currentStep += 2;
			if (this.menuButtonType == "Attack") {
				this.inAnimation = this.playerAttackAnimation(this.currentStep);
			}
			
			//Always finish animations before exiting the battle screen
			return(true);

		}else {
			
			//reset animation frames
			this.currentStep = 0;
			
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
					
					this.menuButtonType = menuArray[menuButtonX][menuButtonY].getButtonType();
					
					if (this.menuButtonType == "Attack") {
						this.inAnimation = true;
						this.inBattle = Player.playerMove(Enemy, this.menuButtonType);
						
					}else if (this.menuButtonType == "Taunt") {
						
					}else if (this.menuButtonType == "Fly") {
						
					}else if (this.menuButtonType == "Item") {
						
					}
					
				}
			});
			
			// Continue/End the battle
			return(this.inBattle);
			
		} //End of else statement
		
	}//End of Update
	
	/**
	 * This will be called when the attack button is selected and the enter key is pressed.
	 * Plays an animation of the player attacking the enemy.
	 * 
	 * @param player
	 * 						The player sprite that will attack the enemy.
	 * @param currentStep
	 * 						The current frame of the animation (goes up by 2)
	 * @return
	 * 						True or false, based on whether the animation is over or not.
	 */
	public boolean playerAttackAnimation(int currentStep) {
		
		int runDistance = 64*2;
		int animationLength = runDistance*2;
		
		if(currentStep == animationLength + 2) {
			playerAnimation.animation.play();
			playerAnimation.animation.setOffsetY(0);
			return(false);
			
		}else if ( currentStep <= runDistance) {
			playerAnimation.animation.setOffsetY(128);
			playerAnimation.animation.play();
			playerAnimation.moveX(this.stepSize);
			return(true);
			
		}else if ( currentStep <= animationLength) {
			playerAnimation.animation.setOffsetY(256);
			playerAnimation.animation.play();
			playerAnimation.moveX(-this.stepSize);
			return(true);
		}
		return(false);
		
		
	}//End of playerAttackAnimation
}
