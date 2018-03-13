package tiles;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Utilities;

/**
 * This class represents a basic, empty tile. It is used as the super class for
 * many other specific types of tile.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @author Colin Au Yeung
 * @version 2.0
 */
public class Tile {

	/*
	 * 
	 *  INSTANCE VARIABLES
	 * 
	 */
	private String		stringRepr	= "TER";
	private char		    fileCharacter;
	protected boolean	canWalkOn	= true;
	private boolean		isGUI;
	
	private String		pathToImage =  "/Sprites/Tiles/Sewer/Empty,png";
	private Image		image;
	private Point	    position	= new Point();
	

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
		this.isGUI = isGUI;
		
		if (this.isGUI)
			this.updateImage();
		
	}
	
	/**
	 * Creates a new tile at a specified position.
	 * 
	 * @param x The X co-ordinate of the tile.
	 * @param y The Y co-ordinate of the tile.
	 */
	public Tile(int x, int y, String pathToImage) {
		
		this.canWalkOn = false;
		this.stringRepr = "   ";
		this.position.setLocation(x, y);
		this.pathToImage = pathToImage;
		
	} // End of constructor
	
	/**
	 * Creates a new tile at a specified position.
	 * 
	 * @param x The X co-ordinate of the tile.
	 * @param y The Y co-ordinate of the tile.
	 */
	public Tile(int x, int y) {
		
		this.canWalkOn = false;
		this.stringRepr = "   ";
		this.position.setLocation(x, y);
		
	} // End of constructor
	
	/**
	 * Creates a new tile at a specified position and defined characteristics.
	 * 
	 * @param x The X co-ordinate of the tile.
	 * @param y The Y co-ordinate of the tile.
	 * @param canMoveOn Whether the player can move on this tile or not.
	 * @param stringRepr The string to display when printing the tile.
	 */
	public Tile(int x, int y, boolean canMoveOn, String stringRepr, String pathToImage) {
		
		this.position.setLocation(x, y);
		this.canWalkOn = canMoveOn;
		this.stringRepr = stringRepr;
		this.pathToImage = pathToImage;
		
	} // End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Create a new image depending on the image directory and direction of the
	 * entity.
	 */
	public void updateImage() {
		
		this.image = new Image("file:///" + Utilities.getParentDir() + this.pathToImage);
		
	}
	
	/**
	 * Returns the string representation of the tile.
	 * 
	 * @return The string representation of the tile.
	 */
	public String getStringRepr() {
		
		return this.stringRepr;
		
	} // End of getStringRepr
	
	/**
	 * Returns the file character of the tile.
	 * 
	 * @return The file character of the tile.
	 */
	public char getFileCharacter() {
		
		return this.fileCharacter;
		
	}
	
	/**
	 * Returns the ID of the map this tile is in.
	 * 
	 * @return The ID of the map this tile is in.
	 */
	public String getMapID() {
		
		return null;
		
	}//end of getMapID
	
	/**
	 * Returns the path to the image file corresponding with the entity's current state
	 * 
	 * @return the String corresponding to the path to the entity sprite (default floor sprite)
	 * 
	 */
	public Image getImage() {
		
		return new Image("file:///" + Utilities.getParentDir() + this.pathToImage);
		
	}// End of getImage
	
	/**
	 * Returns the X position of the tile.
	 * 
	 * @return The X position of the tile.
	 */
	public int getX() {
		
		return (int) this.position.getX();
		
	} // End of getX
	
	/**
	 * Returns the Y position of the tile.
	 * 
	 * @return The Y position of the tile.
	 */
	public int getY() {
		
		return (int) this.position.getY();
		
	} // End of getY
	
	/**
	 * Returns whether the tile can be walked on or not.
	 * 
	 * @return Whether the tile can be walked on or not.
	 */
	public boolean getCanWalkOn() {
		
		return this.canWalkOn;
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
