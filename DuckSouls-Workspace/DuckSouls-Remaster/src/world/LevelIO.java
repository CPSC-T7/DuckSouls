package world;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	
	public static void saveLevelToDir(Level level, String pathDir) {
		
		Room[][] roomArray = level.getRoomArray();
		
		for (int x = 0; x < roomArray.length; x++) {
			for (int y = 0; y < roomArray[0].length; y++) {
				RoomIO.saveRoomToTextFile(pathDir + "/Level-" + level.getLevelNum() + "/Room-" + x + "-" + y + ".txt",
						roomArray[x][y]);
			}
		}
		
	}
	
	public static void saveStoryLevel(Level level) {
		
		saveLevelToDir(level, "../Levels/Saves");
		
		String storyDataFileName = "../Level/Saves/Level-" + level.getLevelNum() + "/storyDat.bin";
		
		try (ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(storyDataFileName))) {
			
			oos.writeObject(level.currentRoom.getPlayer());
			oos.writeObject(level.getCurrentRoomPoint());
			
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		
	}
	
}
