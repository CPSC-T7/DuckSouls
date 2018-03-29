package tiles;

import genericInterfaces.Drawable;

public interface Tile extends Drawable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String TILE_SPRITE_FOLDER_PATH = SPRITE_FOLDER_PATH + "Tiles/";
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Returns if the tile can be walked on.
	 * @return If the tile can be walked on.
	 */
	public abstract boolean getCanWalkOn();
	
}