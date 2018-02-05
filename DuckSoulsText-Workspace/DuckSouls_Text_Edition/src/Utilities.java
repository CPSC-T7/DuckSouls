
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Utilities that will be used throughout the program. Basic tasks that are
 * repeated more than once.
 * 
 * @author Wylee McAndrews
 * @author Matthew Allwright
 * @author add name if modified
 */
public class Utilities {
	
	/**
	 * Clear the console screen of text.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void clearConsole() {
		
		try {
			// Get the operating system name
			final String os = System.getProperty("os.name");
			
			// If windows: clear using cmd command
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			// If other: use terminal command
			else {
				// Runtime.getRuntime().exec("clear");
				System.out.println("\033[H\033[2J");
				System.out.flush();
			}
		}
		
		// If clearing doesn't work: tell the user and stop the program
		catch (Exception e) {
			// If console doesn't clear print this error
			System.out.println("Console failed to clear.");
			waitMilliseconds(3000);
			System.exit(0);
		}
		
	}// End of clearConsole
	
	/**
	 * Multiply a string by a specified amount.
	 * 
	 * i.e: "f"*3 = "fff"
	 * 
	 * @param string
	 *            The string to multiply.
	 * @param multiple
	 *            The number of times to multiply the string.
	 * @return newString The new string.
	 */
	public static String multiplyString(String string, int multiple) {
		
		StringBuilder stringArray = new StringBuilder();
		for (int i = 0; i < multiple; i++) {
			stringArray.append(string);
		}
		String newString = stringArray.toString();
		return (newString);
		
	}// End of multiplyString
	
	/**
	 * Timer method used as an alternative to Thread.sleep() Gives a more accurately
	 * timed delay across systems.
	 * 
	 * @param stopTime
	 *            Stop after this long (Milliseconds)
	 */
	public static void waitMilliseconds(long stopTime) {
		long startTime = System.currentTimeMillis();
		while (true) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime >= stopTime)
				break;
		}
	}// End of waitMilliSeconds
	
	/**
	 * Reads and prints a sprite from it's text file, with added X and Y padding.
	 * 
	 * @param sprite
	 *            The file path and name of the sprite to print.
	 * @param xPadding
	 *            The X padding to add to the left side of each line.
	 * @param yPadding
	 *            The Y padding to add to the top line of the sprite.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 */
	public static void printSprite(String sprite, String xPadding, String yPadding) {
		
		String fileName = "TextSprites/" + sprite + ".txt";
		File spriteFile = new File(fileName);
		System.out.print(yPadding);
		
		try(Scanner readFile = new Scanner(spriteFile);) {
			
			while (readFile.hasNext()) {
				String line = readFile.nextLine();
				System.out.println(xPadding + line);
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File [" + fileName + "] not found.");
			
		}
		
	}// End of printSprite
	
	/**
	 * Reads lines from a text file into a String array.
	 * 
	 * @param fileName
	 *            The file to read from.
	 * @return A String array of all the lines in the file.
	 */
	public static String[] readLines(String fileName) {
		
		try {
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			
			while ((line = bufferedReader.readLine()) != null) {
				
				lines.add(line);
				
			}
			
			bufferedReader.close();
			return lines.toArray(new String[lines.size()]);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File [" + fileName + "] not found.");
			e.printStackTrace();
			
		} catch (IOException e) {
			
			System.out.println("Cannot read file [" + fileName + "], IO Exception.");
			e.printStackTrace();
			
		}
		
		return new String[0];
		
	}// End of readLines
	
	/**
	 * Writes lines from a String array to a specified file.
	 * 
	 * @param fileName
	 *            The file to write to.
	 * @param lines
	 *            The lines to write to the file.
	 */
	public static void writeFile(String fileName, ArrayList<String> lines) {
		
		Path file = Paths.get(fileName);
		
		try {
			
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
			
		} catch (IOException e) {
			
			System.out.println("Cannot write to file [" + fileName + "], IO Exception.");
			e.printStackTrace();
			
		}
		
	}// End of writeFile
	
}// End of Utilities
