package tiles;

import javafx.scene.image.Image;

public enum Wall implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	WALL_T("═══", "Wall-Top.png"),
	WALL_B("═══", "Wall-Bottom.png"),
	WALL_L(" ║ ", "Wall-Left.png"),
	WALL_R(" ║ ", "Wall-Right.png"),
	WALL_TL(" ╔═", "Wall-Top-Left.png"),
	WALL_TR("═╗ ", "Wall-Top-Right.png"),
	WALL_BL(" ╚═", "Wall-Bottom-Left.png"),
	WALL_BR("═╝ ", "Wall-Bottom-Right.png");
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
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
	private Wall(String stringRepr, String imageName) {
		
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
