package scenes;

import entities.Player;
import genericInterfaces.Drawable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MenuButton;
import utils.Utilities;

/**
 * Draws the pause menu.
 * 
 * @author Wylee McAndrews
 *
 */
public class PauseScene implements Drawable{

	/*
	 * 
	 * CONSTANTS
	 * 
	 */

	private static final int TILESIZE 			= 64;
	
	private static final int BUTTON_1_POSITION 	= TILESIZE * 4;
	private static final int BUTTON_2_POSITION 	= TILESIZE * 6;
	
	
	// Pause background image and imageview
	private final Image pauseImage = new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Pause.png");
	private final ImageView pauseImageView = new ImageView(pauseImage);
	
	// Attack button image and viewer
	private final Image saveImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Main/Save.png");
	private ImageView saveImageView = new ImageView(saveImage);

	// Taunt button image and viewer
	private final Image exitImage = new Image(
			"file:///" + Utilities.parentDir + "/Sprites/Menus/Main/Exit.png");
	private ImageView exitImageView = new ImageView(exitImage);
	
	private int buttonSelected = 0;
	
	// Array of menu buttons (Save and Exit)
	private MenuButton[] menuArray = new MenuButton[] { 
			new MenuButton(saveImageView, "Save", 328, 100, 128, BUTTON_1_POSITION),
			new MenuButton(exitImageView, "Exit", 328, 100, 128, BUTTON_2_POSITION) };
	
	// Menu Layers
	private Pane backgroundLayer = new Pane(pauseImageView);
	private Pane mainMenuLayer = new Pane(menuArray);
	private Group root = new Group(backgroundLayer, mainMenuLayer);
	
	// If the player wishes to exit the pause menu
	private boolean paused = true;
	
	// The BattleGUI Stage and Scene
	private Stage window;
	private Scene scene = new Scene(root);


	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * PauseMenu constructor
	 */
	public PauseScene(Stage window) {
		this.window = window;
		
		// Play button animations (allow selecting)
		menuArray[0].animation.play();
		menuArray[1].animation.play();
		
		// Select "Save" first
		menuArray[1].animation.setOffsetY(0);
		menuArray[0].animation.setOffsetY(100);
		
		window.setScene(scene);
		window.show();
	}

	/**
	 * Updates the pause menu
	 * 
	 * @return paused
	 * 				Whether or not the player should still be in the menu.
	 */
	public boolean update() {
		
		// Check for key input
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			
				case W: // Select the top button
					if (buttonSelected == 1) {
						menuArray[1].animation.setOffsetY(0);
						menuArray[0].animation.setOffsetY(100);
						buttonSelected -= 1;
					}
					break;
					
				case S:// Select the bottom button
					if (buttonSelected == 0) {
						menuArray[0].animation.setOffsetY(0);
						menuArray[1].animation.setOffsetY(100);
						buttonSelected += 1;
					}
					break;
					
				case ENTER: // Choose the selected button
					
					if (buttonSelected == 1) System.exit(0);
					//else //save the game state
					break;
					
				case ESCAPE: // Leave the pause menu
					System.out.println("Hit Escape");
					paused = false;
					break;
			}
			
		});
		
		return(paused);
	}

	@Override
	public Image getImage() {
		// Does nothing in this class
		return null;
	}

	@Override
	public String getStringRepr() {
		// Does nothing in this class
		return null;
	}

}
