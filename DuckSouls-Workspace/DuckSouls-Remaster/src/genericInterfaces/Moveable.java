package genericInterfaces;

import java.awt.Point;
import utils.Orientation;

/**
 * This interface contains abstracts for anything that can move.
 * 
 * @author Matthew Allwright
 */
public interface Moveable {
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Returns the object's orientation.
	 * 
	 * @return The object's orientation.
	 */
	public abstract Orientation getOrientation();
	
	/**
	 * Sets the object's orientation.
	 * 
	 * @param orientation
	 *            The object's new orientation.
	 */
	public abstract void setOrientation(Orientation orientation);
	
	/**
	 * Returns the object's position.
	 * 
	 * @return The object's position.
	 */
	public abstract Point getPosition();
	
	/**
	 * Sets the object's position.
	 * 
	 * @param orientation
	 *            The object's new position.
	 */
	public abstract void setPosition(Point position);
	
}
