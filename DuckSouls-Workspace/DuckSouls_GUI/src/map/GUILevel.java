package map;

import java.awt.Point;
import java.util.Scanner;

//JavaFX
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import battle.BattleWorldTest;
import mattEntities.*;
import utils.Utilities;

public class GUILevel{
	
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
		this.roomAt(this.currentRoomPoint).placeEntity(new Point(this.roomSize / 2 + 1, this.roomSize / 2 + 1),
				new Player());
		
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
		
		this.roomAt(this.currentRoomPoint).placeEntity(newPlayerPoint, new Player());
		this.minimapArray[this.currentRoomPoint.x][this.currentRoomPoint.y] = "@";
		
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void moveLoop(GraphicsContext gc) {
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		int test = 0;
		while (test == 0) {

			this.roomAt(this.currentRoomPoint).draw_Tiles(gc);
			test += 1;
			
			/**
			
			// Check for enemies\
			Point battlePoint = this.roomAt(this.currentRoomPoint).checkForBattlePoint();
			if (battlePoint != null) {
				
				Utilities.clearConsole();
				BattleWorldTest.battleLoop();
				
				this.roomAt(this.currentRoomPoint).removeEntity(battlePoint);
				
			}
			*/
			
		}
		
	}
	
}
