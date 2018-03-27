package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.application.Application;

public class GameState implements Serializable {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	/**
	 * Serial Version UID that was generated.
	 */
	private static final long	serialVersionUID	= -7324970970860461971L;
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private boolean				isStory;
	private boolean				isGUI;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new Game State.
	 * 
	 * @param isStory
	 *            If the game is in Story mode. (Arcade = false)
	 * @param isGUI
	 *            If the game is in GUI mode. (Text = false)
	 */
	public GameState(boolean isStory, boolean isGUI) {
		
		this.isStory = isStory;
		this.isGUI = isGUI;
		
	}
	
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
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Saves this GameState object to a binary file.
	 * 
	 * @param binaryFileName
	 *            The name of the binary file in ../Saves/ to save to.
	 */
	public void saveState(String binaryFileName) {
		
		try (ObjectOutputStream obj_OS = new ObjectOutputStream(new FileOutputStream(new File(binaryFileName)))) {
			
			obj_OS.writeObject(this);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void play() {
		
		if (this.isGUI) {
			
			Application.launch(Controller_GUI.class, new String[] { Boolean.toString(this.isStory) });
			
		} else {
			
			Controller_Text.play(this.isStory);
			
		}
		
	}
	
}
