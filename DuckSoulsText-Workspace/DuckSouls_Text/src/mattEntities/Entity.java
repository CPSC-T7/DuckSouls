package mattEntities;

import java.awt.Point;

public class Entity {
	
	public String STRING_REPR = "EER";
	public String type = "NUN";
	public Point POS;
	
	/**
	 * Creates an entity.
	 * 
	 * @param STRING_REPR
	 *            The 3-character string used to print the tile.
	 */
	protected Entity(String STRING_REPR) {
		
		this.STRING_REPR = STRING_REPR;
		
	}
	
}
