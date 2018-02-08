package ui;

import java.util.Scanner;
<<<<<<< HEAD
import battle.DuckObject;
=======
import utils.Utilities;
>>>>>>> 13706e6bf131ec656fec7b8d27ae80fdbb64e209

/**
 * Stores and runs the game's title screen, which disappears after you press
 * enter.
 * 
 * @author Wylee McAndrews
 */
public class TitleScreen {
<<<<<<< HEAD

	private static String duckSouls = "\n\n"
			+ "\t\t\t\t\t █████▄  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████ \r\n"
			+ "\t\t\t\t\t ██   ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n"
			+ "\t\t\t\t\t ██   ██░██  ▒██░▒██    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n"
			+ "\t\t\t\t\t ██  ▄██░██  ░██░▒██▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n"
			+ "\t\t\t\t\t░██████░░▓████▓ ▒  ███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒\r\n"
=======
	
	// Scanner just to read if the user presses enter
	private static Scanner	scanner		= new Scanner(System.in);
	
	// The awesome tile ASCII art from
	// http://patorjk.com/software/taag/#p=display&f=Bloody&t=Duck%20Souls
	private static String	duckSoulsTitle	= "\n\n"
			+ "\t\t\t\t\t █████▄  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████  \r\n"
			+ "\t\t\t\t\t ██   ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n"
			+ "\t\t\t\t\t ██   ██░██  ▒██░▒██    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n"
			+ "\t\t\t\t\t ██  ▄██░██  ░██░▒██▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n"
			+ "\t\t\t\t\t░██████░░▓████▓ ▒  ███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒ \r\n"
>>>>>>> 13706e6bf131ec656fec7b8d27ae80fdbb64e209
			+ "\t\t\t\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n"
			+ "\t\t\t\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n"
			+ "\t\t\t\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n"
			+ "\t\t\t\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n"
			+ "\t\t\t\t\t ░               ░                                                              \r\n";
<<<<<<< HEAD

	private static DuckObject duck = new DuckObject();
	private static Scanner scanner = new Scanner(System.in);

	public static void menu() {
		System.out.print(duckSouls);
		System.out.println("\t\t\t\t\t\t\t\t     Press Enter To Start");
		duck.getSprite("stand" + "\n");
		scanner.nextLine();
		scanner.close();
		// Add a
=======
	
	/**
	 * Display's the game's menu, clearing it when the user presses enter.
	 */
	public static void displayMenu() {
		
		// Clear the console
		Utilities.clearConsole();
		
		// Print the tile and instructions
		System.out.print(duckSoulsTitle);
		System.out.println("\t\t\t\t\t\t\t\t     Press Enter To Start");
		
		// Wait for the user to press enter to continue
		scanner.nextLine();
		scanner.close();
		
>>>>>>> 13706e6bf131ec656fec7b8d27ae80fdbb64e209
	}
	
}
