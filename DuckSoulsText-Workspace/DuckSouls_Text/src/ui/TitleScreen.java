package ui;

import java.util.Scanner;
import battle.DuckObject;

/**
 * 
 * @author Wylee McAndrews
 */
public class TitleScreen {

	private static String duckSouls = "\n\n"
			+ "\t\t\t\t\t █████▄  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████ \r\n"
			+ "\t\t\t\t\t ██   ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n"
			+ "\t\t\t\t\t ██   ██░██  ▒██░▒██    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n"
			+ "\t\t\t\t\t ██  ▄██░██  ░██░▒██▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n"
			+ "\t\t\t\t\t░██████░░▓████▓ ▒  ███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒\r\n"
			+ "\t\t\t\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n"
			+ "\t\t\t\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n"
			+ "\t\t\t\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n"
			+ "\t\t\t\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n"
			+ "\t\t\t\t\t ░               ░                                                              \r\n";

	private static DuckObject duck = new DuckObject();
	private static Scanner scanner = new Scanner(System.in);

	public static void menu() {
		System.out.print(duckSouls);
		System.out.println("\t\t\t\t\t\t\t\t     Press Enter To Start");
		duck.getSprite("stand" + "\n");
		scanner.nextLine();
		scanner.close();
		// Add a
	}

}
