package world;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import entities.Player;

public final class LevelIO {
	
	public static Level loadLevelFromDir(boolean loadFromSave, String pathToDir, int levelNum, Player player,
			Point currentRoomPoint) {
		
		int maxX = 0;
		int maxY = 0;
		
		for (File roomFile : new File(pathToDir + "/Level-" + levelNum + "/").listFiles()) {
			
			if (roomFile.getName().split("-").length != 3) {
				continue;
			}
			
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
			
			if (roomFile.getName().split("-").length != 3) {
				continue;
			}
			
			int roomX = Integer.parseInt(roomFile.getName().split("-")[1]);
			int roomY = Integer.parseInt(roomFile.getName().split("-")[2].replace(".txt", ""));
			
			roomArray[roomX][roomY] = RoomIO.loadStoryRoom(loadFromSave, levelNum, new Point(roomX, roomY));
			
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
				
				String roomFileName = pathDir + "/Level-" + level.getLevelNum() + "/Room-" + x + "-" + y + ".txt";
				RoomIO.saveRoomToTextFile(roomFileName, roomArray[x][y]);
				
			}
		}
		
	}
	
	public static void saveStoryLevel(Level level) {
		
		deletePreviousSaves();
		
		saveLevelToDir(level, "../Levels/Saves");
		
		String storyDataFileName = "../Levels/Saves/Level-" + level.getLevelNum() + "/storyDat.bin";
		try {
			File f = new File(storyDataFileName);
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storyDataFileName, false))) {
			
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
	
	public static void deletePreviousSaves() {
		
		File saveDir = new File("../Levels/Saves");
		
		for (File levelDir : saveDir.listFiles()) {
			for (File file : levelDir.listFiles()) {
				file.delete();
			}
			levelDir.delete();
		}
		
	}
	
}
