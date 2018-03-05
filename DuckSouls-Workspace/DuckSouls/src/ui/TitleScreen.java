package ui;

import java.util.Scanner;
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

	//Get user input from console
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Display's the game's menu, clearing it when the user presses enter.
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
	
	public static void cleanup() {
		scanner.close();
	}
	
}
