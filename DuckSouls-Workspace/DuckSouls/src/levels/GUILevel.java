package levels;

import java.awt.Point;

import entities.*;

import javafx.scene.canvas.GraphicsContext;
import rooms.GUIRoom;
import utils.Orientation;

/**
 * This class represents a GUI level; A 2D grid of GUI rooms.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 2.0
 */
public class GUILevel extends Level {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final boolean IS_GUI = true;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public GUILevel(Player player, Point playerPosition, Point roomPoint, int enemySpawnChance) {
		
		super(player, playerPosition, roomPoint, enemySpawnChance);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Generates an array of text rooms for the level to use.
	 */
	protected void genRoomArray() {
		
		this.roomArray = new GUIRoom[this.levelWidth][this.levelHeight];
		
		// For each room space...
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				// Generate a square, random room
				this.roomArray[x][y] = new GUIRoom(this.getRoomSize(), enemySpawnChance);
				
			}
		}
		
		super.genRoomArray_STUB(IS_GUI);
		
	}
	
	/**
	 * Returns the room at a certain point in the room array.
	 * 
	 * @param position
	 *            The position of the room to get.
	 * @return The room at that position.
	 */
	public GUIRoom roomAt(Point position) {
		return (GUIRoom) this.roomArray[position.x][position.y];
	}
	
	/**
	 * Moves the player and current room in a certain direction.
	 * 
	 * @param direction
	 *            An Orientation designating the direction to move in the room
	 *            array.
	 * @param gc
	 *            Graphics Context
	 */
	public void moveRoom_Direction(Orientation direction, GraphicsContext gc) {
		
		// Get the player and their position
		Point newPlayerPoint = new Point(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		Player player = (Player) this.roomAt(this.getCurrentRoomPoint()).entityAt(newPlayerPoint);
		
		// Remove the player from the old room
		this.roomAt(this.getCurrentRoomPoint()).removeEntity(this.roomAt(this.getCurrentRoomPoint()).getPlayerPoint());
		
		// Depending on the direction...
		switch (direction) {
			
			case NORTH:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y - 1));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.y = this.getRoomSize() + 1;
				break;
			
			case SOUTH:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x, this.getCurrentRoomPoint().y + 1));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.y = 0;
				break;
			
			case WEST:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x - 1, this.getCurrentRoomPoint().y));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.x = this.getRoomSize() + 1;
				break;
			
			case EAST:
				// Move to the new room...
				this.setCurrentRoomPoint(new Point(this.getCurrentRoomPoint().x + 1, this.getCurrentRoomPoint().y));
				// And swap the player to the corresponding side of the new room
				newPlayerPoint.x = 0;
				break;
			
		}
		
		// Place the player at the new position in the new room
		this.roomAt(this.getCurrentRoomPoint()).placeEntity(newPlayerPoint, player);
		
		// Set the player direction
		this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).setOrientation(direction);
		this.roomAt(this.currentRoomPoint).entityAt(newPlayerPoint).updateImage();
		
		// Re-draw the room
		this.roomAt(this.currentRoomPoint).draw(gc);
		
	}
	
}
