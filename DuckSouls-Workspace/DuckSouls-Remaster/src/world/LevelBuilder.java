package world;

import java.awt.Point;
import java.io.File;

import entities.Player;

public class LevelBuilder {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String WORLD_FOLDER_PATH = "../Levels/";
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static Level buildArcadeLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return new Level(levelNum, player, currentRoomPoint);
		
	}
	
	public static Level buildStoryLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		int maxX = 0;
		int maxY = 0;
		
		for (File roomFile : new File(WORLD_FOLDER_PATH + "Story/Level-" + levelNum + "/").listFiles()) {
			
			int roomX = Integer.parseInt(roomFile.getName().split("-")[1]);
			int roomY = Integer.parseInt(roomFile.getName().split("-")[2].replace(".txt", ""));
			
			if (roomX > maxX) {
				maxX = roomX;
			}
			if (roomY > maxY) {
				maxY = roomY;
			}
			
		}
		
		Room[][] roomArray = new Room[maxX + 1][maxY + 1];
		
		for (File roomFile : new File(WORLD_FOLDER_PATH + "Story/Level-" + levelNum + "/").listFiles()) {
			
			int roomX = Integer.parseInt(roomFile.getName().split("-")[1]);
			int roomY = Integer.parseInt(roomFile.getName().split("-")[2].replace(".txt", ""));
			
			roomArray[roomX][roomY] = RoomIO.loadStoryRoom(levelNum, new Point(roomX, roomY));
			
		}
		
		return new Level(roomArray.length, roomArray[0].length, roomArray, levelNum, player, currentRoomPoint);
		
	}
	
}
