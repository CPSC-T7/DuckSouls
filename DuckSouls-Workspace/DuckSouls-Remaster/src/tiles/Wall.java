package tiles;

import javafx.scene.image.Image;

public enum Wall implements Tile {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	WALL_T("═══", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Top.png")),
	WALL_B("═══", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Bottom.png")),
	WALL_L(" ║ ", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Left.png")),
	WALL_R(" ║ ", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Right.png")),
	WALL_TL(" ╔═", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Top-Left.png")),
	WALL_TR("═╗ ", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Top-Right.png")),
	WALL_BL(" ╚═", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Bottom-Left.png")),
	WALL_BR("═╝ ", new Image(TILE_SPRITE_FOLDER_PATH + "Wall-Bottom-Right.png"));
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	STRING_REPR;
	private final Image		IMAGE;
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
	 * @param image
	 *            The image used to draw the wall in the GUI version of the game.
	 */
	private Wall(String stringRepr, Image image) {
		
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
