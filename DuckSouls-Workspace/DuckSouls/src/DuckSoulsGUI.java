import ui.TitleScreen;
import battle.DuckObject;
import game.Controller_GUI;
import game.Controller_GUI_Arcade;
import javafx.application.Application;

/**
 * The main class to play DuckSouls.
 *
 * TODO: Fill in JavaDoc
 *
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @author Cassie Platel
 * @author Nadhif Satriana
 * @author Colin Yeung
 *
 */
public class DuckSoulsGUI {
	
	/**
	 * Plays DuckSouls.
	 *
	 * TODO: Fill in JavaDoc
	 *
	 * @param args
	 *            Not used.
	 */
	public static void main(String[] args) {
		
		System.out.println("Intitialization Starting...");
		initialize();
		System.out.println("Intitialization Done.");
		
		System.out.println("Pre-Loop Starting...");
		preLoop();
		System.out.println("Pre-Loop Done.");
		
		System.out.println("Game Loop Starting...");
		loop(Integer.parseInt(args[0]));
		System.out.println("Game Loop Done.");
		
		System.out.println("Post-Loop Starting...");
		postLoop();
		System.out.println("Post-Loop Done.");
		
		System.out.println("Cleanup Starting...");
		cleanup();
		System.out.println("Cleanup Done.");
		
	}
	
	/**
	 * Initializes all game components.
	 */
	private static void initialize() {
		
		// TODO: Initialization
		
	}
	
	/**
	 * Runs what needs to run before the game loop.
	 */
	private static void preLoop() {
		
		// TODO: Pre Main Game Loop
		
		
		
	}
	
	/**
	 * Runs what needs to be continuously run for the game to work.
	 */
	private static void loop(int mode) {

		switch(mode) {
			
			case 0: // Story
				Application.launch(Controller_GUI.class);
				break;
			
			case 1: // Arcade
				Application.launch(Controller_GUI_Arcade.class);
				break;
				
			default:
				System.out.println("Mode not recognized. Exiting...");
				break;
			
		}
		
	}
	
	/**
	 * Runs what needs to be run after the loop.
	 */
	private static void postLoop() {
		
		// TODO: Game Ending
		
	}
	
	/**
	 * Cleans up any resources.
	 */
	private static void cleanup() {
		
		// TODO: Cleanup
		
		DuckObject.cleanup();
		TitleScreen.cleanup();
		
	}
	
}
