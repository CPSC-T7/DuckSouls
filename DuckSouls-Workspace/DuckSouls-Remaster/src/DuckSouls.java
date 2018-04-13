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
	 *            The list of arguments for which version of DuckSouls to play.
	 *            <ol>
	 *            <li>The first argument sets whether the game is running in GUI
	 *            mode or Text mode
	 *            <br>
	 *            ("1" for GUI and "0" for Text).
	 *            <li>The second argument sets whether the game is running in Story
	 *            mode or Arcade mode
	 *            <br>
	 *            ("1" for Story and "0" for Arcade).
	 *            <li>The third argument is only necessary when running story mode
	 *            (ignored in arcade mode), and decides if the game should be loaded
	 *            from the saved state, or if it should generate a new save state
	 *            <br>
	 *            ("1" to load the state and "0" to make a new state).
	 *            </ol>
	 */
	public static void main(String[] args) {
		
		System.out.println("Intitialization Starting...");
		initialize(args);
		System.out.println("Intitialization Done.");
		
		System.out.println("Game Loop Starting...");
		loop();
		System.out.println("Game Loop Done.");
		
	}
	
	/**
	 * Initializes all game components.
	 * 
	 * @param args
	 *            The list of arguments for which version of DuckSouls to play.
	 *            <ol>
	 *            <li>The first argument sets whether the game is running in GUI
	 *            mode or Text mode
	 *            <br>
	 *            ("1" for GUI and "0" for Text).
	 *            <li>The second argument sets whether the game is running in Story
	 *            mode or Arcade mode
	 *            <br>
	 *            ("1" for Story and "0" for Arcade).
	 *            <li>The third argument is only necessary when running story mode
	 *            (ignored in arcade mode), and decides if the game should be loaded
	 *            from the saved state, or if it should generate a new save state
	 *            <br>
	 *            ("1" to load the state and "0" to make a new state).
	 *            </ol>
	 */
	private static void initialize(String[] args) {
		
		GameData.IS_GUI = (args[0].equals("1"));
		GameData.IS_STORY = (args[1].equals("1"));
		if (args[1].equals("1")) {
			GameData.LOAD_GAME = (args[2].equals("1"));
		}
		
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
	
}
