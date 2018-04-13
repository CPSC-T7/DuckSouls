package tiles;

import genericInterfaces.Drawable;

/**
 * This interface contains abstracts and constants for different types of tiles.
 * 
 * @author Matthew Allwright
 */
public interface Tile extends Drawable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String TILE_SPRITE_FOLDER_PATH = SPRITE_FOLDER_PATH + "Tiles/Sewer/";
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Returns if the tile can be walked on.
	 * 
	 * @return If the tile can be walked on.
	 */
	public abstract boolean getCanWalkOn();
	
	/**
	 * Returns the string used to read/write the tile to text files.
	 * 
	 * @return The string used to read/write the tile to text files.
	 */
	public abstract String getFileString();
	
}
