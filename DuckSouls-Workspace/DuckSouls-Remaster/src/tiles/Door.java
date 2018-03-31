package tiles;

import javafx.scene.image.Image;

public enum Door implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	DOOR_T(" D ", "Door-Top"),
	DOOR_B(" D ", "Door-Bottom"),
	DOOR_L(" D ", "Door-Left"),
	DOOR_R(" D ", "Door-Right");
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
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
	 * @param imageName
	 *            The image used to draw the wall in the GUI version of the game.
	 */
	private Door(String stringRepr, String imageName) {
		
		this.STRING_REPR = stringRepr;
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
	
}
