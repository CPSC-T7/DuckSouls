package ui;

import controllers.GUIGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import world.Room;

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
	
	public static void drawGUIRoom(Room room, GraphicsContext gc) {
		
//		gc.setFill(Color.rgb(0xBE, 0xEF, 0x69));
		gc.setFill(Color.BLANCHEDALMOND);
		gc.fillRect(0, 0, (GUIGame.mapsize + 2) * GUIGame.tileSize, (GUIGame.mapsize + 2) * GUIGame.tileSize);
		
		Image[][][] sprites = room.getImageSprites();
		
		for (int x = 0; x < sprites.length; x++) {
			for (int y = 0; y < sprites[0].length; y++) {
				
				if (sprites[x][y][TILES] != null) {
					// System.out.println("TILE");
					gc.drawImage(sprites[x][y][TILES], x * TILESIZE, y * TILESIZE);
					
				}
				if (sprites[x][y][ITEMS] != null) {
					// System.out.println("ITEMS");
					gc.drawImage(sprites[x][y][ITEMS], x * TILESIZE, y * TILESIZE);
					
				}
				if (sprites[x][y][ENTITIES] != null) {
					// System.out.println("ENTITES");
					gc.drawImage(sprites[x][y][ENTITIES], x * TILESIZE, y * TILESIZE);
					
				}
			}
		}
	}
	
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
