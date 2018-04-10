package tiles;

import javafx.scene.image.Image;

public enum Water implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	WATER_IT("###", "SIT", "Water-Intersection-Top.png", false),
	WATER_IB("###", "SIB", "Water-Intersection-Bottom.png", false),
	WATER_IL(" ##", "SIL", "Water-Intersection-Left.png", false),
	WATER_IR("## ", "SIR", "Water-Intersection-Right.png", false),
	WATER_TL(" **", "STL", "Water-Top-Left.png", false),
	WATER_TR("** ", "STR", "Water-Top-Right.png", false),
	WATER_BL(" **", "SBL", "Water-Bottom-Left.png", false),
	WATER_BR("** ", "SBR", "Water-Bottom-Right.png", false),
	WATER_H("***", "SH", "Water-Horizontal.png", false),
	WATER_V(" # ", "SV", "Water-Verticle.png", false),
	WATER_BV("* *", "SBV", "Bridge-Verticle.png", true),
	WATER_BH("* *", "SBH", "Bridge-Horizontal.png", true);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final String	FILE_STRING;
	private final String	IMAGE_NAME;
	private final boolean	CAN_WALK_ON	= false;
	
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
