import ui.TitleScreen;

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
public class DuckSoulsText {

	/**
	 * Plays DuckSouls.
	 * 
	 * TODO: Fill in JavaDoc
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		

		System.out.println("======== Duck Souls ========");
		
		
		System.out.println("Initialization Starting...");s
		initialize();
		System.out.println("Initialization Done.");
		
		
		System.out.println("Game Loop Starting...");
		loop();
		System.out.println("Game Loop Done.");
		
		
		System.out.println("Game Ending...");
		end();
		System.out.println("Game Ending Done.");
		
		
		System.out.println("Cleanup Starting...");
		cleanup();
		System.out.println("Cleanup Done.");
		

	}
	
	/**
	 * Calls the init methods of all game components.
	 */
	private static void initialize() {
		
		// TODO: Initialization
		TitleScreen.menu();
		
	}
	
	/**
	 * Calls the loop methods of all game components.
	 */
	private static void loop() {
		
		// TODO: Main Game Loop
		
	}
	
	/**
	 * Calls the end methods of all game components.
	 */
	private static void end() {
		
		// TODO: Game Ending
		
	}
	
	/**
	 * Calls the cleanup methods of all game components.
	 */
	private static void cleanup() {
		
		// TODO: Cleanup
		
	}

}
