package world;

public final class LevelIO {
	
	public static void saveLevelToDir(Level level, String pathDir) {
		
		Room[][] roomArray = level.getRoomArray();
		
		for (int x = 0; x < roomArray.length; x++) {
			for (int y = 0; y < roomArray[0].length; y++) {
				RoomIO.saveRoomToTextFile(pathDir + "Room-" + x + "-" + y + ".txt", roomArray[x][y]);
			}
		}
		
	}
	
}
