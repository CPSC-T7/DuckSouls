package map;

import java.awt.Point;
import java.util.Scanner;

//JavaFX
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import battle.BattleWorldTest;
import mattEntities.*;
import utils.Utilities;

public class GUILevel {
	
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
	
	private GUIRoom[][]		roomArray;
	private String[][]		minimapArray;
	private Point			currentRoomPoint;
	private int				levelWidth, levelHeight;
	private int				roomSize;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public GUILevel() {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		
		this.genRoomArray();
		
		this.currentRoomPoint = new Point(0, 0);
		Point startPosition = new Point(this.roomSize / 2 + 1, this.roomSize / 2 + 1);
		this.roomAt(this.currentRoomPoint).placeEntity(startPosition, new Player());
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	private void genRoomArray() {
		
		this.roomArray = new GUIRoom[this.levelWidth][this.levelHeight];
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				this.roomArray[x][y] = new GUIRoom(this.roomSize);
				
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
	
	private GUIRoom roomAt(Point position) {
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
		
		this.roomAt(this.currentRoomPoint).removeEntity(this.roomAt(this.currentRoomPoint).playerPoint);
		//this.minimapArray[this.currentRoomPoint.x][this.currentRoomPoint.y] = ".";
		
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
		
		this.roomAt(this.currentRoomPoint).placeEntity(newPlayerPoint, new Player());
		//this.minimapArray[this.currentRoomPoint.x][this.currentRoomPoint.y] = "@";
		
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void moveLoop(GraphicsContext gc, Scene scene) {
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		
		this.roomAt(this.currentRoomPoint).draw_Room(gc);
		
		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.W) { // NORTH
				
				if (this.roomAt(this.currentRoomPoint).playerPoint.y == 0) {
					this.moveRoom_Direction('u');
				} else {
					
					// Point to move the player to (Up)
					Point newPlayerPoint = new Point(this.roomAt(this.currentRoomPoint).playerPoint.x,
							this.roomAt(this.currentRoomPoint).playerPoint.y - 1);
					
					// Run the method to move the player
					this.roomAt(this.currentRoomPoint).moveEntity(this.roomAt(this.currentRoomPoint).playerPoint,
							newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = this.roomAt(this.currentRoomPoint).playerPoint;
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).DIRECTION = "Up";
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).newImage();
					
					// Re-draw the room
					this.roomAt(this.currentRoomPoint).draw_Room(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.A) { // EAST
				
				if (this.roomAt(this.currentRoomPoint).playerPoint.x == 0) {
					this.moveRoom_Direction('l');
				} else {
					
					// Point to move the player to (Left)
					Point newPlayerPoint = new Point(this.roomAt(this.currentRoomPoint).playerPoint.x - 1,
							this.roomAt(this.currentRoomPoint).playerPoint.y);
					
					// Run the method to move the player
					this.roomAt(this.currentRoomPoint).moveEntity(this.roomAt(this.currentRoomPoint).playerPoint,
							newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = this.roomAt(this.currentRoomPoint).playerPoint;
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).DIRECTION = "Left";
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).newImage();
					
					// Re-draw the room
					this.roomAt(this.currentRoomPoint).draw_Room(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.S) { // SOUTH
				
				if (this.roomAt(this.currentRoomPoint).playerPoint.y == this.roomSize + 1) {
					this.moveRoom_Direction('d');
				} else {
					
					// Point to move the player to (Down)
					Point newPlayerPoint = new Point(this.roomAt(this.currentRoomPoint).playerPoint.x,
							this.roomAt(this.currentRoomPoint).playerPoint.y + 1);
					
					// Run the method to move the player
					this.roomAt(this.currentRoomPoint).moveEntity(this.roomAt(this.currentRoomPoint).playerPoint,
							newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = this.roomAt(this.currentRoomPoint).playerPoint;
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).DIRECTION = "Down";
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).newImage();
					
					// Re-draw the room
					this.roomAt(this.currentRoomPoint).draw_Room(gc);
					
				}
				
			} else if (key.getCode() == KeyCode.D) { // WEST
				
				if (this.roomAt(this.currentRoomPoint).playerPoint.x == this.roomSize + 1) {
					this.moveRoom_Direction('r');
				} else {
					
					// Point to move the player to (Right)
					Point newPlayerPoint = new Point(this.roomAt(this.currentRoomPoint).playerPoint.x + 1,
							this.roomAt(this.currentRoomPoint).playerPoint.y);
					
					// Run the method to move the player
					this.roomAt(this.currentRoomPoint).moveEntity(this.roomAt(this.currentRoomPoint).playerPoint,
							newPlayerPoint);
					
					// Set the player direction
					newPlayerPoint = this.roomAt(this.currentRoomPoint).playerPoint;
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).DIRECTION = "Right";
					this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).newImage();
					
					// Re-draw the room
					this.roomAt(this.currentRoomPoint).draw_Room(gc);
					
				}
				
			}
		});
		
		/**
		 * 
		 * // Check for enemies\ Point battlePoint =
		 * this.roomAt(this.currentRoomPoint).checkForBattlePoint(); if (battlePoint !=
		 * null) {
		 * 
		 * Utilities.clearConsole(); BattleWorldTest.battleLoop();
		 * 
		 * this.roomAt(this.currentRoomPoint).removeEntity(battlePoint);
		 * 
		 * }
		 */
		
	}
	
}
