package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MenuButton;
import ui.NumberSprite;
import ui.StaticSprite;
import battle.BattleLoop;
import entities.Enemy;
import entities.Player;
import animation.BattleSprite;
import utils.Utilities;

//TODO: Seperate animations from this class 
//		so that people don't get Carpal Tunnel 
//		from scrolling through all the lines

/**
 * 
 * @author Wylee McAndrews
 * 
 *         Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleScene {


	


	// The distance that the player runs to hit the enemy, and vice versa
	private final int runDistance = 80 * 2;
	// The step size per frame
	private final int stepSize = 4;
	// The current step of the animation
	private int currentStep = 0;
	
	
	// If the player is selecting an item or not
	private boolean itemSelection = false;
	// Whether or not an animation is playing
	private boolean inAnimation = false;
	private boolean endGame = false;

	// Whose turn it is
	private boolean playerTurn = true;

	// If the battle has ended
	private boolean inBattle = true;

	// The entities
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
	private Image enemyImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Entities/Rat/Battle/Rat.png");
	private ImageView enemyImageView = new ImageView(enemyImage);

	// Enemy sprite class
	private BattleSprite enemyAnimation = new BattleSprite(enemyImageView, 64 * 6);
	
	// Player Health image and viewer
	private final Image HPImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/HP.png");
	private final ImageView HPImageView = new ImageView(HPImage);
	
	// Numbers image and viewer
	private final String numbersImageURL =
			"file:///" + Utilities.parentDir + "/Sprites/Numbers/Numbers.png";

	/*
	 * Battle Menu and Item buttons
	 * 
	 */

	// Button Data Variables:
	
	// Keep track of the currently selected Menu button
	private int[] menuButton = new int[] { 0, 0 };
	
	// Keep track of the currently selected Item button
	private int[] itemButton = new int[] { 0, 0 };
	
	private String buttonType; // String rep of any button type
	
	// Move Button ImageViews:
	
	// Attack button ImageView
	private ImageView attackButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Attack.png"));

	// Taunt button ImageView
	private ImageView tauntButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Taunt.png"));

	// Quack button ImageView
	private ImageView flyButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Fly.png"));

	// Item button ImageView
	private ImageView itemButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Item.png"));

	// Item Button ImageViews:

	// Bugs button ImageView
	private ImageView bugsButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Bugs.png"));

	// Crouton button ImageView
	private ImageView croutonButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Crouton.png"));

	// Goo button ImageView
	private ImageView gooButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Goo.png"));

	// Fish button ImageView
	private ImageView fishButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Fish.png"));

	// Back button image and viewer
	private ImageView backButtonImageView = new ImageView(new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Battle/Back.png"));
	

	/*
	 * 
	 * Groups, Stages, Scenes, Panes
	 * 
	 */

	// Root group will separate all layers
	private Group root = new Group();

	// Background Layer
	private Pane backgroundLayer = new Pane();
	// Menu Layer
	private Pane menuLayer = new Pane(attackButtonImageView, tauntButtonImageView, flyButtonImageView,
			itemButtonImageView);
	// Item Menu Layer
	private Pane itemLayer = new Pane(bugsButtonImageView, croutonButtonImageView, gooButtonImageView,
			fishButtonImageView, backButtonImageView);
	// Player/Enemy Layer
	private Pane playerLayer = new Pane();
	
	// Battle message layer
	private Pane messageLayer = new Pane();
	
	// Add all sprites to draw in order
	private Pane groupPane = new Pane(backgroundLayer, menuLayer, itemLayer, playerLayer, messageLayer);

	// 2D array of Move menu buttons [rows][columns]
	private MenuButton[][] moveArray = new MenuButton[][] {
			{ new MenuButton(attackButtonImageView, "Attack", 120, 40, 10, 64 * 6),
					new MenuButton(tauntButtonImageView, "Taunt", 120, 40,  10, 64 * 6 + 50) },

			{ new MenuButton(flyButtonImageView, "Fly", 120, 40,  160, 64 * 6),
					new MenuButton(itemButtonImageView, "Item", 120, 40,  160, 64 * 6 + 50) } };

	// 2D array of Item menu buttons [rows][columns]
	private MenuButton[][] itemArray = new MenuButton[][] {
			{ new MenuButton(bugsButtonImageView, "Bugs", 120, 40,  10, 64 * 6),
					new MenuButton(croutonButtonImageView, "Crouton", 120, 40,  10, 64 * 6 + 50) },

			{ new MenuButton(gooButtonImageView, "Goo", 120, 40,  160, 64 * 6),
					new MenuButton(fishButtonImageView, "Fish", 120, 40,  160, 64 * 6 + 50) },

			{ new MenuButton(backButtonImageView, "Back", 120, 40,  310, 64 * 6) } };
			
	/*
	 * Battle Messages
	 * 
	 */
	
	// Player health section
	private StaticSprite HP = new StaticSprite(HPImageView, (int) HPImage.getWidth(), (int) HPImage.getHeight(), 310, 64*6);
			
	// 2D array of player health digits
	private NumberSprite[] playerHealth = new NumberSprite[] {
			new NumberSprite(new ImageView(new Image(numbersImageURL)), 340, 64 * 6 -10), 
			new NumberSprite(new ImageView(new Image(numbersImageURL)), 356, 64 * 6 -10)
	};

	// The BattleGUI Stage and Scene
	private Stage window;
	private Scene scene = new Scene(root);

	/**
	 * Constructor for the new BattleScene
	 * 
	 * @param window
	 *            The Stage to draw the battle scene to
	 * @param player
	 * 			  The player object
	 * @param enemy
	 * 			  The enemy object
	 */
	public BattleScene(Stage window, Player player, Enemy enemy) {

		this.window = window;
		// battleLogic = battleLogic;
		this.player = player;
		this.enemy = enemy;

		// Add all layers to the main group
		root.getChildren().add(groupPane);

		// Add nodes of the background and player to their respective layers
		backgroundLayer.getChildren().add(battleBackgroundImageView);
		playerLayer.getChildren().addAll(playerAnimation, enemyAnimation);

		// Add 2d array of menu buttons to menuLayer
		menuLayer.getChildren().addAll(moveArray[0][0], moveArray[0][1], moveArray[1][0], moveArray[1][1]);

		// Add 2d array of Item menu buttons to itemLayer
		itemLayer.getChildren().addAll(itemArray[0][0], itemArray[0][1], itemArray[1][0], itemArray[1][1],
				itemArray[2][0]);
		itemLayer.setVisible(false); // Make it invisible at the start of the battle.
		
		messageLayer.getChildren().addAll(playerHealth[0], playerHealth[1], HP);

		// Start the player, enemy and button animations (background has none)
		playerAnimation.animation.play();
		enemyAnimation.animation.play();
		moveArray[menuButton[0]][menuButton[1]].animation.play();
		moveArray[menuButton[0]][menuButton[1]].animation.setOffsetY(40);
		itemArray[menuButton[0]][menuButton[1]].animation.play();
		itemArray[menuButton[0]][menuButton[1]].animation.setOffsetY(40);
		playerHealth[0].animation.play();
		playerHealth[1].animation.play();
		
		// Set the scene
		this.window.setScene(scene);
		this.window.show();

		drawPlayerHealth();
		
	} // End of BattleScene

	/**
	 * Update the battle to get user input, or play animations
	 * 
	 * @return If the battle is over: false If the battle is going: true
	 */
	@SuppressWarnings("incomplete-switch")
	public boolean update() {
		
		window.setScene(scene);

		// If a move animation is playing:
		if (inAnimation) {
			currentStep += 2;

			// If the player goes first:
			// Run the player animation then finish with the enemy animation
			if (BattleLoop.playerFirst(player, enemy)) {
				if (playerTurn) {
					// Change animation depending on move
					switch (buttonType) {

					case "Attack":
						playerTurn = playerAttackAnimation();
						break;

					case "Taunt":
						playerTurn = playerTauntAnimation();
						break;

					case "Fly":
						inBattle = playerFlyAnimation();
						break;
					default:
						playerTurn = false;
						break;
					}

					// If the enemy is not dead run it's move
				} else if (!BattleLoop.checkDeath(enemy, false, false)) {

					// If the player dies run the enemy move, then player death animation
					if (BattleLoop.checkDeath(player, true, false)) {

						if (!endGame) {
							endGame = !enemyAttackAnimation();
							playerTurn = false;

						} else {
							inBattle = playerDeathAnimation();
						}

						// If the player does not die end the animation after enemy move
					} else {
						inAnimation = enemyAttackAnimation();
					}

					// If the enemy is dead run it's death animation
				} else {
					inBattle = enemyDeathAnimation();
				}

				// If the enemy goes first:
				// Run the enemy animation then finish with the player animation
			} else {
				if (!playerTurn) {
					playerTurn = !enemyAttackAnimation();

					// If the player is not dead run their animation
				} else if (!BattleLoop.checkDeath(player, true, false)) {

					// Change animation depending on move
					switch (buttonType) {

						case "Attack":
							// If the enemy dies run the player move, then enemy death animation
							if (BattleLoop.checkDeath(enemy, false, false)) {
	
								if (!endGame) {
									endGame = !playerAttackAnimation();
									playerTurn = true;
	
								} else {
									inBattle = enemyDeathAnimation();
								}
	
								// If the enemy does not die end the animation after player move
							} else {
								inAnimation = playerAttackAnimation();
							}
							break;
	
						case "Taunt":
							inAnimation = playerTauntAnimation();
							break;
	
						case "Fly":
							inBattle = playerFlyAnimation();
							break;
						default:
							inAnimation = false;
							break;

					}

					// If the player is dead run their death animation
				} else {
					inBattle = playerDeathAnimation();
				}
			}

			// If there is no move animation running
		} else if (!inAnimation) {

			// reset animation frames
			currentStep = 0;
		}

		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {

			// Don't take input while a priority animation is playing
			if (!inAnimation) {
				switch (key.getCode()) {

					case W:
	
						if (itemSelection) {
							if (itemButton[1] != 0) {
								// Move the button Y position to 0
								selectItemButton('V', 0);
							}
						} else {
							if (menuButton[1] != 0) {
								// Move the button Y position to 0
								selectMenuButton('V', 0);
							}
						}
	
						break;
	
					case A:
	
						if (itemSelection) {
							if (itemButton[0] != 0) {
								// Move the button X position to 0
								selectItemButton('H', itemButton[0] - 1);
							}
						} else {
							if (menuButton[0] != 0) {
								// Move the button X position to 0
								selectMenuButton('H', 0);
							}
						}
	
						break;
	
					case S:
	
						if (itemSelection) {
							if (itemButton[1] != 1 && itemButton[0] != 2) {
								// Move the button Y position to 1
								selectItemButton('V', itemButton[1] + 1);
							}
						} else {
							if (menuButton[1] != 1) {
								// Move the button Y position to 1
								selectMenuButton('V', 1);
							}
						}
	
						break;
	
					case D:
	
						if (itemSelection) {
							if (itemButton[0] != 2 && !(itemButton[0] == 1 && itemButton[1] == 1)) {
								// Move the button x position to 1
								selectItemButton('H', itemButton[0] + 1);
							}
						} else {
							if (menuButton[0] != 1) {
								// Move the button x position to 1
								selectMenuButton('H', 1);
							}
						}
	
						break;
	
					case ENTER:
	
						// Select the item to use
						if (itemSelection) {
							buttonType = itemArray[itemButton[0]][itemButton[1]].getButtonType();
							
							// If the player has the item, or wants to go back to the move menu
							if (player.hasItem(buttonType) || buttonType == "Back") {
								itemSelection = false;
								itemLayer.setVisible(false);
								messageLayer.setVisible(true);
							
							// If the player doesn't have the item, don't register a move.
							} else {
								break;
							}
						
						// Select a move to make
						}else {
							buttonType = moveArray[menuButton[0]][menuButton[1]].getButtonType();
						}
	
						// Let the player switch between battle/item menu
						if (buttonType == "Item") {
							itemSelection = true;
							itemLayer.setVisible(true);
							messageLayer.setVisible(false);
						} else if (buttonType == "Back") {
							itemLayer.setVisible(false);
							messageLayer.setVisible(true);
						
						// Run the player's move
						}else{
							// If the player goes first
							if (BattleLoop.playerFirst(player, enemy)) {
								BattleLoop.executeMove(true, buttonType, player, enemy);
								inAnimation = true;
								playerTurn = true;
								inBattle = true;
	
								// If the enemy survives do it's turn
								if (!BattleLoop.checkDeath(enemy, false, false)) {
									BattleLoop.executeMove(false, "Attack", player, enemy);
								}
	
								// If the enemy goes first
							} else {
								BattleLoop.executeMove(false, "Attack", player, enemy);
								inAnimation = true;
								playerTurn = false;
								inBattle = true;
	
								// If the player survives, do their turn.
								if (!BattleLoop.checkDeath(player, true, false)) {
									BattleLoop.executeMove(true, buttonType, player, enemy);
	
								}
							}
						}
						
				}// End of Switch
			}
		}); // End of keyPressEvents

		return (inBattle);

	}// End of Update
	
	/**
	 * Update the player's health
	 */
	public void drawPlayerHealth() {
		int currentHealth = (int) player.getHealth();
		this.playerHealth[0].setNumber(currentHealth / 10);
		this.playerHealth[1].setNumber(currentHealth % 10);
	}

	/**
	 * Select one of the menu buttons based on direction
	 * 
	 * @param direction
	 *            The plane to move on (Horizontal / Vertical)
	 * @param button
	 *            The button to move to on the 'direction' plane
	 */
	public void selectMenuButton(char direction, int button) {
		moveArray[menuButton[0]][menuButton[1]].animation.play();
		moveArray[menuButton[0]][menuButton[1]].animation.setOffsetY(0);
		if (direction == 'H') {
			menuButton[0] = button;
		} else {
			menuButton[1] = button;
		}
		moveArray[menuButton[0]][menuButton[1]].animation.play();
		moveArray[menuButton[0]][menuButton[1]].animation.setOffsetY(40);
	}

	/**
	 * Select one of the Item menu buttons based on direction
	 * 
	 * @param direction
	 *            The plane to move on (Horizontal / Vertical)
	 * @param button
	 *            The button to move to on the 'direction' plane
	 */
	public void selectItemButton(char direction, int button) {
		itemArray[itemButton[0]][itemButton[1]].animation.play();
		itemArray[itemButton[0]][itemButton[1]].animation.setOffsetY(0);
		if (direction == 'H') {
			itemButton[0] = button;
		} else {
			itemButton[1] = button;
		}
		itemArray[itemButton[0]][itemButton[1]].animation.play();
		itemArray[itemButton[0]][itemButton[1]].animation.setOffsetY(40);
	}
	
	/*
	 * Animations
	 * 
	 */

	/**
	 * Plays an animation of the player attacking the enemy.
	 * 
	 * @return True if the animation is going, False if it is over
	 */
	public boolean playerAttackAnimation() {

		int animationLength = runDistance * 2 + 100;

		// End the player's turn
		if (currentStep >= animationLength + 2) {
			currentStep = 0;
			playerAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			return (false);

			// Run towards the enemy
		} else if (currentStep <= runDistance) {
			playerAnimation.animation.setOffsetY(playerAnimation.RUN_RIGHT_POSITION);
			playerAnimation.animation.play();
			playerAnimation.moveX(stepSize);
			return (true);

			// Attack the enemy
		} else if (currentStep <= runDistance + 100) {
			playerAnimation.animation.setOffsetY(playerAnimation.ATTACK_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.HURT_POSITION);
			enemyAnimation.animation.play();
			return (true);

			// Run away from the enemy
		} else if (currentStep <= animationLength) {
			playerAnimation.animation.setOffsetY(playerAnimation.RUN_LEFT_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.moveX(-stepSize);
			return (true);
		}
		return (false);

	}// End of playerAttackAnimation
	
	/**
	 * Plays an animation of the player leaving the battle.
	 * 
	 * @return True if the animation is going, False if it is over
	 */
	public boolean playerFlyAnimation() {

		int animationLength = runDistance;

		// End the player's turn
		if (currentStep >= animationLength + 2) {
			return (false);

			// Run away from the enemy
		} else if (currentStep <= animationLength) {
			playerAnimation.animation.setOffsetY(playerAnimation.RUN_LEFT_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.moveX(-stepSize);
			return (true);
		}
		return (false);

	}// End of playerFlyAnimation

	/**
	 * Player taunt animation
	 * 
	 * @return True if the animation is going, False if it is over
	 */
	public boolean playerTauntAnimation() {

		int animationLength = 100;

		// If the death animation is over return false
		if (currentStep >= animationLength) {
			currentStep = 0;
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			return (false);
		} else {
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
	 * @return True if the animation is going, False if it is over
	 */
	public boolean playerDeathAnimation() {

		int animationLength = runDistance * 2;

		// If the death animation is over return false
		if (currentStep >= animationLength) {
			currentStep = 0;
			BattleLoop.postBattle(true, player, enemy, false);
			return (false);
		} else {
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
	 * @return True if the animation is going, False if it is over
	 */
	public boolean enemyAttackAnimation() {

		int animationLength = runDistance * 2 + 100;

		// End the enemy's turn
		if (currentStep >= animationLength + 2) {
			currentStep = 0;
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			return (false);

			// Run towards the player
		} else if (currentStep <= runDistance) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.RUN_LEFT_POSITION);
			enemyAnimation.animation.play();
			enemyAnimation.moveX(-stepSize);
			return (true);

			// Attack the player
		} else if (currentStep <= runDistance + 100) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.ATTACK_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.DEAD_POSITION);
			playerAnimation.animation.play();
			
			drawPlayerHealth();
			return (true);

			// Run away from the player
		} else if (currentStep <= animationLength) {
			enemyAnimation.animation.setOffsetY(enemyAnimation.RUN_RIGHT_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(playerAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			enemyAnimation.moveX(stepSize);
			return (true);
		}
		return (false);

	}// End of enemyAttackAnimation

	/**
	 * Enemy taunt animation
	 * 
	 * @return True if the animation is going, False if it is over
	 */
	public boolean enemyTauntAnimation() {

		int animationLength = 100;

		// If the death animation is over return false
		if (currentStep >= animationLength) {
			currentStep = 0;
			enemyAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			enemyAnimation.animation.play();
			return (false);
		} else {
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
	 * @return True if the animation is going, False if it is over
	 */
	public boolean enemyDeathAnimation() {

		int animationLength = runDistance * 2;

		// If the death animation is over return false
		if (currentStep >= animationLength) {
			currentStep = 0;
			BattleLoop.postBattle(false, player, enemy, false);
			return (false);
		} else {
			enemyAnimation.animation.setOffsetY(playerAnimation.DEAD_POSITION);
			enemyAnimation.animation.play();
			playerAnimation.animation.setOffsetY(enemyAnimation.IDLE_POSITION);
			playerAnimation.animation.play();
			return (true);
		}
	}// End of enemyDeathAnimation
}
