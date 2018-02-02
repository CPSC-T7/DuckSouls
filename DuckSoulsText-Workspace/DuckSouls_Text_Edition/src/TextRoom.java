import java.awt.Point;

//TODO: Fill in JavaDocs

/**
 * Text-based room class.
 * 
 * @author Matthew Allwright
 * @requires java.awt.Point
 * @version 1.0.0
 *
 */
public class TextRoom {

	// ENUMERATORS

	public static enum Tile {

		// Doors

		DOOR_H(" D ", true), DOOR_V("D", true),

		// Walls

		WALL_H("═══", false), WALL_V("║", false), WALL_TL("╔", false), WALL_TR("╗", false), WALL_BL("╚",
				false), WALL_BR("╝", false),

		// Not-Walls

		PLAYER(" @ ", false), EMPTY(" . ", true), FISH(" F ", true), CURRENCY(" $ ", true);

		private String STR;
		private boolean CAN_WALK_ON;

		Tile(String STR, boolean CAN_WALK_ON) {
			this.STR = STR;
			this.CAN_WALK_ON = CAN_WALK_ON;
		}

	}

	// INSTANCE VARIABLES

	private int width, height;
	private final int DEFAULT_WIDTH = 8, DEFAULT_HEIGHT = 8;
	private Tile[][] tileArray;

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

		this.addDoors(doors);

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

		this.addDoors(doors);

	}

	// METHODS

	/**
	 * Generates and appends a tile array to the instance.
	 */
	private void genTileArray() {

		this.tileArray = new Tile[this.width + 2][this.height + 2];

		for (int x = 0; x < this.width + 2; x++) {
			for (int y = 0; y < this.height + 2; y++) {

				if (x == 0 && y == 0) { // Top Left

					this.tileArray[x][y] = Tile.WALL_TL;

				} else if (x == this.width + 1 && y == 0) { // Top Right

					this.tileArray[x][y] = Tile.WALL_TR;

				} else if (x == 0 && y == this.height + 1) { // Bottom Left

					this.tileArray[x][y] = Tile.WALL_BL;

				} else if (x == this.width + 1 && y == this.height + 1) { // Bottom Right

					this.tileArray[x][y] = Tile.WALL_BR;

				} else if (x == 0 || x == this.width + 1) { // Left & Right Walls

					this.tileArray[x][y] = Tile.WALL_V;

				} else if (y == 0 || y == this.height + 1) { // Top & Bottom Walls

					this.tileArray[x][y] = Tile.WALL_H;

				} else { // Centre Tiles

					this.tileArray[x][y] = Tile.EMPTY;

				}

			}

		}

	}

	/**
	 * Sets a tile at a point in the tile array to a specific character.
	 * 
	 * @param pos
	 *            A java.awt.Point specifying the tile to change.
	 * @param ch
	 *            The character to set the tile to.
	 */
	public void setTile(Point pos, Tile tile) {

		this.tileArray[pos.x][pos.y] = tile;

	}

	/**
	 * Takes an array of points and places a door at every point.
	 * 
	 * @param doors
	 *            The array of points to place doors at.
	 */
	private void addDoors(Point[] doors) {

		for (Point pos : doors) {

			if (pos.x == 0 || pos.x == this.width + 1) {
				this.setTile(pos, Tile.DOOR_V);
			} else {
				this.setTile(pos, Tile.DOOR_H);
			}

		}

	}

	/**
	 * Draws the room to the console
	 */
	public void draw() {

		for (int y = 0; y < this.height + 2; y++) {
			for (int x = 0; x < this.width + 2; x++) {

				System.out.print(this.tileArray[x][y].STR);

			}

			System.out.println();

		}

	}

	/**
	 * Gets the tile at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The tile at pos.
	 */
	public Tile tileAt(Point pos) {
		return this.tileArray[pos.x][pos.y];
	}

	/**
	 * Moves a tile from one point to another, throwing a tantrum, I mean error, if
	 * the ending tile cannot be moved to (walls or player).
	 * 
	 * @param toMove
	 *            Point of the tile that should be moved.
	 * @param moveTo
	 *            Point of the tile that will be moved to.
	 */
	public void moveTile(Point toMove, Point moveTo) {

		Tile temp = this.tileAt(toMove), landedOnTile = this.tileAt(moveTo);

		if (this.tileAt(moveTo).CAN_WALK_ON) {
			this.setTile(toMove, Tile.EMPTY);
			this.setTile(moveTo, temp);

			// TODO: Add step-on methods. https://www.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html <- Section 2.2
			
		} else {
			throw new Error("Cannot move tile " + this.tileAt(toMove).toString() + " from point " + toMove.toString()
					+ " to point " + moveTo.toString() + ". There's a " + this.tileAt(moveTo).toString() + " there!");
		}

	}

}
