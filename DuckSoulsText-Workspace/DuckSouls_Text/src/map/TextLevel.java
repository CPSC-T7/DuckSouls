package map;

import java.awt.Point;
import java.util.Scanner;
import java.util.Random;

import entities.*;
import tiles.Stairs;
import utils.Orientation;

public class TextLevel {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Scanner	_scanner			= new Scanner(System.in);
	private static Random	_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_LEVEL_SIZE	= 3;
	private final int		DEFAULT_ROOM_SIZE	= 5;
	
	private TextRoom[][]	roomArray;
	private String[][]		minimapArray;
	private Point			currentRoomPoint;
	private int				levelWidth, levelHeight;
	private int				roomSize;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public TextLevel() {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.setRoomSize(DEFAULT_ROOM_SIZE);
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(new Point(0, 0));
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(new Point(this.getRoomSize() / 2 + 1, this.getRoomSize() / 2 + 1),
				new Player());
		
		this.genMinimapArray();
		
	}
	
	public TextLevel(Player player, Point playerPosition, Point roomPoint) {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.setRoomSize(DEFAULT_ROOM_SIZE);
		
		this.genRoomArray();
		
		this.setCurrentRoomPoint(roomPoint);
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(playerPosition, player);
		
		this.genMinimapArray();
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public void drawMinimap() {
		
		int yMax = 2 * this.levelHeight + 1, xMax = 2 * this.levelWidth + 1;
		
		for (int y = 0; y < yMax; y++) {
			for (int x = 0; x < xMax; x++) {
				
				if (y == 0) {
					
					if (x == 0) {
						System.out.print("┌");
					} else if (x == xMax - 1) {
						System.out.print("┐");
					} else if (x % 2 == 0) {
						System.out.print("┬");
					} else {
						System.out.print("─");
					}
					
				} else if (y == yMax - 1) {
					
					if (x == 0) {
						System.out.print("└");
					} else if (x == xMax - 1) {
						System.out.print("┘");
					} else if (x % 2 == 0) {
						System.out.print("┴");
					} else {
						System.out.print("─");
					}
					
				} else if (y % 2 == 0) {
					
					if (x == 0) {
						System.out.print("├");
					} else if (x == xMax - 1) {
						System.out.print("┤");
					} else if (x % 2 == 0) {
						System.out.print("┼");
					} else {
						System.out.print(" ");
					}
					
				} else {
					
					if (x == 0) {
						System.out.print("│");
					} else if (x == xMax - 1) {
						System.out.print("│");
					} else if (x % 2 == 0) {
						System.out.print(" ");
					} else {
						System.out.print(this.minimapArray[(x - 1) / 2][(y - 1) / 2]);
					}
					
				}
				
			}
			
			System.out.println();
		}
		
	}
	
	private void genMinimapArray() {
		
		this.minimapArray = new String[this.levelWidth][this.levelHeight];
		
		// For each position...
		for (int x = 0; x < this.levelWidth; x++) {
			for (int y = 0; y < this.levelHeight; y++) {
				
				if (x == this.getCurrentRoomPoint().x && y == this.getCurrentRoomPoint().y) {
					
					this.minimapArray[x][y] = "@";
					
				} else {
					
					this.minimapArray[x][y] = " ";
					
				}
				
			}
			
		}
		
	}
	
	private void genRoomArray() {
		
		this.roomArray = new TextRoom[this.levelWidth][this.levelHeight];
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				this.roomArray[x][y] = new TextRoom(this.getRoomSize());
				
			}
		}
		
		this.placeAllConnectingDoors();

		int stairsRoomX = _random.nextInt(levelWidth);
		int stairsRoomY = _random.nextInt(levelHeight);
		int stairsTileX = _random.nextInt(this.getRoomSize()) + 1;
		int stairsTileY = _random.nextInt(this.getRoomSize()) + 1;
		Point stairsPoint = new Point(stairsTileX, stairsTileY);
		TextRoom stairsRoom = this.roomArray[stairsRoomX][stairsRoomY];
		
		stairsRoom.setTile(stairsPoint, new Stairs());
		stairsRoom.removeEntity(stairsPoint);
		stairsRoom.removeItem(stairsPoint);
		
	}
	
	private void placeAllConnectingDoors() {
		
		Point[] doors;
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				doors = new Point[4];
				
				try {
					if (this.roomArray[x][y - 1] != null) {
						doors[0] = new Point((this.getRoomSize() + 2) / 2, 0);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x][y + 1] != null) {
						doors[1] = new Point((this.getRoomSize() + 2) / 2, (this.getRoomSize() + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x - 1][y] != null) {
						doors[2] = new Point(0, (this.getRoomSize() + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x + 1][y] != null) {
						doors[3] = new Point((this.getRoomSize() + 1), (this.getRoomSize() + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				this.roomArray[x][y].placeDoors(doors);
				
			}
		}
		
	}
	
	public TextRoom roomAt(Point position) {
		return this.roomArray[position.x][position.y];
	}
	
	/**
	 * Cleans up the static resources
	 */
	public static void cleanup() {
		
		_scanner.close();
		
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void moveRoom_Direction(Orientation direction) {
		
		Point newPlayerPoint = new Point(this.roomAt(this.getCurrentRoomPoint()).playerPoint);
		Player player = (Player) this.roomAt(this.getCurrentRoomPoint())
				.entityAt(this.roomAt(this.getCurrentRoomPoint()).playerPoint);
		
		this.roomAt(this.getCurrentRoomPoint()).removeEntity(this.roomAt(this.getCurrentRoomPoint()).playerPoint);
		this.minimapArray[this.getCurrentRoomPoint().x][this.getCurrentRoomPoint().y] = ".";
		
		switch (direction) {
			
			case NORTH:
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y - 1));
				newPlayerPoint.y = this.getRoomSize() + 1;
				break;
			
			case SOUTH:
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y + 1));
				newPlayerPoint.y = 0;
				break;
			
			case WEST:
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x - 1, this.getCurrentRoomPoint().y));
				newPlayerPoint.x = this.getRoomSize() + 1;
				break;
			
			case EAST:
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x + 1, this.getCurrentRoomPoint().y));
				newPlayerPoint.x = 0;
				break;
			
		}
		
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(newPlayerPoint, player);
		this.minimapArray[this.getCurrentRoomPoint().x][this.getCurrentRoomPoint().y] = "@";
		
	}

	public Point getCurrentRoomPoint() {
		return currentRoomPoint;
	}

	public void setCurrentRoomPoint(Point currentRoomPoint) {
		this.currentRoomPoint = currentRoomPoint;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	
}
