package tiles;

import javafx.scene.image.Image;

public enum Water implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	WATER_IT("~~~", "SIT", "Water-Intersection-Top.png", false),
	WATER_IB("~~~", "SIB", "Water-Intersection-Bottom.png", false),
	WATER_IL(" ~~", "SIL", "Water-Intersection-Left.png", false),
	WATER_IR("~~ ", "SIR", "Water-Intersection-Right.png", false),
	WATER_TL(" ~~", "STL", "Water-Top-Left.png", false),
	WATER_TR("~~ ", "STR", "Water-Top-Right.png", false),
	WATER_BL(" ~~", "SBL", "Water-Bottom-Left.png", false),
	WATER_BR("~~ ", "SBR", "Water-Bottom-Right.png", false),
	WATER_H("~~~", "SH", "Water-Horizontal.png", false),
	WATER_V(" ~ ", "SV", "Water-Verticle.png", false),
	WATER_CT(" ~ ", "SCT", "Water-Cap-Top.png", false),
	WATER_CB(" ~ ", "SCB", "Water-Cap-Bottom.png", false),
	WATER_CL("~~~", "SCL", "Water-Cap-Left.png", false),
	WATER_CR("~~~", "SCR", "Water-Cap-Right.png", false),
	BRIDGE_V("| |", "SBV", "Bridge-Verticle.png", true),
	BRIDGE_H("| |", "SBH", "Bridge-Horizontal.png", true);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final String	FILE_STRING;
	private final String	IMAGE_NAME;
	private boolean	CAN_WALK_ON;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new wall.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to draw the wall in the text version
	 *            of the game.
	 * @param imageName
	 *            The name of the image used to draw the wall in the GUI version of
	 *            the game.
	 */
	private Water(String stringRepr, String fileString, String imageName, boolean canWalkOn) {
		
		this.STRING_REPR = stringRepr;
		this.FILE_STRING = fileString;
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
		return this.FILE_STRING;
	}
	
}
