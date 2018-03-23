package items;

import genericInterfaces.Drawable;

public interface Item extends Drawable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String ITEM_SPRITE_FOLDER_PATH = SPRITE_FOLDER_PATH + "Items/";
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Returns the name of the item.
	 * @return The name of the item.
	 */
	public abstract String getName();
	
	/**
	 * Returns if the item should be spawned via RNG.
	 * @return If the item should be spawned via RNG.
	 */
	public abstract boolean tryToSpawn();
	
}
