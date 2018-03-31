package ui;

import world.Room;

public class RoomDrawer {
	
	public static void drawRoom(Room room, boolean isGUI) {
		
		if (isGUI) {
			
			// TODO: Draw GUI Room.
			
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
