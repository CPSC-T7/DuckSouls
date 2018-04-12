package world;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import entities.Player;

/**
 * This class contains methods for building levels.
 */
public class LevelBuilder {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Builds a new arcade level.
	 * 
	 * @param levelNum
	 *            The number of the level.
	 * @param player
	 *            The player of the level.
	 * @param currentRoomPoint
	 *            The point of the current room in the room array.
	 * @return A new arcade level.
	 */
	public static Level buildArcadeLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return new Level(levelNum, player, currentRoomPoint);
		
	}
	
	/**
	 * Builds the next story level.
	 * 
	 * @param levelNum
	 *            The number of the level.
	 * @param player
	 *            The player of the level.
	 * @param currentRoomPoint
	 *            The point of the current room in the room array.
	 * @return The next story level.
	 */
	public static Level buildStoryLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return LevelIO.loadLevelFromDir(false, "../Levels/Story", levelNum, player, currentRoomPoint);
		
	}
	
	/**
	 * Builds the saved story level.
	 * 
	 * @return The saved story level.
	 */
	public static Level buildSavedStoryLevel() {
		
		int fileNum = -2;
		int levelNum = -1;
		
		// Grab the highest level number of the levels stored in the saves folder
		for (File levelFile : new File("../Levels/Saves").listFiles()) {
			
			try {
				fileNum = Integer.parseInt(levelFile.getName().split("-")[1]);
			} catch (Exception e) {
				continue;
			}
			
			if (fileNum > levelNum) {
				levelNum = fileNum;
			}
			
		}
		
		// Make sure a level number was found
		if (levelNum < 1) {
			System.out.println("Could not read saved state. LevelNum Err.");
			System.exit(1);
		}
		
		String saveDir = "../Levels/Saves/Level-" + levelNum;
		Player player = null;
		Point currentRoomPoint = null;
		
		// Read the player and current room point
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveDir + "/storyDat.bin"))) {
			
			player = (Player) ois.readObject();
			currentRoomPoint = (Point) ois.readObject();
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		// Make sure a player and point were read
		if (player == null || currentRoomPoint == null) {
			System.out.println("Could not read saved state. Player / CRP Err.");
			System.exit(1);
		}
		
		return LevelIO.loadLevelFromDir(true, "../Levels/Saves", levelNum, player, currentRoomPoint);
		
	}
	
}
