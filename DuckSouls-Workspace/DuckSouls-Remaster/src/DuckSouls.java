import controllers.GUIGame;
import controllers.GameData;
import controllers.TextGame;
import javafx.application.Application;
import scenes.TitleScene;

/**
 * The main class to play DuckSouls.
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
	 * @param args
	 *            The list of arguments for which version of DuckSouls to play. The
	 *            first argument sets whether the game is running in GUI mode or
	 *            Text mode ("1" for GUI and "0" for Text). The second argument sets
	 *            whether the game is running in Story mode or Arcade mode ("1" for
	 *            Story and "0" for Arcade).
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
	 * 
	 * @param args
	 *            The list of arguments for which version of DuckSouls to play. The
	 *            first argument sets whether the game is running in GUI mode or
	 *            Text mode ("1" for GUI and "0" for Text). The second argument sets
	 *            whether the game is running in Story mode or Arcade mode ("1" for
	 *            Story and "0" for Arcade).
	 */
	private static void initialize(String[] args) {
		
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
			TitleScene.displayMenu();
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
