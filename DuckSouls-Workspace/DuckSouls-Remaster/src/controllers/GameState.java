package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Deprecated
public class GameState {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	@SuppressWarnings("unused")
	private static boolean		isStory;
	@SuppressWarnings("unused")
	private static boolean		isGUI;
	private static Controller	gameController;
	
	/*
	 * 
	 * STATIC METHODS
	 * 
	 */
	
	/**
	 * Loads a GameState object from a binary file.
	 * 
	 * @param binaryFileName
	 *            The name of the binary file in ../Saves/ to read from.
	 * @return The GameState object stored in the binary file.
	 */
	public static GameState loadState(String binaryFileName) {
		
		try (ObjectInputStream obj_IS = new ObjectInputStream(new FileInputStream(new File(binaryFileName)))) {
			
			if (obj_IS.available() > 0) {
				return (GameState) obj_IS.readObject();
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		System.exit(1);
		return null;
		
	}
	
	/**
	 * Saves this GameState object to a binary file.
	 * 
	 * @param binaryFileName
	 *            The name of the binary file in ../Saves/ to save to.
	 */
	public static void saveState(String binaryFileName) {
		
		try (ObjectOutputStream obj_OS = new ObjectOutputStream(new FileOutputStream(new File(binaryFileName)))) {
			
			obj_OS.writeObject(gameController);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void play() {
		
		gameController.mainLoop();
		
	}
	
}
