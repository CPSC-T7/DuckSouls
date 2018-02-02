import java.awt.Point;

public class TestRunner {

	public static void main(String[] args) {

		Point[] doors = new Point[4];

		doors[0] = new Point(0, 1);
		doors[1] = new Point(5, 9);
		doors[2] = new Point(4, 0);
		doors[3] = new Point(9, 2);

		TextRoom r = new TextRoom(8, 8, doors, new Point(3, 3));
		
		r.moveLoop();

	}

}
