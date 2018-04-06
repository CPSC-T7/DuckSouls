import controllers.GUIGame;
import controllers.GameData;
import controllers.TextGame;
import javafx.application.Application;
import tests.ParameterTests;

/**
 * The main class to play DuckSouls.
 *
 * TODO: Fill in JavaDoc
 *
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @author Cassie Platel
 * @author Rahmanta Satriana
 * @author Colin Au Yeung
 *
 */
public class DuckSouls {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
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
		initialize(args);
		System.out.println("Intitialization Done.");
		
		System.out.println("Game Loop Starting...");
		loop();
		System.out.println("Game Loop Done.");
		
		System.out.println("Cleanup Starting...");
		cleanup();
		System.out.println("Cleanup Done.");
		
	}
	
	/**
	 * Initializes all game components.
	 */
	private static void initialize(String[] args) {

		ParameterTests.assertTwoParams(args);

		GameData.IS_GUI = (args[0].equals("1"));
		GameData.IS_STORY = (args[1].equals("1"));
		
	}
	
	/**
	 * Runs what needs to be continuously run for the game to work.
	 */
	private static void loop() {

		if (GameData.IS_GUI) {
			Application.launch(GUIGame.class);
		} else {
			TextGame tg = new TextGame();
			tg.mainLoop();
		}
		
	}
	
	/**
	 * Cleans up any resources.
	 */
	private static void cleanup() {
		
		// TODO: Cleanup
		
	}
	
}
