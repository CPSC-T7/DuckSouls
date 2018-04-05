package ui;

import java.util.HashMap;

import entities.Player;
import genericInterfaces.Drawable;
import items.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import world.Room;

/**
 * Draws the player inventory.
 * 
 * @author Wylee McAndrews
 *
 */
public class InventoryDrawer implements Drawable{

	/*
	 * 
	 * CONSTANTS
	 * 
	 */

	private static final int ITEMS 				= 4;
	private static final int TILESIZE 			= 64;
	private static final int NUMBER_SIZE		= 16;
	
	private static final int ITEM_X_POSITION	= TILESIZE;
	private static final int EQUIPT_X_POSITION	= TILESIZE * 6;
	
	private static final int GOO_POSITION 		= TILESIZE * 3;
	private static final int FISH_POSITION 		= TILESIZE * 4;
	private static final int CRUTON_POSITION 	= TILESIZE * 5;
	private static final int BUGS_POSITION 		= TILESIZE * 6;
	
	private static final int ARMOUR_POSITION	= TILESIZE * 4;
	private static final int WEAPON_POSITION 	= TILESIZE * 7;
	
	private static final Image INVENTORY 		= new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Inventory.png");
	private static final Image BLUR 			= new Image(SPRITE_FOLDER_PATH + "/Backgrounds/Black.png");

	/*
	 * 
	 * METHODS
	 * 
	 */

	/**
	 * Draw the inventory background, item amounts,
	 * armour type, and weapon type.
	 * 
	 * @param gc
	 * @param player
	 */
	public static void drawInventory(GraphicsContext gc, Player player) {
		
		// Tint the game background
		gc.setGlobalAlpha(0.5);
		gc.drawImage(BLUR, 0, 0);
		gc.setGlobalAlpha(1);
		
		// Display the inventory background
		gc.drawImage(INVENTORY, 0, 0);
		
		// Display the number of each item
		player.getInventory().forEach((item, quantity) -> {
			
			switch(item.getStringRepr()) {
			
				case(" G "): //Goo
					
					drawItemNumber(GOO_POSITION, quantity, gc);
					break;
					
				case(" F "): //Fish
					
					drawItemNumber(FISH_POSITION, quantity, gc);
					break;
					
				case(" C "): //Cruton
					
					drawItemNumber(CRUTON_POSITION, quantity, gc);
					break;
					
				case(" B "): //Bugs
					
					drawItemNumber(BUGS_POSITION, quantity, gc);
					break;
				}
		});

		// Draw Armour and Weapon
		gc.drawImage(player.getArmour().getImage(), EQUIPT_X_POSITION, ARMOUR_POSITION);
		gc.drawImage(player.getWeapon().getImage(), EQUIPT_X_POSITION, WEAPON_POSITION);
		
	}
	
	/**
	 * Draws the item-amount beside the item in the inventory.
	 * Works for item-amounts less than 3 digits.
	 * 
	 * @param itemYPosition
	 * 						Y position to place the numbers
	 * @param quantity
	 * 						Number of the specific item
	 * @param gc
	 * 						GraphicsContext
	 */
	private static void drawItemNumber(int itemYPosition, int quantity, GraphicsContext gc) {
		if (quantity < 10) {
			gc.drawImage(new Image (SPRITE_FOLDER_PATH + "/Numbers/" + quantity + ".png"), 
														ITEM_X_POSITION * 2, itemYPosition);
		}else {
			// Draw first digit
			gc.drawImage(new Image (SPRITE_FOLDER_PATH + "/Numbers/" + quantity/10 + ".png"), 
														ITEM_X_POSITION * 2, itemYPosition);
			// Draw second digit
			gc.drawImage(new Image (SPRITE_FOLDER_PATH + "/Numbers/" + quantity%10 + ".png"), 
														ITEM_X_POSITION * 2 + NUMBER_SIZE, itemYPosition);
		}
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
