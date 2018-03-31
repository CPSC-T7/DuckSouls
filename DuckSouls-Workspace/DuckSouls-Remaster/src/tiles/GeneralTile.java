package tiles;

import javafx.scene.image.Image;

public enum GeneralTile implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	EMPTY("   ", "Empty.png", false),
	FLOOR("   ", "Floor.png", true),
	PATH(" . ", "Path.png", true),
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
	
}
