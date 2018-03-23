package tiles;

import javafx.scene.image.Image;

public enum Door implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	DOOR_T(" D ", new Image(TILE_SPRITE_FOLDER_PATH + "Door-Top")),
	DOOR_B(" D ", new Image(TILE_SPRITE_FOLDER_PATH + "Door-Bottom")),
	DOOR_l(" D ", new Image(TILE_SPRITE_FOLDER_PATH + "Door-Left")),
	DOOR_R(" D ", new Image(TILE_SPRITE_FOLDER_PATH + "Door-Right"));
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final Image		IMAGE;
	private boolean			CAN_WALK_ON = true;
	
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
	 * @param image
	 *            The image used to draw the wall in the GUI version of the game.
	 */
	private Door(String stringRepr, Image image) {
		
		this.STRING_REPR = stringRepr;
		this.IMAGE = image;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return this.IMAGE;
	}
	
	@Override
	public String getStringRepr() {
		return this.STRING_REPR;
	}
	
	@Override
	public boolean getCanWalkOn() {
		return this.CAN_WALK_ON;
	}
	
}
