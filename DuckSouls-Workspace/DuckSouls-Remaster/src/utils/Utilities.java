package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final String parentDir = new File(new File("").getAbsolutePath()).getParent();
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
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
		
	}
	
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
		
	}
	
	/**
	 * Reads lines from a text file into a String array.
	 * 
	 * @param fileName
	 *            The file to read from.
	 * @return A String array of all the lines in the file.
	 */
	public static String[] readTextFile(String fileName) {
		
		// Locate the file to read
		File fileToRead = new File(fileName);
		
		// Have an array list of lines waiting to be filled with the file's contents
		ArrayList<String> lines = new ArrayList<String>();
		
		// Try to open and read the file line-by-line with a scanner
		try (Scanner _scanner = new Scanner(fileToRead);) {
			
			// Read all non-null lines from the file into the array list
			while (_scanner.hasNext()) {
				lines.add(_scanner.nextLine());
			}
			
			// Return all of the lines in an array
			return lines.toArray(new String[lines.size()]);
			
		} catch (FileNotFoundException e) { // If the file isn't found
			
			System.out.println("File [" + fileName + "] not found.");
			e.printStackTrace();
			
		}
		
		// If nothing was read, return an empty string array
		return new String[0];
		
	}
	
	/**
	 * Writes lines from a String array to a specified file.
	 * 
	 * @param fileName
	 *            The file to write to.
	 * @param lines
	 *            The lines to write to the file.
	 */
	@SuppressWarnings("resource")
	public static void writeTextFile(String fileName, ArrayList<String> lines) {
		
		ensureDirsForFileExist(fileName);
		
		// Try to write the file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			
			// Write each line
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			
		} catch (IOException ioe) {
			
			System.out.println("Cannot write to file [" + fileName + "], IO Exception.");
			ioe.printStackTrace();
			
			// For debugging
			new java.util.Scanner(System.in).nextLine();
			
		}
		
	}
	
	/**
	 * Given a path to a file, this method ensures that the directories leading to
	 * the file exist, and creates them if they do not exist.
	 * 
	 * @param fileName
	 *            The path to the file you intend to write to.
	 */
	public static void ensureDirsForFileExist(String fileName) {
		
		// Containers
		String filePath = "";
		String[] pathBits = fileName.split("/");
		
		// Build the path up to and including the last directory
		for (int i = 0; i < pathBits.length - 1; i++) {
			filePath = filePath + pathBits[i] + "/";
		}
		
		// Make the directories if they don't exist
		new File(filePath).mkdirs();
		
	}
	
}
