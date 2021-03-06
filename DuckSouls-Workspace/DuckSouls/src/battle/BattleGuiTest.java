package battle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MenuButton;
import utils.Utilities;

import items.Item;

/**
 * 
 * @author Wylee McAndrews
 * 
 *         Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleGuiTest {
	
	// Keep track of the currently selected button
	private int				menuButtonX					= 0;
	private int				menuButtonY					= 0;
	private String			menuButtonType;
	
	// The distance that the player runs to hit the enemy, and vice versa
	private final int		runDistance					= 64 * 2;
	// The step size per frame
	private final int		stepSize					= 4;
	// The current step of the animation
	private int				currentStep					= 0;
	
	// Whether or not an animation is playing
	private boolean			inAnimation					= false;
	
	// Whose turn it is
	private boolean			playerTurn					= true;
	
	// If the enemy will take a turn or not
	private boolean			enemyAttacks				= true;
	// If the battle has ended
	private boolean			inBattle					= true;
	
	// Background image and viewer
	private final Image		battleBackgroundImage		= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Backgrounds/Sewer-Battle.png");
	private ImageView		battleBackgroundImageView	= new ImageView(battleBackgroundImage);
	
	// Player image and viewer
	private final Image		playerImage					= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Entities/Duck/Battle/Duck.png");
	private ImageView		playerImageView				= new ImageView(playerImage);
	
	// Player sprite class
	private BattleSprite	playerAnimation				= new BattleSprite(playerImageView, 0);
	
	// Enemy image and viewer
	private Image			enemyImage					= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Entities/Rat/Battle/Rat.png");
	private ImageView		enemyImageView				= new ImageView(enemyImage);
	
	// Enemy sprite class
	private BattleSprite	enemyAnimation				= new BattleSprite(enemyImageView, 64 * 6);
	
	/* Battle menu buttons */
	// Attack button image and viewer
	private final Image		attackButtonImage			= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Menus/Battle/Attack.png");
	private ImageView		attackButtonImageView		= new ImageView(attackButtonImage);
	
	// Taunt button image and viewer
	private final Image		tauntButtonImage			= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Menus/Battle/Taunt.png");
	private ImageView		tauntButtonImageView		= new ImageView(tauntButtonImage);
	
	// Quack button image and viewer
	private final Image		flyButtonImage			= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Menus/Battle/Fly.png");
	private ImageView		flyButtonImageView		= new ImageView(flyButtonImage);
	
	// Item button image and viewer
	private final Image		itemButtonImage				= new Image(
			"file:///" + Utilities.getParentDir() + "/Sprites/Menus/Battle/Item.png");
	private ImageView		itemButtonImageView			= new ImageView(itemButtonImage);
	
	// Root group will separate all layers
	private Group			root						= new Group();
	
	// Background, menu, and player layers (drawn in order of appearance)
	private Pane			backgroundLayer				= new Pane();
	private Pane			menuLayer					= new Pane(attackButtonImageView, tauntButtonImageView,
			flyButtonImageView, itemButtonImageView);
	private Pane			playerLayer					= new Pane();
	
	// Add all sprites to draw in order
	private Pane			groupPane					= new Pane(backgroundLayer, menuLayer, playerLayer);
	
	// 2D array of menu buttons [rows][columns]
	private MenuButton[][]	menuArray					= new MenuButton[][] {
			{ new MenuButton(attackButtonImageView, "Attack", 10, 64 * 6),
					new MenuButton(tauntButtonImageView, "Taunt", 10, 64 * 6 + 50) },
			
			{ new MenuButton(flyButtonImageView, "Fly", 160, 64 * 6),
					new MenuButton(itemButtonImageView, "Item", 160, 64 * 6 + 50) } };
	
	// The BattleGUI Stage and Scene
	private Stage			window;
	private Scene			scene						= new Scene(root);
	
	/**
	 * Start the JavaFX stage
	 */
	public BattleGuiTest(Stage window) {
		
		this.window = window;
		
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
		menuArray[menuButtonX][menuButtonY].animation.play();
		menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
		
	} // End of start()
	
	/**
	 * Set and show the scene on the window.
	 */
	public void setScene() {
		window.setScene(scene);
		window.show();
	}
	
	/**
	 * Update the scene based on user input: -Select moves (attack, taunt, etc)
	 * -Play Animations
	 * 
	 * @param scene
	 *            The current scene.
	 * @param player
	 *            The player reference (for sprites, animations, stats).
	 * @param menuArray
	 *            A 2D array of menu buttons used to make moves against an enemy.
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
		
		// If an animation is playing, do not take key inputs.
		if (this.inAnimation) {
			this.currentStep += 2;
			
			// Do the player's animation
			if (this.playerTurn) {
				if (this.menuButtonType == "Attack") {
					this.playerTurn = this.playerAttackAnimation();
				}
				// Do the enemy's animation
			} else if (this.enemyAttacks == true) {
				this.inAnimation = this.enemyAttackAnimation();
				
				// End the animation loop
			} else {
				this.inAnimation = false;
			}
			
			// Always finish animations before exiting the battle screen
			return (true);
			
		} else if (!this.inAnimation) {
			
			// reset animation frames
			this.currentStep = 0;
		}
			
		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {
			
			if(!this.inAnimation) {
			
				switch (key.getCode()) {
					
					case W:
						
						if (menuButtonY == 1) {
							// Move the button Y position to 0
							selectButton('V', 0);
						}
						
						break;
					
					case A:
						
						if (menuButtonX == 1) {
							// Move the button X position to 0
							selectButton('H', 0);
						}
						
						break;
					
					case S:
						
						if (menuButtonY == 0) {
							// Move the button Y position to 1
							selectButton('V', 1);
						}
						
						break;
					
					case D:
						
						if (menuButtonX == 0) {
							// Move the button X position to 1
							selectButton('H', 1);
						}
						
						break;
					
					case ENTER:
					
						this.menuButtonType = menuArray[menuButtonX][menuButtonY].getButtonType();
						
						if (this.menuButtonType == "Attack") {
							this.inAnimation = true;
							this.playerTurn = true;
							this.inBattle = Player.playerMove(Enemy, this.menuButtonType);
							
							// If the enemy survives, do its turn.
							if (this.inBattle) {
								this.inBattle = Enemy.enemyMove(Player, 2);
								this.enemyAttacks = true;
							} else {
								this.enemyAttacks = false;
							}
							
						} else if (this.menuButtonType == "Taunt") {
							
						} else if (this.menuButtonType == "Fly") {
							
						} else if (this.menuButtonType == "Item") {
							
						}
					break;
				}
			}
		});
		
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
	public void selectButton(char direction, int button) {
		menuArray[menuButtonX][menuButtonY].animation.play();
		menuArray[menuButtonX][menuButtonY].animation.setOffsetY(0);
		if (direction == 'H') {
			menuButtonX = button;
		} else {
			menuButtonY = button;
		}
		menuArray[menuButtonX][menuButtonY].animation.play();
		menuArray[menuButtonX][menuButtonY].animation.setOffsetY(40);
	}
	
	/**
	 * This will be called when the attack button is selected and the enter key is
	 * pressed. Plays an animation of the player attacking the enemy.
	 * 
	 * @param player
	 *            The player sprite that will attack the enemy. Plays an animation
	 *            of the player attacking the enemy.
	 * 
	 * @param currentStep
	 *            The current frame of the animation (goes up by 2)
	 * @return True or false, based on whether the animation is over or not.
	 */
	public boolean playerAttackAnimation() {
		
		int animationLength = this.runDistance * 2;
		
		// End the player's turn
		if (this.currentStep == animationLength + 2) {
			this.currentStep = 0;
			this.playerTurn = false;
			playerAnimation.animation.play();
			playerAnimation.animation.setOffsetY(0);
			return (false);
			
			// Run towards the enemy
		} else if (this.currentStep <= this.runDistance) {
			playerAnimation.animation.setOffsetY(128);
			playerAnimation.animation.play();
			playerAnimation.moveX(this.stepSize);
			return (true);
			
			// Run away from the enemy
		} else if (this.currentStep <= animationLength) {
			playerAnimation.animation.setOffsetY(256);
			playerAnimation.animation.play();
			playerAnimation.moveX(-this.stepSize);
			return (true);
		}
		return (false);
		
	}// End of playerAttackAnimation
	
	/**
	 * Plays an animation of the enemy attacking the player,
	 * 
	 * @param currentStep
	 * @return
	 */
	public boolean enemyAttackAnimation() {
		
		int animationLength = this.runDistance * 2;
		
		// End the enemy's turn
		if (this.currentStep == animationLength + 2) {
			this.currentStep = 0;
			enemyAnimation.animation.setOffsetY(0);
			enemyAnimation.animation.play();
			return (false);
			
			// Run towards the player
		} else if (this.currentStep <= this.runDistance) {
			enemyAnimation.animation.setOffsetY(128);
			enemyAnimation.animation.play();
			enemyAnimation.moveX(-this.stepSize);
			return (true);
			
			// Run away from the player
		} else if (this.currentStep <= animationLength) {
			enemyAnimation.animation.setOffsetY(256);
			enemyAnimation.animation.play();
			enemyAnimation.moveX(this.stepSize);
			return (true);
		}
		return (false);
		
	}
}
