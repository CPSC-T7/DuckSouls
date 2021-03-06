package scenes;

import genericInterfaces.Drawable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Draws the pause menu.
 * 
 * @author Wylee McAndrews
 *
 */
public class DeathScene implements Drawable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final int	TILESIZE		= 64;
	
	// Pause background image and imageview
	private final Image		deathImage		= new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Death.png");
	private final ImageView	deathImageView	= new ImageView(deathImage);
	
	// Menu Layers
	private Pane			backgroundLayer	= new Pane(deathImageView);
	private Group			root			= new Group(backgroundLayer);
	
	// If the player wishes to exit the pause menu
	private boolean			dead			= true;
	
	// The BattleGUI Stage and Scene
	private Stage			window;
	private Scene			scene			= new Scene(root);
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * DeathScreen constructor
	 */
	public DeathScene(Stage window) {
		this.window = window;
		window.setScene(scene);
		window.show();
	}
	
	/**
	 * Updates the death menu
	 */
	@SuppressWarnings("incomplete-switch")
	public boolean update() {
		this.window.setScene(scene);
		
		// Check for key input
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
				
				case ENTER:
					
					System.exit(0);
					
					break;
				
			}
			
		});
		
		return (dead);
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
