//IOException for use with CMD in Windows
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class Title_Screen {
	
	private static String duckSouls = 
			"\n\n"+
			"\t\t\t\t\t █████▄  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████ \r\n" + 
			"\t\t\t\t\t ██   ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n" + 
			"\t\t\t\t\t ██   ██░██  ▒██░▒██    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n" + 
			"\t\t\t\t\t ██  ▄██░██  ░██░▒██▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n" + 
			"\t\t\t\t\t░██████░░▓████▓ ▒  ███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒\r\n" + 
			"\t\t\t\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n" + 
			"\t\t\t\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n" + 
			"\t\t\t\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n" + 
			"\t\t\t\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n" + 
			"\t\t\t\t\t ░               ░                                                              \r\n";
	
	private static Duck_Object duck = new Duck_Object();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		Utilities.clearConsole();
		duck.xPosition = 32;
		menu();
	}
	
	public static void menu() throws InterruptedException, IOException 
	{
		System.out.print(duckSouls);
		System.out.println("\t\t\t\t\t\t\t\t     Press Enter To Start");
		duck.getSprite("stand" + "\n");
		String userStart = scanner.nextLine();
		
	}

}
