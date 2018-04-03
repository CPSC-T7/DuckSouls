package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import world.Room;

public class RoomDrawer {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */

	private static final int TILES = 0;
	private static final int ITEMS = 1;
	private static final int ENTITIES = 2;
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	private static GraphicsContext gc;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static void setGraphicsContext(GraphicsContext gc) {
		RoomDrawer.gc = gc;
	}
	
	public static void drawRoom(Room room, boolean isGUI) {
		
		if (isGUI) {
			
			Image[][][] sprites = room.getAllImageSprites();
			
			for (int x = 0; x < sprites.length; x++) {
				for (int y = 0; y < sprites[0].length; y++) {
					
					/*
					 * sprites[x][y][TILES] --> tile @ (x, y)
					 * sprites[x][y][ITEMS] --> item @ (x, y)
					 * sprites[x][y][ENTITIES] --> entity @ (x, y)
					 */
					
				}
			}
			
		} else {
			
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
	
}
