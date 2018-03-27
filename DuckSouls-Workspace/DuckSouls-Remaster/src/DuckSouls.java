
/**
 * The main class to play DuckSouls.
 *
 * TODO: Fill in JavaDoc
 *
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @author Cassie Platel
 * @author Rahmanta Satriana
 * @author Colin Yeung
 *
 */
public class DuckSouls {
	
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
		loop();
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
	private static void loop() {

		// TODO: Main Game Loop
		
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
		
	}
	
}
