import java.util.*;

public class Map {
	private ArrayList<ArrayList<Tile>> currentMap = new ArrayList<ArrayList<Tile>>(0);

	public void loadMap(ArrayList<ArrayList<String>> mapData) {
		for (int x = 0; x < mapData.size(); x++) {
			currentMap.add(new ArrayList<Tile>(0));
			for (int y = 0; y < mapData.get(x).size(); y++) {
				char type = mapData.get(x).get(y).charAt(0);
				switch (type) {
				case 'F':
					currentMap.get(x).add(new Floor(x, y));
					break;
				case 'E':
					currentMap.get(x).add(new Tile(x, y));
					break;
				case 'D':
					currentMap.get(x).add(loadDoor(mapData.get(x).get(y).substring(1), x, y));
					break;
				case 'W':
					currentMap.get(x).add(loadWall(mapData.get(x).get(y).substring(1), x, y));
					break;
				}
			}
		}
	}

	public void clearMap() {
		currentMap.clear();
	}

	public Door loadDoor(String data, int x, int y) {
		String[] arguments = data.split("-");
		char lock = arguments[0].charAt(0);
		boolean locked;
		if (lock == 'L')
			locked = true;
		else
			locked = false;
		char orient = arguments[0].charAt(1);
		boolean vertical;
		if (orient == 'V')
			vertical = true;
		else
			vertical = false;
		String keyID = arguments[1];
		String mapID = arguments[2];
		Door currentDoor = new Door(x, y, locked, mapID, vertical, keyID);
		return currentDoor;
	}

	public Wall loadWall(String orient, int x, int y) {
		Wall currentWall = new Wall(x, y, orient);
		return currentWall;
	}

	public void print() {
		for (int x = 0; x < currentMap.size(); x++) {
			for (int y = 0; y < currentMap.get(x).size(); y++) {
				System.out.print(currentMap.get(x).get(y).getRep());
			}
			System.out.println("");
		}
	}
}
