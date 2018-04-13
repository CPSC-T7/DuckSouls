package world;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import entities.Player;

/**
 * This class contains methods used in input and output of level objects.
 */
public final class LevelIO {
	
	/**
	 * Loads a level, given the number and parent directory.
	 * 
	 * @param loadFromSave
	 *            If the level being loaded is from a save.
	 * @param pathToDir
	 *            The path to the parent directory.
	 * @param levelNum
	 *            The number of the level.
	 * @param player
	 *            The player of the level.
	 * @param currentRoomPoint
	 *            The point of the current room in the room array.
	 * @return The level loaded from the directory.
	 */
	public static Level loadLevelFromDir(boolean loadFromSave, String pathToDir, int levelNum, Player player,
			Point currentRoomPoint) {
		
		int maxX = 0;
		int maxY = 0;
		
		// Find the maximum x and y of rooms to appropriately size the room array.
		for (File roomFile : new File(pathToDir + "/Level-" + levelNum + "/").listFiles()) {
			
			// Check for the room naming
			if (roomFile.getName().split("-").length != 3) {
				continue;
			}
			
			/*
			 * Calculate the co-ords
			 */
			
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
		
		// Load each room into the room array
		for (File roomFile : new File(pathToDir + "/Level-" + levelNum + "/").listFiles()) {
			
			// Check for the room naming
			if (roomFile.getName().split("-").length != 3) {
				continue;
			}
			
			/*
			 * Read the room
			 */
			
			int roomX = Integer.parseInt(roomFile.getName().split("-")[1]);
			int roomY = Integer.parseInt(roomFile.getName().split("-")[2].replace(".txt", ""));
			
			roomArray[roomX][roomY] = RoomIO.loadStoryRoom(loadFromSave, levelNum, new Point(roomX, roomY));
			
		}
		
		// Set the position of the player on the first level
		if (levelNum == 1) {
			player.setPosition(roomArray[currentRoomPoint.x][currentRoomPoint.y].getPlayer().getPosition());
		}
		
		return new Level(roomArray, levelNum, player, currentRoomPoint);
		
	}
	
	/**
	 * Saves a level to a directory.
	 * 
	 * @param level The level to save.
	 * @param pathDir The directory to save the level in.
	 */
	public static void saveLevelToDir(Level level, String pathDir) {
		
		Room[][] roomArray = level.getRoomArray();
		
		// Save each room to a file in the level folder
		for (int x = 0; x < roomArray.length; x++) {
			for (int y = 0; y < roomArray[0].length; y++) {
				
				String roomFileName = pathDir + "/Level-" + level.getLevelNum() + "/Room-" + x + "-" + y + ".txt";
				RoomIO.saveRoomToTextFile(roomFileName, roomArray[x][y]);
				
			}
		}
		
	}
	
	/**
	 * Saves a story level in the appropriate location.
	 * @param level
	 */
	public static void saveStoryLevel(Level level) {
		
		// Clear the save dir
		deletePreviousSaves();
		
		// Save the level
		saveLevelToDir(level, "../Levels/Saves");
		
		/*
		 * Save the player and current room point.
		 */
		
		String storyDataFileName = "../Levels/Saves/Level-" + level.getLevelNum() + "/storyDat.bin";
		
		try {
			
			// Create the bin file and path to it
			File f = new File(storyDataFileName);
			f.getParentFile().mkdirs();
			f.createNewFile();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storyDataFileName, false))) {
			
			// Write the objects
			oos.writeObject(level.currentRoom.getPlayer());
			oos.writeObject(level.getCurrentRoomPoint());
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	/**
	 * Deletes all levels in the save location.
	 */
	public static void deletePreviousSaves() {
		
		File saveDir = new File("../Levels/Saves");
		
		for (File levelDir : saveDir.listFiles()) {
			
			// Delete the files in the lever dir
			for (File file : levelDir.listFiles()) {
				file.delete();
			}
			
			// Delete the dir
			levelDir.delete();
			
		}
		
	}
	
}
