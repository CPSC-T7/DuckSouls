package tiles;

import javafx.scene.image.Image;

/**
 * This enumerator contains constants for different types of wall tiles.
 * 
 * @author Matthew Allwright
 */
public enum Wall implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	WALL_T("═══", "WT", "Wall-Top.png"),
	WALL_B("═══", "WB", "Wall-Bottom.png"),
	WALL_L(" ║ ", "WL", "Wall-Left.png"),
	WALL_R(" ║ ", "WR", "Wall-Right.png"),
	WALL_TL(" ╔═", "WTL", "Wall-Top-Left.png"),
	WALL_TR("═╗ ", "WTR", "Wall-Top-Right.png"),
	WALL_BL(" ╚═", "WBL", "Wall-Bottom-Left.png"),
	WALL_BR("═╝ ", "WBR", "Wall-Bottom-Right.png");
	
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
	 * @param fileString
	 *            the string used for I/O of the tile.
	 * @param imageName
	 *            The name of the image used to draw the wall in the GUI version of
	 *            the game.
	 */
	private Wall(String stringRepr, String fileString, String imageName) {
		
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
