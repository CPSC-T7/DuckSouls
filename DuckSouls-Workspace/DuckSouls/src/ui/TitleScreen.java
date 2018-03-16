package ui;

import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import utils.Utilities;


/**
 * Stores and runs the game's title screen, which disappears after you press
 * enter.
 * 
 * @author Wylee McAndrews
 */
public class TitleScreen {
	
	// The awesome tile ASCII art from
	// http://patorjk.com/software/taag/#p=display&f=Bloody&t=Duck%20Souls
	private static String	duckSoulsTitle	= 
			"\n\n"
			+ "\t\t\t\t\t\t\t █████▄  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████  \r\n"
			+ "\t\t\t\t\t\t\t ██   ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n"
			+ "\t\t\t\t\t\t\t ██   ██░██  ▒██░▒██    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n"
			+ "\t\t\t\t\t\t\t ██  ▄██░██  ░██░▒██▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n"
			+ "\t\t\t\t\t\t\t░██████░░▓████▓ ▒  ███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒ \r\n"
			+ "\t\t\t\t\t\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n"
			+ "\t\t\t\t\t\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n"
			+ "\t\t\t\t\t\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n"
			+ "\t\t\t\t\t\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n"
			+ "\t\t\t\t\t\t\t ░               ░                                                              \r\n";

	// Get user input from console
	private static Scanner scanner = new Scanner(System.in);
	
	
	// GUI Game variables:
	
	// If the TitleScreen should continue to be shown
	private boolean inTitle = true;
	
	// Group to add to the scene
	private Group root = new Group();
	
    // Menu Background Image (temporary)
	private Image attackButtonImage = new Image("file:///" + Utilities.getParentDir() + "/Sprites/Menus/Main/TitleScreen.png");
	private ImageView attackButtonImageView = new ImageView(attackButtonImage);
	
	// Game stage and new scene
	private Stage window;
	private Scene scene = new Scene(root);
	
	/**
	 * TitleScreen constructor for the GUI game.
	 * 
	 * @param window
	 * 				The stage to draw the TitleScreen scene to.
	 */
	public TitleScreen(Stage window) {
		this.window = window;
		
		//Add all layers to the main group
	    root.getChildren().add(attackButtonImageView);
	    
	    //Set the scene and show it on the window
	    window.setScene(scene);
		window.show();
	}
	
	/**
	 * TitleScreen constructor for the Text game.
	 * Takes no arguments.
	 */
	public TitleScreen() {
	}

	/**
	 * Display's the game's menu, clearing it when the user presses enter.
	 * Text version only.
	 */
	public static void displayMenu() {
		
		// Clear the console
		Utilities.clearConsole();
		
		// Tell the player to maximize the console then press enter
		System.out.println("Please maximize the console, then press enter.");
		scanner.nextLine();
		Utilities.clearConsole();
		
		// Print the tile and instructions
		System.out.print(duckSoulsTitle);
		System.out.println("\t\t\t\t\t\t\t\t\t\t       Press Enter To Start");
		
		// Wait for the user to press enter to continue, then clear the console
		scanner.nextLine();
		Utilities.clearConsole();
	}
	
	/**
	 * The update method for TitleScreen
	 */
	public boolean update() {
		
		// Set the scene to the title scene
		window.setScene(scene);
		
		// Check for key presses
		this.scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.ENTER) { // On ENTER key press
				
				// Tell the loop that the scene should no longer be the title
				this.inTitle = false;
			}
		});
		// Return the title status
		return(inTitle);
	}
	
	public static void cleanup() {
		scanner.close();
	}
	
}
