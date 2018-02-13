import java.awt.Point;

import map.TextRoom;
import map.TextRoom.Entity;

public class RoomTests {
	
	public static void main(String[] args) {
		
		TextRoom r = new TextRoom("Test Room", 5, 5);
		r.placeEntity(new Point(4, 4), Entity.PLAYER);
		
		// r.moveLoop();
		
	}
	
}
