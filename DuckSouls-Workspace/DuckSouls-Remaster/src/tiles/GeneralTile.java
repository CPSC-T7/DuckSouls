package tiles;

import javafx.scene.image.Image;

/**
 * This enumerator contains constants for different types of tiles.
 * 
 * @author Matthew Allwright
 */
public enum GeneralTile implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	EMPTY("XXX", "Empty.png", false),
	FLOOR("   ", "Floor.png", true),
	PATH(" . ", "Floor.png", true),
	STAIRS("/=/", "Stairs.png", true);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final String	IMAGE_NAME;
	private final boolean	CAN_WALK_ON;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new tile.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to draw the wall in the text version
	 *            of the game.
	 * @param imageName
	 *            The name of the image used to draw the wall in the GUI version of
	 *            the game.
	 * @param canWalkOn
	 *            If the player can walk on the tile or not.
	 */
	private GeneralTile(String stringRepr, String imageName, boolean canWalkOn) {
		
		this.STRING_REPR = stringRepr;
		this.IMAGE_NAME = imageName;
		this.CAN_WALK_ON = canWalkOn;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return new Image(TILE_SPRITE_FOLDER_PATH + this.IMAGE_NAME);
	}
	
	@Override
	public String getStringRepr() {
		return this.STRING_REPR;
	}
	
	@Override
	public boolean getCanWalkOn() {
		return this.CAN_WALK_ON;
	}
	
	@Override
	public String getFileString() {
		return this.STRING_REPR.replaceAll(" ", "");
	}
	
}
