package mattTiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
	
	public Image	IMAGE;
	public String	STRING_REPR	= "TER";
	public String	FILE_CHAR;
	public boolean	CAN_WALK_ON	= true;
	
	/**
	 * Creates a tile.
	 * 
	 * @param IMAGE
	 *            The 3-character string used to save the map file
	 * @param CAN_WALK_ON
	 *            Whether or not a player can walk on the tile.
	 * @param IMAGE
	 * 			  The Image to draw in the GUI version.
	 */
	protected Tile(String STRING_REPR, boolean CAN_WALK_ON, Image IMAGE) {
		
		this.STRING_REPR = STRING_REPR;
		this.IMAGE = IMAGE;
		this.FILE_CHAR = Character.toString(this.STRING_REPR.charAt(1)); // Middle char
		this.CAN_WALK_ON = CAN_WALK_ON;
		
	}
	
	public void drawTile(GraphicsContext gc, int[] position) {
		gc.drawImage(this.IMAGE, position[0], position[1]);
	}
	
}
