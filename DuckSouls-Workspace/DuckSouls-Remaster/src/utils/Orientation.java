package utils;

import java.awt.Point;

/**
 * This enumerator is used to define the four cardinal directions. This prevents
 * having to pass characters or stings to determine direction.
 * 
 * @author Matthew Allwright
 */
public enum Orientation {
	
	NORTH("Up"),
	SOUTH("Down"),
	EAST("Right"),
	WEST("Left");
	
	public final String STR; // For basic access
	
	/**
	 * Creates a new orientation.
	 * 
	 * @param str
	 *            The colloquial name for the orientation. This is used for things
	 *            such as file access.
	 */
	private Orientation(String str) {
		this.STR = str;
	}
	
	/**
	 * Returns the point adjacent to the passed point in the specified direction.
	 * 
	 * @param position
	 *            The point to reference from.
	 * @param orientation
	 *            The direction to go away from the point.
	 * @return The point adjacent to the passed point in the specified direction.
	 */
	public static Point pointAtDirection(Point position, Orientation orientation) {
		
		switch (orientation) {
			
			case NORTH:
				return new Point(position.x, position.y - 1);
			
			case SOUTH:
				return new Point(position.x, position.y + 1);
			
			case EAST:
				return new Point(position.x + 1, position.y);
			
			case WEST:
				return new Point(position.x - 1, position.y);
			
			default:
				return position;
			
		}
		
	}
	
}
