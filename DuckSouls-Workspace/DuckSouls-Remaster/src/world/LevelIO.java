package world;

import java.awt.Point;
import java.io.File;

import entities.Player;

public final class LevelIO {
	
	public static Level loadLevelFromDir(String pathToDir, int levelNum, Player player, Point currentRoomPoint) {
		
		int maxX = 0;
		int maxY = 0;
		
		for (File roomFile : new File(pathToDir + "/Level-" + levelNum + "/").listFiles()) {
			
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
		
		for (File roomFile : new File(pathToDir + "/Level-" + levelNum + "/").listFiles()) {
			
			int roomX = Integer.parseInt(roomFile.getName().split("-")[1]);
			int roomY = Integer.parseInt(roomFile.getName().split("-")[2].replace(".txt", ""));
			
			roomArray[roomX][roomY] = RoomIO.loadStoryRoom(levelNum, new Point(roomX, roomY));
			
		}
		
		if (levelNum == 1) {
			player.setPosition(roomArray[currentRoomPoint.x][currentRoomPoint.y].getPlayer().getPosition());
		}
		
		return new Level(roomArray.length, roomArray[0].length, roomArray, levelNum, player, currentRoomPoint);
		
	}
	
	public static void saveLevelToDir(Level level, int levelNum, String pathDir) {
		
		Room[][] roomArray = level.getRoomArray();
		
		for (int x = 0; x < roomArray.length; x++) {
			for (int y = 0; y < roomArray[0].length; y++) {
				RoomIO.saveRoomToTextFile(pathDir + "/Level-" + levelNum + "/Room-" + x + "-" + y + ".txt",
						roomArray[x][y]);
			}
		}
		
	}
	
	public static void saveStoryLevel(Level level, int levelNum) {
		saveLevelToDir(level, levelNum, "../Levels/Saves");
	}
	
}
