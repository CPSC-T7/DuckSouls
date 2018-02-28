package map;

import java.awt.Point;
import java.util.Scanner;

import battle.BattleWorldTest;
import entities.*;
import items.Item;
import tiles.Stairs;
import utils.Utilities;

public class TextLevel {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Scanner	_scanner			= new Scanner(System.in);
	
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
		this.roomSize = DEFAULT_ROOM_SIZE;
		
		this.genRoomArray();
		
		this.currentRoomPoint = new Point(0, 0);
		this.roomAt(this.currentRoomPoint).placeEntity(new Point(this.roomSize / 2 + 1, this.roomSize / 2 + 1),
				new Player());
		
		this.genMinimapArray();
		
	}
	
	private void drawMinimap() {
		
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
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	private void genMinimapArray() {
		
		this.minimapArray = new String[this.levelWidth][this.levelHeight];
		
		// For each position...
		for (int x = 0; x < this.levelWidth; x++) {
			for (int y = 0; y < this.levelHeight; y++) {
				
				if (x == this.currentRoomPoint.x && y == this.currentRoomPoint.y) {
					
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
				
				this.roomArray[x][y] = new TextRoom(this.roomSize);
				
			}
		}
		
		this.placeAllConnectingDoors();
		
	}
	
	private void placeAllConnectingDoors() {
		
		Point[] doors;
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				doors = new Point[4];
				
				try {
					if (this.roomArray[x][y - 1] != null) {
						doors[0] = new Point((this.roomSize + 2) / 2, 0);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x][y + 1] != null) {
						doors[1] = new Point((this.roomSize + 2) / 2, (this.roomSize + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x - 1][y] != null) {
						doors[2] = new Point(0, (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x + 1][y] != null) {
						doors[3] = new Point((this.roomSize + 1), (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				this.roomArray[x][y].placeDoors(doors);
				
			}
		}
		
	}
	
	private TextRoom roomAt(Point position) {
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
	 *            Must be one of: 'u', 'd', 'l', or 'r'.
	 */
	public void moveRoom_Direction(char direction) {
		
		Point newPlayerPoint = new Point(this.roomAt(this.currentRoomPoint).playerPoint);
		Player player = (Player) this.roomAt(this.currentRoomPoint).entityAt(this.roomAt(this.currentRoomPoint).playerPoint);
		
		this.roomAt(this.currentRoomPoint).removeEntity(this.roomAt(this.currentRoomPoint).playerPoint);
		this.minimapArray[this.currentRoomPoint.x][this.currentRoomPoint.y] = ".";
		
		switch (direction) {
			
			case 'u':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x, this.currentRoomPoint.y - 1);
				newPlayerPoint.y = this.roomSize + 1;
				break;
			
			case 'd':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x, this.currentRoomPoint.y + 1);
				newPlayerPoint.y = 0;
				break;
			
			case 'l':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x - 1, this.currentRoomPoint.y);
				newPlayerPoint.x = this.roomSize + 1;
				break;
			
			case 'r':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x + 1, this.currentRoomPoint.y);
				newPlayerPoint.x = 0;
				break;
			
		}
		
		this.roomAt(this.currentRoomPoint).placeEntity(newPlayerPoint, player);
		this.minimapArray[this.currentRoomPoint.x][this.currentRoomPoint.y] = "@";
		
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void moveLoop() {
		
		String input;
		TextRoom currentRoom;
		Point playerPoint;
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move. 
		 */
		while (true) {
			
			// Draw the room and ask the user for input
			Utilities.clearConsole();
			this.drawMinimap();
			System.out.println('\n');
			this.roomAt(this.currentRoomPoint).draw_Text();
			System.out.print("\nAction \t: ");
			input = _scanner.nextLine().toUpperCase();
			
			// Update some variables
			currentRoom = this.roomAt(this.currentRoomPoint);
			playerPoint = this.roomAt(this.currentRoomPoint).playerPoint;
			
			// Do the action inputed by the user
			switch (input) {
				
				// Moving...
				
				case "W":
				case "NORTH":
					if (playerPoint.y == 0) {
						this.moveRoom_Direction('u');
					} else {
						currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y - 1));
					}
					break;
				
				case "S":
				case "SOUTH":
					if (playerPoint.y == this.roomSize + 1) {
						this.moveRoom_Direction('d');
					} else {
						currentRoom.moveEntity(playerPoint, new Point(playerPoint.x, playerPoint.y + 1));
					}
					break;
				
				case "D":
				case "EAST":
					if (playerPoint.x == this.roomSize + 1) {
						this.moveRoom_Direction('r');
					} else {
						currentRoom.moveEntity(playerPoint, new Point(playerPoint.x + 1, playerPoint.y));
					}
					break;
				
				case "A":
				case "WEST":
					if (playerPoint.x == 0) {
						this.moveRoom_Direction('l');
					} else {
						currentRoom.moveEntity(playerPoint, new Point(playerPoint.x - 1, playerPoint.y));
					}
					break;
				
				// Room commands...
					
				case "I":
					System.out.println("Player Inventory:\n");
					for(Item item : currentRoom.entityAt(playerPoint).getInventory()) {
						System.out.println(item.getName());
					}
					System.out.println("\nPress Enter To Exit.");
					_scanner.nextLine();
					break;
				
				case "SAVE":
					currentRoom.saveToTextFile();
					break;
				
				// Default
				
				default:
					System.out.println("...What?");
					Utilities.waitMilliseconds(300);
					break;
				
			}
			
			// Exit the level if standing on stairs
			if(currentRoom.tileAt(playerPoint) instanceof Stairs) {
				break;
			}
			
			// Check for enemies
			Point battlePoint = currentRoom.checkForBattlePoint();
			if (battlePoint != null) {
				
				Utilities.clearConsole();
				BattleWorldTest.battleLoop();
				
				currentRoom.removeEntity(battlePoint);
				
			}
			
		}
		
	}
	
}
