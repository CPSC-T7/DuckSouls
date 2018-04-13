package ui;

import controllers.GUIGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import world.Room;

/**
 * This class contains methods to draw a room object in both the text and GUI
 * versions of the game.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 */
public class RoomDrawer {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	private static final int	TILES		= 0;
	private static final int	ITEMS		= 1;
	private static final int	ENTITIES	= 2;
	private static final int	TILESIZE	= 64;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Draws a room in the GUI version of the game to the desired graphics context.
	 * 
	 * @param room
	 *            The room to draw.
	 * @param gc
	 *            The graphics context to draw the room to.
	 */
	public static void drawGUIRoom(Room room, GraphicsContext gc) {
		
		// Clear the screen
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, (GUIGame.mapsize + 2) * GUIGame.tileSize, (GUIGame.mapsize + 2) * GUIGame.tileSize);
		
		// Get the sprites to draw
		Image[][][] sprites = room.getImageSprites();
		
		// For each position...
		for (int x = 0; x < sprites.length; x++) {
			for (int y = 0; y < sprites[0].length; y++) {
				
				// Draw the tiles
				if (sprites[x][y][TILES] != null) {
					gc.drawImage(sprites[x][y][TILES], x * TILESIZE, y * TILESIZE);
					
				}
				
				// Draw the items
				if (sprites[x][y][ITEMS] != null) {
					gc.drawImage(sprites[x][y][ITEMS], x * TILESIZE, y * TILESIZE);
					
				}
				
				// Draw the entities
				if (sprites[x][y][ENTITIES] != null) {
					gc.drawImage(sprites[x][y][ENTITIES], x * TILESIZE, y * TILESIZE);
					
				}
				
			}
		}
	}
	
	/**
	 * Draws a room in the text version of the game to the console.
	 * 
	 * @param room
	 *            The room to draw.
	 */
	public static void drawTextRoom(Room room) {
		
		// Grab the sprites (Strings)
		String[][] sprites = room.getAllTextSprites();
		
		// Print the room
		for (String[] rows : sprites) {
			
			for (String sprite : rows) {
				
				// Print the individual tile sprite
				System.out.print(sprite);
				
			}
			
			// Wrap the line
			System.out.println();
			
		}
		
	}
	
}
