package ui;

import genericInterfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import world.Room;

public class InventoryDrawer implements Drawable{

	/*
	 * 
	 * CONSTANTS
	 * 
	 */

	private static final int ITEMS 			= 4;
	private static final int TILESIZE 		= 64;
	
	private static final Image INVENTORY 	= new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Inventory.png");
	private static final Image BLUR 		= new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Black.png");

	/*
	 * 
	 * METHODS
	 * 
	 */

	public static void drawInventory(GraphicsContext gc) {
		gc.setGlobalAlpha(0.5);
		gc.drawImage(BLUR, 0, 0);
		gc.setGlobalAlpha(1);
		gc.drawImage(INVENTORY, 0, 0);
		
	}

	@Override
	public Image getImage() {
		// Does nothing in this class
		return null;
	}

	@Override
	public String getStringRepr() {
		// Does nothing in this class
		return null;
	}

}
