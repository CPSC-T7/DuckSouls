package world;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import entities.Player;

public class LevelBuilder {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static Level buildArcadeLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return new Level(levelNum, player, currentRoomPoint);
		
	}
	
	public static Level buildStoryLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return LevelIO.loadLevelFromDir("../Levels/Story", levelNum, player, currentRoomPoint);
		
	}
	
	public static Level buildSavedStoryLevel() {
		
		int fileNum = -2;
		int levelNum = -1;
		
		for (File levelFile : new File("../Levels/Saves").listFiles()) {
			
			try {
				Integer.parseInt(levelFile.getName().split("-")[1]);
			} catch (Exception e) {
				continue;
			}
			
			if (fileNum > levelNum) {
				levelNum = fileNum;
			}
			
		}
		
		if (levelNum < 1) {
			System.out.println("Could not read saved state. LevelNum Err.");
			System.exit(1);
		}
		
		String saveDir = "../Levels/Saves/Level-" + levelNum;
		Player player = null;
		Point currentRoomPoint = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveDir))) {
			
			player = (Player) ois.readObject();
			currentRoomPoint = (Point) ois.readObject();
			
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			// TODO Auto-generated catch block
			cnfe.printStackTrace();
		}
		
		if (player == null || currentRoomPoint == null) {
			System.out.println("Could not read saved state. Player / CRP Err.");
			System.exit(1);
		}
		
		return LevelIO.loadLevelFromDir("../Levels/Saves", levelNum, player, currentRoomPoint);
		
	}
	
}
