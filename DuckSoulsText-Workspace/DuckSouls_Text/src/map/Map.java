package map;

import java.util.*;

import utils.Utilities;
import character.*;
import character.Character;
import tiles.*;

public class Map {
	private ArrayList<ArrayList<Tile>> currentMap = new ArrayList<ArrayList<Tile>>(0);
	private String currentMapID;
	private ArrayList<Character> characters = new ArrayList<Character>(0);
	private PlayerCharacter player = new PlayerCharacter();
	private HashMap<String, Mapfile> maps = new HashMap<String, Mapfile>();
	
	public Map() {
		this.characters.add(this.player);
	}
	
	public void loadAllMapFiles(int firstmap, int lastmap) {
		this.maps.clear();
		this.currentMapID = String.format("%03d", firstmap);
		for(int i=firstmap; i<=lastmap; i++) {
			String mapNum = String.format("%03d", i);
			String mapName = "../TextRooms/" + "map" + mapNum + ".txt";
			this.maps.put(mapNum, new Mapfile(mapName));
		}
	}

	public void loadMap() {
		ArrayList<ArrayList<String>> mapData = this.maps.get(currentMapID).getMap();
		for (int y = 0; y < mapData.size(); y++) {
			this.currentMap.add(new ArrayList<Tile>(0));
			for (int x = 0; x < mapData.get(y).size(); x++) {
				char type = mapData.get(y).get(x).charAt(0);
				switch (type) {
				case '@':
					this.currentMap.get(y).add(new Floor(x, y));
					this.player.setPos(x, y, currentMap);
					break;
				case 'E':
					this.characters.add(new Enemy(x, y));
				case 'F':
					this.currentMap.get(y).add(new Floor(x, y));
					break;
				case ' ':
					this.currentMap.get(y).add(new Tile(x, y));
					break;
				case 'D':
					this.currentMap.get(y).add(loadDoor(mapData.get(y).get(x).substring(1), x, y));
					break;
				case 'W':
					this.currentMap.get(y).add(loadWall(mapData.get(y).get(x).substring(1), x, y));
					break;
				}
			}
		}
	}
	
	public void loadMap(String mapID) {
		if(this.maps.containsKey(mapID)) {
			ArrayList<ArrayList<String>> mapData = this.maps.get(mapID).getMap();
			for (int y = 0; y < mapData.size(); y++) {
				this.currentMap.add(new ArrayList<Tile>(0));
				for (int x = 0; x < mapData.get(y).size(); x++) {
					char type = mapData.get(y).get(x).charAt(0);
					switch (type) {
					case '@':
						this.currentMap.get(y).add(new Floor(x, y));
						break;
					case 'E':
						this.characters.add(new Enemy(x, y));
					case 'F':
						this.currentMap.get(y).add(new Floor(x, y));
						break;
					case ' ':
						this.currentMap.get(y).add(new Tile(x, y));
						break;
					case 'D':
						this.currentMap.get(y).add(loadDoor(mapData.get(y).get(x).substring(1), x, y));
						break;
					case 'W':
						this.currentMap.get(y).add(loadWall(mapData.get(y).get(x).substring(1), x, y));
						break;
					}
				}
			}
			for(ArrayList<Tile> row: this.currentMap) {
				for(Tile spot: row) {
					if(spot.getType() == "Door") {
						if(spot.getMapID().equals(this.currentMapID)) {
							int x = spot.getX();
							int y = spot.getY();
							this.player.setPos(x, y, this.currentMap);
						}
					}
				}
			}
		}
		this.currentMapID = mapID;
	}

	public void clearMap() {
		this.currentMap.clear();
	}
	
	public void clearCharacters() {
		this.characters.clear();
		this.characters.add(player);
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
		boolean printed = false;
		for (int y = 0; y < this.currentMap.size(); y++) {
			for (int x = 0; x < this.currentMap.get(y).size(); x++) {
				printed = false;
				for(Character character: this.characters) {
					if(!printed) {
						if(character.getX() == x) {
							if(character.getY() == y) {
								if(character.getX() == 0 || character.getX() == this.currentMap.get(y).size() -1) {
									System.out.print(character.print());
								}
	
								else{
									System.out.print(" " + character.print() + " ");
								}
								printed = true;
							}
						}
					}
				}
				if(!printed)
					System.out.print(this.currentMap.get(y).get(x).getRep());
			}
			System.out.println("");
		}
	}
	
	public ArrayList<ArrayList<Tile>> getMap(){
		return this.currentMap;
	}
	
	public boolean movableLocation(int x, int y) {
		return this.currentMap.get(y).get(x).CanMove();
	}
	
	public void mainloop() {
		int count = 0;
		this.loadAllMapFiles(0, 2);
		this.loadMap();
		String mapID = new String();
		do {
			boolean doorCheck = false;
			this.print();
			for(Character character: this.characters) {
				character.move(this.currentMap);
			}
			for(int y =0; y<this.currentMap.size(); y++) {
				for(int x=0; x<this.currentMap.get(y).size(); x++) {
					if(x == player.getX() && y==player.getY()) {
						if(currentMap.get(y).get(x).getType().equals("Door")) {
							if(!doorCheck) {
								mapID = currentMap.get(y).get(x).getMapID();
								doorCheck = true;
							}
						}
					}
					if(doorCheck) {
						this.clearMap();
						this.clearCharacters();
						this.loadMap(mapID);
					}
				}
				
			}

			Utilities.clearConsole();
			count += 1;
			
		
		} while(count < 20);
	}
	
	public void initalization(int firstmap, int lastmap) {
		this.loadAllMapFiles(firstmap, lastmap);
		this.loadMap();
	}
	
	public void turnLoop() {
		String mapID = new String();
		boolean doorCheck = false;
		this.print();
		for(Character character: this.characters) {
			character.move(this.currentMap);
		}
		for(int y =0; y<this.currentMap.size(); y++) {
			for(int x=0; x<this.currentMap.get(y).size(); x++) {
				if(x == player.getX() && y==player.getY()) {
					if(currentMap.get(y).get(x).getType().equals("Door")) {
						if(!doorCheck) {
							mapID = currentMap.get(y).get(x).getMapID();
							doorCheck = true;
						}
					}
				}
				if(doorCheck) {
					this.clearMap();
					this.clearCharacters();
					this.loadMap(mapID);
				}
			}
			
		}

	}
	
	public boolean isEnemyNear() {
		boolean isnear = false;
		for(Character character: this.characters) {
			if(!character.isPlayer()) {
				if(character.isNextTo(this.player.getX(), this.player.getY())) isnear = true;
			}
		}
		return isnear;
	}
}
