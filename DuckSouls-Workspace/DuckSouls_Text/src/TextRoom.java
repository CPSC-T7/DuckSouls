import java.awt.Point;

//TODO: Fill in JavaDocs

/**
 * Text-based room class.
 * 
 * @author Matthew Allwright
 * @requires java.awt.Point
 * @version 0.2.2
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

	/**
	 * Generates and appends a tile array to the instance.
	 */
	private void genTileArray() {

		this.tileArray = new String[this.width + 2][this.height + 2];

		for (int x = 0; x < this.width + 2; x++) {
			for (int y = 0; y < this.height + 2; y++) {

				if (x == 0 && y == 0) {

					this.tileArray[x][y] = "╔";

				} else if (x == this.width + 1 && y == 0) {

					this.tileArray[x][y] = "╗";

				} else if (x == 0 && y == this.height + 1) {

					this.tileArray[x][y] = "╚";

				} else if (x == this.width + 1 && y == this.height + 1) {

					this.tileArray[x][y] = "╝";

				} else if (x == 0 || x == this.width + 1) {

					this.tileArray[x][y] = "║";

				} else if (y == 0 || y == this.height + 1) {

					this.tileArray[x][y] = "═══";

				} else {

					this.tileArray[x][y] = " . ";

				}

			}

		}

	}

	/**
	 * Sets a tile at a point in the tile array to a specific character.
	 * 
	 * @param pos A java.awt.Point specifying the tile to change.
	 * @param ch The character to set the tile to.
	 */
	private void setTile(Point pos, char ch) {

		String paddingChar = " ";

		if (pos.x == 0 || pos.x == this.width + 1) {
			paddingChar = "";
		} else if (pos.y == 0 || pos.y == this.width + 1) {
			paddingChar = "═";
		}

		this.tileArray[pos.x][pos.y] = paddingChar + ch + paddingChar;

	}
	
	/**
	 * Draws the room to the console
	 */
	public void draw() {

		for (int y = 0; y < this.height + 2; y++) {
			for (int x = 0; x < this.width + 2; x++) {

				System.out.print(this.tileArray[x][y]);

			}

			System.out.println();

		}

	}

}
