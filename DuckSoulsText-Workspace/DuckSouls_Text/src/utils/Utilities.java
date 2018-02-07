package utils;

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
 * @author Colin Au Yeung
 * @author add name if modified
 */
public class Utilities {
	
	/**
	 * Clear the console screen of text.
	 */
	public static void clearConsole() {
		
		// Try to clear the console via unique OS-Specific methods
		try {
			
			// Get the operating system name
			final String os = System.getProperty("os.name");
			
			// If using Windows: clear using cmd command
			if (os.contains("Windows")) {
				
				// Run the cmd command and wait for it to finish
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				
			} else { // If other: use terminal clearing command (bash)
				
				// Print this interesting series of character and flush the console
				System.out.println("\033[H\033[2J");
				System.out.flush();
				
			}
			
			// TODO: Add MacOS clearing
			
		} catch (Exception e) { // If clearing doesn't work: tell the user and stop the program
			// If console doesn't clear print this error
			System.out.println("Console failed to clear.");
			waitMilliseconds(3000);
			System.exit(1);
		}
		
	}// End of clearConsole
	
	/**
	 * Multiply a string by a specified amount.<br>
	 * 
	 * i.e: "f"*3 = "fff"
	 * 
	 * @param string
	 *            The string to multiply.
	 * @param multiple
	 *            The number of times to multiply the string.
	 * @return newString The multiplied string.
	 */
	public static String multiplyString(String string, int multiple) {
		
		// Create a string builder
		StringBuilder stringBuilder = new StringBuilder();
		
		// Append the inputed string to the string builder the desired number of times
		for (int i = 0; i < multiple; i++) {
			stringBuilder.append(string);
		}
		
		// Process and return the newly multiplied string
		String newString = stringBuilder.toString();
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
		
		// The time at the beginning of the method
		long startTime = System.currentTimeMillis();
		
		// Keep checking to see if the difference in time has passed the required length
		while (true) {
			
			long currentTime = System.currentTimeMillis();
			
			// If the time has passed, break out of the loop
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
		
		// Locate the sprite file in the TextSprites folder above bin
		String fileName = "../TextSprites/" + sprite + ".txt";
		File spriteFile = new File(fileName);
		
		// Pad the image
		System.out.print(yPadding);
		
		// Try to read the file line-by-line and print out each line
		try (Scanner readFile = new Scanner(spriteFile);) {
			
			// Read all the non-null lines in the file and print them
			while (readFile.hasNext()) {
				String line = readFile.nextLine();
				System.out.println(xPadding + line);
			}
			
		} catch (FileNotFoundException e) { // If the file isn't found
			
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
		
		// Try to open and read the file line-by-line with auto-closable BufferedReader
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			
			// Have an array list of lines waiting to be filled with the file's contents
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			
			// Read all non-null lines from the file into the array list
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			
			// Return all of the lines in an array
			return lines.toArray(new String[lines.size()]);
			
		} catch (FileNotFoundException e) { // If the file isn't found
			
			System.out.println("File [" + fileName + "] not found.");
			e.printStackTrace();
			
		} catch (IOException e) { // If something goes wrong whilst reading the file
			
			System.out.println("Cannot read file [" + fileName + "], IO Exception.");
			e.printStackTrace();
			
		}
		
		// If nothing was read, return an empty string array
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
		
		// Create a path object to the file
		Path file = Paths.get(fileName);
		
		// Try to write to the file in UTF-8, creating a file if it doesn't exist.
		try {
			
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
			
		} catch (IOException e) { // If something goes wrong whilst writing the file
			
			System.out.println("Cannot write to file [" + fileName + "], IO Exception.");
			e.printStackTrace();
			
		}
		
	}// End of writeFile
	
}// End of Utilities
