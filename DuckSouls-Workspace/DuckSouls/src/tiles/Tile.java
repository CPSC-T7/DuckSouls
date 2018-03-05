package tiles;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents the tiles for DuckSouls.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 1.3
 */
public class Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private String		stringRepr	= "TER";
	private char		fileCharacter;
	protected boolean	canWalkOn	= true;
	
	private String		pathToImage;
	private Image		image;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a tile.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the tile.
	 * @param canWalkOn
	 *            Whether or not a player can walk on the tile.
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 */
	protected Tile(boolean isGUI, String stringRepr, boolean canWalkOn, String pathToImage) {
		
		this.stringRepr = stringRepr;
		this.fileCharacter = this.stringRepr.charAt(1); // Middle char
		this.canWalkOn = canWalkOn;
		this.pathToImage = pathToImage;
		
		if (isGUI)
			this.updateImage();
		
	}
	
	/**
	 * Draw the tile to the screen at a position (x,y)
	 * 
	 * @param gc
	 *            Graphics Context
	 * @param position
	 *            Entity Position
	 */
	public void drawTile(GraphicsContext gc, Point position) {
		
		gc.drawImage(this.image, position.x, position.y);
		
	}
	
	/**
	 * Create a new image depending on the image directory and direction of the
	 * entity.
	 */
	public void updateImage() {
		
		this.image = new Image(this.pathToImage.substring(3));
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Returns the string representation of the tile.
	 * 
	 * @return The string representation of the tile.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	}
	
	/**
	 * Returns the file character of the tile.
	 * 
	 * @return The file character of the tile.
	 */
	public char getFileCharacter() {
		
		return this.fileCharacter;
		
	}
	
	/**
	 * Returns whether the tile can be walked on or not.
	 * 
	 * @return Whether the tile can be walked on or not.
	 */
	public boolean getCanWalkOn() {
		
		return this.canWalkOn;
		
	}
	
}
