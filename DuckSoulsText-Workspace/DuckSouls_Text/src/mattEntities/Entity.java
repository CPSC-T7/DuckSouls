package mattEntities;

import java.awt.Point;

public class Entity {
	
	private Point		position	= new Point();
	private String		stringRepr	= "EER";
	private Orientation	orientation	= Orientation.SOUTH;
	
	/**
	 * Creates an entity.
	 * 
	 * @param STRING_REPR
	 *            The 3-character string used to print the tile.
	 */
	protected Entity(String STRING_REPR) {
		
		this.stringRepr = STRING_REPR;
		
	}
	
	/**
	 * Returns the string representation of the character.
	 * 
	 * @return The string representation of the character.
	 */
	public String getStringRepr() {
		
		return new String(this.stringRepr);
		
	} // End of getStringRepr
	
	/**
	 * Returns the position of the character.
	 * 
	 * @return The position of the character.
	 */
	public Point getPosition() {
		
		return new Point(this.position);
		
	} // End of getPosition
	
	/**
	 * Returns the position of the character. <br>
	 * TODO: Privacy Leak?
	 * 
	 * @return The position of the character.
	 */
	public Orientation getOrientation() {
		
		return this.orientation;
		
	} // End of getPosition
	
}
