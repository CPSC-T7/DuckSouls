package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.MenuButton;
import animation.SpriteAnimation;
import battle.Loop;
import entities.Enemy;
import entities.Player;
import animation.BattleSprite;
import utils.Utilities;

import items.Item;

/**
 * 
 * @author Wylee McAndrews
 * 
 *         Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleScene {

	// Keep track of the currently selected button
	private int[] menuButton = new int[] {0,0};
	private String menuButtonType;

	// The distance that the player runs to hit the enemy, and vice versa
	private final int runDistance = 80 * 2;
	// The step size per frame
	private final int stepSize = 4;
	// The current step of the animation
	private int currentStep = 0;

	// Whether or not an animation is playing
	private boolean inAnimation = false;
	private boolean endGame 	= false;
	
	// Whose turn it is
	private boolean playerTurn = true;

	// If the battle has ended
	private boolean inBattle = true;
	
	//The entities
	private Player player;
	private Enemy enemy;

	/*
	 * 
	 * Images, ImageViews, Animations
	 * 
	 */
	
	// Background image and viewer
	private final Image battleBackgroundImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Backgrounds/Sewer-Battle.png");
	private ImageView battleBackgroundImageView = new ImageView(battleBackgroundImage);

	// Player image and viewer
	private final Image playerImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Entities/Duck/Battle/Duck.png");
	private ImageView playerImageView = new ImageView(playerImage);

	// Player sprite class
	private BattleSprite playerAnimation = new BattleSprite(playerImageView, 0);

	// Enemy image and viewer
	private Image enemyImage = new Image("file:///" + Utilities.parentDir + "/Sprites/Entities/Rat/Battle/Rat.png");
	private ImageView enemyImageView = new ImageView(enemyImage);

	// Enemy sprite class
	private BattleSprite enemyAnimation = new BattleSprite(enemyImageView, 64 * 6);

	/* Battle menu buttons */
	// Attack button image and viewer
	private final Image attackButtonImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Attack.png");
	private ImageView attackButtonImageView = new ImageView(attackButtonImage);

	// Taunt button image and viewer
	private final Image tauntButtonImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Taunt.png");
	private ImageView tauntButtonImageView = new ImageView(tauntButtonImage);

	// Quack button image and viewer
	private final Image flyButtonImage = new Image("file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Fly.png");
	private ImageView flyButtonImageView = new ImageView(flyButtonImage);

	// Item button image and viewer
	private final Image itemButtonImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Item.png");
	private ImageView itemButtonImageView = new ImageView(itemButtonImage);
	
	/*
	 * 
	 * Groups, Stages, Scenes, Panes
	 * 
	 */

	// Root group will separate all layers
	private Group root = new Group();

	// Background, menu, and player layers (drawn in order of appearance)
	private Pane backgroundLayer = new Pane();
	private Pane menuLayer = new Pane(attackButtonImageView, tauntButtonImageView, flyButtonImageView,
			itemButtonImageView);
	private Pane playerLayer = new Pane();

	// Add all sprites to draw in order
	private Pane groupPane = new Pane(backgroundLayer, menuLayer, playerLayer);

	// 2D array of menu buttons [rows][columns]
	private MenuButton[][] menuArray = new MenuButton[][] {
			{ new MenuButton(attackButtonImageView, "Attack", 10, 64 * 6),
					new MenuButton(tauntButtonImageView, "Taunt", 10, 64 * 6 + 50) },

			{ new MenuButton(flyButtonImageView, "Fly", 160, 64 * 6),
					new MenuButton(itemButtonImageView, "Item", 160, 64 * 6 + 50) } };

	// The BattleGUI Stage and Scene
	private Stage window;
	private Scene scene = new Scene(root);

	/**
	 * Constructor for the new BattleScene
	 * 
	 * @param window
	 * 				The Stage to draw the battle scene to
	 * @param battleLogic
	 * 				The logic behind entity moves
	 */
	public BattleScene(Stage window/*, TestBattleScripts battleLogic*/, Player player, Enemy enemy) {
		
		this.window = window;
		//this.battleLogic = battleLogic;
		this.player = player;
		this.enemy = enemy;

		// Add all layers to the main group
		root.getChildren().add(groupPane);

		// Add nodes of the background and player to their respective layers
		backgroundLayer.getChildren().add(battleBackgroundImageView);
		playerLayer.getChildren().addAll(this.playerAnimation, this.enemyAnimation);

		// Add 2d array of menu buttons to menuLayer
		menuLayer.getChildren().addAll(menuArray[0][0], menuArray[0][1], menuArray[1][0], menuArray[1][1]);

		// Start the player, enemy and button animations (background has none)
		playerAnimation.animation.play();
		enemyAnimation.animation.play();
		menuArray[menuButton[0]][menuButton[1]].animation.play();
		menuArray[menuButton[0]][menuButton[1]].animation.setOffsetY(40);

		// Set the scene
		window.setScene(scene);
		window.show();

	} // End of BattleScene

	/**
	 * Update the battle to get user input, or play animations
	 * 
	 * @return	
	 * 			If the battle is over:  false
	 * 			If the battle is going: true
	 */
	public boolean update() {

		window.setScene(scene);

		// If a move animation is playing:
		if (this.inAnimation) {
			this.currentStep += 2;
			
			// If the player goes first:
			// Run the player animation then finish with the enemy animation
			if (Loop.playerFirst(this.player, this.enemy)) {
				if(this.playerTurn) {
					//Change animation depending on move
					switch(this.menuButtonType) {
					
						case "Attack":
							this.playerTurn = this.playerAttackAnimation();
							break;
							
						case "Taunt":
							this.playerTurn = this.playerTauntAnimation();
							break;
							
						case "Fly":
							//this.playerTurn = this.playerFlyAnimation();
							break;
					}

					
				// If the enemy is not dead run it's move
				}else if (!Loop.checkDeath(this.enemy, false, false)){
					
					// If the player dies run the enemy move, then player death animation
					if (Loop.checkDeath(this.player, true, false)) {
						
						if (!this.endGame){
							this.endGame = !this.enemyAttackAnimation();
							this.playerTurn = false;
							
						}else{
							this.inBattle = this.playerDeathAnimation(); 
						}
						
					// If the player does not die end the animation after enemy move
					}else {
						this.inAnimation = this.enemyAttackAnimation();
					}
					
				// If the enemy is dead run it's death animation
				}else {
					this.inBattle = enemyDeathAnimation();
				}
				
			// If the enemy goes first:
			// Run the enemy animation then finish with the player animation
			}else {
				if(!this.playerTurn) {
					this.playerTurn = !this.enemyAttackAnimation();
					
				// If the player is not dead run their animation
				}else if (!Loop.checkDeath(this.player, true, false)){
					
					//Change animation depending on move
					switch(this.menuButtonType) {
					
						case "Attack":
							// If the enemy dies run the player move, then enemy death animation
							if (Loop.checkDeath(this.enemy, false, false)) {
								
								if (!this.endGame){
									this.endGame = !this.playerAttackAnimation();
									this.playerTurn = true;
									
								}else{
									this.inBattle = this.enemyDeathAnimation(); 
								}
								
							// If the enemy does not die end the animation after player move
							}else {
								this.inAnimation = this.playerAttackAnimation();
							}
							break;
							
						case "Taunt":
							this.inAnimation = this.playerTauntAnimation();
							break;
							
						case "Fly":
							//this.playerTurn = this.playerFlyAnimation();
							break;
							
					}
					
				// If the player is dead run their death animation
				}else {
					this.inBattle = playerDeathAnimation();
				}
			}

		// If there is no move animation running
		} else if (!this.inAnimation) {

			// reset animation frames
			this.currentStep = 0;
		}

		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {

			// Don't take input while a priority animation is playing
			if (!this.inAnimation) {

				switch (key.getCode()) {

					case W:
	
						if (menuButton[1] == 1) {
							// Move the button Y position to 0
							selectMenuButton('V', 0);
						}
	
						break;
	
					case A:
	
						if (menuButton[0] == 1) {
							// Move the button X position to 0
							selectMenuButton('H', 0);
						}
	
						break;
	
					case S:
	
						if (menuButton[1] == 0) {
							// Move the button Y position to 1
							selectMenuButton('V', 1);
						}
	
						break;
	
					case D:
	
						if (menuButton[0] == 0) {
							// Move the button X position to 1
							selectMenuButton('H', 1);
						}
	
						break;
	
					case ENTER:
	
						this.menuButtonType = menuArray[menuButton[0]][menuButton[1]].getButtonType();
						
						// If the player goes first
						if (Loop.playerFirst(this.player, this.enemy)) {
							
							Loop.executeMove(true, this.menuButtonType, this.player, this.enemy);
							this.inAnimation = true;
							this.playerTurn = true;
							
							this.inBattle = true;
	
							// If the enemy survives, do its turn.
							if (Loop.checkDeath(this.enemy, false, false)) {
								System.out.println("Player Wins!");

								
							}else {
								
								Loop.executeMove(false, "Attack", this.player, this.enemy);
								
								// If the player dies
								if (Loop.checkDeath(this.player, true, false)) {
									System.out.println("Enemy Wins");

								}
							}
						
						// If the enemy goes first
						}else {

							Loop.executeMove(false, "Attack", this.player, this.enemy);
							this.inAnimation = true;
							this.playerTurn = false;
							
							//TODO: Change battle outcome based on moves
							this.inBattle = true;
	
							// If the player survives, do their turn.
							if (Loop.checkDeath(this.player, true, false)) {
								System.out.println("Enemy Wins");

							}else {
								
								Loop.executeMove(true, this.menuButtonType, this.player, this.enemy);
								
								// If the enemy dies
								if (Loop.checkDeath(this.enemy, false, false)) {
									System.out.println("Player Wins");

								}
							}
						}
				}// End of Switch
			}
		}); // End of keyPressEvents

		return (this.inBattle);

	}// End of Update

	/**
	 * Select one of the menu buttons based on direction
	 * 
	 * @param direction
	 *            The plane to move on (Horizontal / Vertical)
	 * @param button
	 *            The button to move to on the 'direction' plane
	 */
	public void selectMenuButton(char direction, int button) {
		menuArray[menuButton[0]][menuButton[1]].animation.play();
		menuArray[menuButton[0]][menuButton[1]].animation.setOffsetY(0);
		if (direction == 'H') {
			menuButton[0] = button;
		} else {
			menuButton[1] = button;
		}
		menuArray[menuButton[0]][menuButton[1]].animation.play();
		menuArray[menuButton[0]][menuButton[1]].animation.setOffsetY(40);
	}

	/**
	 * This will be called when the attack button is selected and the enter key is
	 * pressed. Plays an animation of the player attacking the enemy.
	 * 
	 * @return 
	 * True if the animation is going, False if it is over
	 */
	public boolean playerAttackAnimation() {

		int animationLength = this.runDistance * 2  + 100;

		// End the player's turn
		if (this.currentStep >= animationLength + 2) {
			this.currentStep = 0;
			playerAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			return (false);

		// Run towards the enemy
		} else if (this.currentStep <= this.runDistance) {
			playerAnimation.animation.setOffsetY(playerAnimation.RUN_RIGHT_POSITION);
			playerAnimation.animation.play();
			playerAnimation.moveX(this.stepSize);
			return (true);

		// Attack the enemy
		} else if (this.currentStep <= this.runDistance + 100) {
			playerAnimation.animation.setOffsetY(playerAnimation.ATTACK_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.HURT_POSITION);
			enemyAnimation.animation.play();
			return (true);

		// Run away from the enemy
		} else if (this.currentStep <= animationLength) {
			playerAnimation.animation.setOffsetY(playerAnimation.RUN_LEFT_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.moveX(-this.stepSize);
			return (true);
		}
		return (false);

	}// End of playerAttackAnimation
	
	/**
	 * Player taunt animation
	 * 
	 * @return
	 * True if the animation is going, False if it is over
	 */
	public boolean playerTauntAnimation() {
		
		int animationLength = 100;
		
		// If the death animation is over return false
		if (this.currentStep >= animationLength) {
			this.currentStep = 0;
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			return (false);
		}else {
			playerAnimation.animation.setOffsetY(playerAnimation.ATTACK_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.HURT_POSITION);
			enemyAnimation.animation.play();
			return (true);
		}
	}// End of playerTauntAnimation
	
	
	/**
	 * Player death animation
	 * 
	 * @return
	 * True if the animation is going, False if it is over
	 */
	public boolean playerDeathAnimation() {
		
		int animationLength = this.runDistance*2;
		
		// If the death animation is over return false
		if (this.currentStep >= animationLength) {
			this.currentStep = 0;
			Loop.postBattle(true, this.player, this.enemy, false);
			return (false);
		}else {
			playerAnimation.animation.setOffsetY(playerAnimation.DEAD_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			return (true);
		}
	}// End of playerDeathAnimation

	/**
	 * Plays an animation of the enemy attacking the player,
	 * 
	 * @return
	 * True if the animation is going, False if it is over
	 */
	public boolean enemyAttackAnimation() {

		int animationLength = this.runDistance * 2 + 100;

		// End the enemy's turn
		if (this.currentStep >= animationLength + 2) {
			this.currentStep = 0;
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			return (false);

		// Run towards the player
		} else if (this.currentStep <= this.runDistance) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.RUN_LEFT_POSITION);
			enemyAnimation.animation.play();
			enemyAnimation.moveX(-this.stepSize);
			return (true);

		// Attack the player
		} else if (this.currentStep <= this.runDistance + 100) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.ATTACK_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.DEAD_POSITION);
			playerAnimation.animation.play();
			return (true);

		// Run away from the player
		} else if (this.currentStep <= animationLength) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.RUN_RIGHT_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.moveX(this.stepSize);
			return (true);
		}
		return (false);

	}// End of enemyAttackAnimation
	
	
	/**
	 * Enemy taunt animation
	 * 
	 * @return
	 * True if the animation is going, False if it is over
	 */
	public boolean enemyTauntAnimation() {
		
		int animationLength = 100;
		
		// If the death animation is over return false
		if (this.currentStep >= animationLength) {
			this.currentStep = 0;
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			return (false);
		}else {
			enemyAnimation.animation.setOffsetY(enemyAnimation.ATTACK_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.HURT_POSITION);
			playerAnimation.animation.play();
			return (true);
		}
	}// End of enemyTauntAnimation
	
	
	/**
	 * Enemy death animation
	 * 
	 * @return
	 * True if the animation is going, False if it is over
	 */
	public boolean enemyDeathAnimation() {
		
		int animationLength = this.runDistance*2;
		
		// If the death animation is over return false
		if (this.currentStep >= animationLength) {
			this.currentStep = 0;
			Loop.postBattle(false, this.player, this.enemy, false);
			return (false);
		}else {
			enemyAnimation.animation.setOffsetY(playerAnimation.DEAD_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			return (true);
		}
	}// End of playerDeathAnimation
}
