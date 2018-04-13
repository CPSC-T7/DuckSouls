package genericInterfaces;

import javafx.scene.image.Image;
import utils.Utilities;

/**
 * This interface contains abstracts and constants for anything that can be
 * drawn.
 * 
 * @author Matthew Allwright
 */
public interface Drawable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	public static final String SPRITE_FOLDER_PATH = "file:///" + Utilities.parentDir + "/Sprites/";
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Returns the object's graphical image.
	 * 
	 * @return The object's graphical image.
	 */
	public abstract Image getImage();
	
	/**
	 * Returns the object's string representation for drawing.
	 * 
	 * @return The object's string representation for drawing.
	 */
	public abstract String getStringRepr();
	
}
