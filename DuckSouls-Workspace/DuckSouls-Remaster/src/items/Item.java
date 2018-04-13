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
	 * 
	 * @return The name of the item.
	 */
	public abstract String getName();
	
	/**
	 * Returns if the item should be spawned via RNG.
	 * 
	 * @return If the item should be spawned via RNG.
	 */
	public abstract boolean tryToSpawn();
	
	/**
	 * Returns the spawn chance of the item.
	 * 
	 * @return The spawn chance of the item.
	 */
	public abstract int getSpawnChance();
	
	/**
	 * Returns the string used to read/write the tile to text files.
	 * 
	 * @return The string used to read/write the tile to text files.
	 */
	public abstract String getFileString();
	
}
