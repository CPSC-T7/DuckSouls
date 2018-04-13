package tiles;

import javafx.scene.image.Image;

/**
 * This enumerator contains constants for different types of door tiles.
 * 
 * @author Matthew Allwright
 */
public enum Door implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	DOOR_T(" D ", "DT", "Door-Top.png"),
	DOOR_B(" D ", "DB", "Door-Bottom.png"),
	DOOR_L(" D ", "DL", "Door-Left.png"),
	DOOR_R(" D ", "DR", "Door-Right.png");
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final String	FILE_STRING;
	private final String	IMAGE_NAME;
	private boolean			CAN_WALK_ON	= true;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new door.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to draw the wall in the text version
	 *            of the game.
	 * @param fileString
	 *            the string used for I/O of the tile.
	 * @param imageName
	 *            The image used to draw the wall in the GUI version of the game.
	 */
	private Door(String stringRepr, String fileString, String imageName) {
		
		this.STRING_REPR = stringRepr;
		this.FILE_STRING = fileString;
		this.IMAGE_NAME = imageName;
		
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
		return this.FILE_STRING;
	}
	
}
