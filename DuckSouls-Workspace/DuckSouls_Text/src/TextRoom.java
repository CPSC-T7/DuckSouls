import java.awt.Point;

//TODO: Fill in JavaDocs

/**
 * Text-based room class.
 * 
 * @author Matthew Allwright
 * @requires java.awt.Point
 * @version 0.1.0
 *
 */
public class TextRoom {

	// INSTANCE VARIABLES

	private int width, height;
	private final int DEFAULT_WIDTH = 8, DEFAULT_HEIGHT = 8;
	private String[][] tileArray;

	// CONSTRUCTORS

	/**
	 * Creates an empty, doorless room of default size.
	 */
	TextRoom() {

		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.genTileArray();

	}

	/**
	 * Creates an empty room of set size.
	 * 
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 */
	TextRoom(int width, int height) {

		this.width = width;
		this.height = height;
		this.genTileArray();

	}

	/**
	 * Creates an empty room of default size, with specified door points.
	 * 
	 * @param doors
	 *            A java.awt.Point array of door co-ordinates. Acceptable points lie
	 *            in a range of (0, 0) to (width+2, height+2) to accommodate walls,
	 *            with (0, 0) as the top-left.
	 */
	TextRoom(Point[] doors) {

		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.genTileArray();

		for (Point pos : doors) {
			this.setTile(pos, 'D');
		}

	}

	/**
	 * Creates an empty room of specified size, with specified door points.
	 * 
	 * @param width
	 *            Width of the room. Must be 0 < width < 32.
	 * @param height
	 *            Height of the room. Must be 0 < height < 32.
	 * @param doors
	 *            A java.awt.Point array of door co-ordinates. Acceptable points lie
	 *            in a range of (0, 0) to (width+2, height+2) to accommodate walls,
	 *            with (0, 0) as the top-left.
	 */
	TextRoom(int width, int height, Point[] doors) {

		this.width = width;
		this.height = height;
		this.genTileArray();

		for (Point pos : doors) {
			this.setTile(pos, 'D');
		}

	}

	// METHODS

	public void draw() {

		for (int i = 0; i < this.width + 2; i++) {
			for (int j = 0; j < this.height + 2; j++) {

				System.out.print(this.tileArray[i][j]);

			}

			System.out.println();

		}

	}

	private void genTileArray() {

		this.tileArray = new String[this.width + 2][height + 2];

		for (int i = 0; i < this.width + 2; i++) {
			for (int j = 0; j < this.height + 2; j++) {

				if (i == 0 && j == 0) {

					this.tileArray[i][j] = "╔";

				} else if (i == this.width + 1 && j == 0) {

					this.tileArray[i][j] = "╚";

				} else if (i == 0 && j == this.height + 1) {

					this.tileArray[i][j] = "╗";

				} else if (i == this.width + 1 && j == this.height + 1) {

					this.tileArray[i][j] = "╝";

				} else if (i == 0 || i == this.width + 1) {

					this.tileArray[i][j] = "═══";

				} else if (j == 0 || j == this.height + 1) {

					this.tileArray[i][j] = "║";

				} else {

					this.tileArray[i][j] = " . ";

				}

			}

		}

	}

	private void setTile(Point pos, char ch) {

		String paddingChar = " ";

		if (pos.x == 0 || pos.x == this.width + 1) {
			paddingChar = "";
		} else if (pos.y == 0 || pos.y == this.width + 1) {
			paddingChar = "═";
		}

		this.tileArray[pos.y][pos.x] = paddingChar + ch + paddingChar;

	}

}
