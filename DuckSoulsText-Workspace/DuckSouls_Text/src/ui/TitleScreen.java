package ui;

import java.util.Scanner;
import utils.Utilities;

/**
 * Stores and runs the game's title screen, which disappears after you press
 * enter.
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class TitleScreen {
	
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
			+ "\t\t\t\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n"
			+ "\t\t\t\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n"
			+ "\t\t\t\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n"
			+ "\t\t\t\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n"
			+ "\t\t\t\t\t ░               ░                                                              \r\n";
	
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
		
	}
	
}
