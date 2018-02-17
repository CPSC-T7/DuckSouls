import ui.TitleScreen;
import utils.Utilities;
import map.Map;

import map.TextRoom;
import battle.BattleWorldTest;
import battle.DuckObject;

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
public class DuckSoulsText {

	/**
	 * Plays DuckSouls.
	 *
	 * TODO: Fill in JavaDoc
	 *
	 * @param args Not used.
	 */


	public static void main(String[] args) {

		//Start the game on the title menu
		Map map = new Map();
		initialize(map);

		System.out.println("Pre-Loop Staring...");
		preLoop();
		System.out.println("Pre-Loop Done.");

		System.out.println("Game Loop Starting...");
		loop(map);
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
	private static void initialize(Map map) {
		map.initalization(0,2);

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
	private static void loop(Map map) {
		TitleScreen.displayMenu();
		boolean battleDone = false;
		do {
			Utilities.clearConsole();
			map.runTurn();
			if(map.isEnemyNear()){
				Utilities.clearConsole();
				BattleWorldTest.battleLoop();
				battleDone = true;
			}
		}while (!battleDone);
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

		DuckObject.cleanup();
		TextRoom.cleanup();
		TitleScreen.cleanup();


	}

}
