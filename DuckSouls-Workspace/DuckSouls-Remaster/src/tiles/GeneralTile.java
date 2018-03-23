package tiles;

import javafx.scene.image.Image;

public enum GeneralTile implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	EMPTY("   ", new Image(TILE_SPRITE_FOLDER_PATH + "Empty.png"), false),
	FLOOR("   ", new Image(TILE_SPRITE_FOLDER_PATH + "Floor.png"), true),
	PATH(" . ", new Image(TILE_SPRITE_FOLDER_PATH + "Path.png"), true),
	STAIRS("/=/", new Image(TILE_SPRITE_FOLDER_PATH + "Stairs.png"), true);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final Image		IMAGE;
	private final boolean	CAN_WALK_ON;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	private GeneralTile(String stringRepr, Image image, boolean canWalkOn) {
		
		this.STRING_REPR = stringRepr;
		this.IMAGE = image;
		this.CAN_WALK_ON = canWalkOn;
		
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
