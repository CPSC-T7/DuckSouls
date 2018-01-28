import java.awt.Point;

public class TestRunner {

	public static void main(String[] args) {

		Point[] doors = new Point[3];

		doors[0] = new Point(0, 1);
		doors[1] = new Point(2, 3);
		doors[2] = new Point(4, 0);

		TextRoom r = new TextRoom(5, 8, doors);

		r.draw();

	}

}
