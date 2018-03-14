package entities;

import java.awt.Point;
import java.util.ArrayList;

import items.Clothes;
import items.Item;
import items.Unarmed;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tiles.Tile;
import utils.Orientation;
import utils.Utilities;

public class Entity {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Point			position				= new Point();
	private Orientation     orientation = Orientation.SOUTH;
	private String			stringRepr				= "EER";
	private int				ID;
	private ArrayList<Item>  inventory = new ArrayList<Item>();
	

	private Item weapon = new Unarmed();
	private Item armour = new Clothes();

	private Image image;
	private String pathToImage;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new character at a defined position.
	 * 
	 * @param x
	 *            The X co-ordinate of the character.
	 * @param y
	 *            The Y co-ordinate of the character.
	 * @param isPlayer
	 *            Whether the character is the player or not.
	 */
	public Entity(int x, int y, String stringRepr, String pathToImage) {
		
		// Set the character characteristics
		this.position.setLocation(x, y);
		
		// Set the appropriate string representation
		this.stringRepr = stringRepr;
		this.pathToImage = pathToImage;
		
	} // End of constructor
	
	public Entity(int x, int y, int ID, String stringRepr, String pathToImage) {
		
		// Set the character characteristics
		this.position.setLocation(x, y);

		this.ID = ID;
		
		// Set the appropriate string representation
		this.stringRepr = stringRepr;
		this.pathToImage = pathToImage;
		
	} // End of constructor
	
	/**
	 * Creates an entity.
	 * 
	 * @param stringRepr
	 *            The 3-character string used to print the entity.
	 */
	protected Entity(String stringRepr, String pathToImage) {

		this.stringRepr = stringRepr;
		this.pathToImage = pathToImage;
		// this.updateImage();

	}
	
	/**
	 * Moves the character to a specified position. <br>
	 * TODO: Swap Y and X. X should always come first when imputing co-ords!
	 * 
	 * @param y
	 *            The Y co-ordinate to move to.
	 * @param x
	 *            The X co-ordinate to move to.
	 * @param mapdata
	 *            The array list of tiles of the map the character should move in.
	 */
	public void move(int x, int y, ArrayList<ArrayList<Tile>> mapdata) {
		
		// Move the character
		// this.position.setLocation(x, y);
		
		// If the specific point can be moved to...
		if (mapdata.get(y).get(x).getCanWalkOn()) {
			
			// Move the character
			this.position.setLocation(x, y);
			
		}
		
	} // End of move
	
	/**
	 * Returns the X co-ordinate of the player.
	 * 
	 * @return The X co-ordinate of the player.
	 */
	public int getX() {
		
		return this.position.x;
		
	} // End of getX
	
	/**
	 * Returns the Y co-ordinate of the player.
	 * 
	 * @return The Y co-ordinate of the player.
	 */
	public int getY() {
		
		return this.position.y;
		
	} // End of getY
	
	/**
	 * Returns a copy of the entity's position.
	 * 
	 * @return A copy of the entity's position.
	 */
	public Point getPosition() {

		return new Point(this.position);

	}
	
	/**
	 * Returns the orientation of the entity.
	 * 
	 * @return The orientation of the entity.
	 */
	public Orientation getOrientation() {

		return this.orientation;

	}
	
	/**
	 * Sets the orientation of the entity.
	 * 
	 * @return The orientation of the entity.
	 */
	public void setOrientation(Orientation orientation) {

		this.orientation = orientation;

	}
	
	/**
	 * Adds an item to the entity's inventory.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public void addToInventory(Item item) {

		this.inventory.add(item);

	}

	/**
	 * Returns a copy of the entity's inventory.
	 * 
	 * @return A copy of the entity's inventory.
	 */
	public ArrayList<Item> getInventory() {

		return new ArrayList<Item>(this.inventory);

	}

	/**
	 * Returns the weapon of the entity.
	 * 
	 * @return the weapon of the entity.
	 */

	public Item getWeapon() {
		return this.weapon;
	}

	/**
	 * Returns the armour of the entity.
	 * 
	 * @return the armour of the entity.
	 */

	public Item getArmour() {
		return this.armour;
	}

	/**
	 * Replaces the weapon of the entity
	 * 
	 * @param newWeapon
	 *            The new weapon to be stored
	 */

	public void setWeapon(Item newWeapon) {
		this.weapon = newWeapon;
	}

	/**
	 * Replaces the armour of the entity
	 * 
	 * @param newArmour
	 *            The new armour to be stored
	 */

	public void setArmour(Item newArmour) {
		this.armour = newArmour;
	}
	
	/**
	 * Returns whether the character is next to a specific position.
	 * 
	 * @param x
	 *            The X co-ordinate of the position to check.
	 * @param y
	 *            The Y co-ordinate of the position to check.
	 * @return Whether the character is next to the position.
	 */
	public boolean isNextTo(int x, int y) {
		
		// Return whether the position is less than or equal to 1 tile away in x & y
		return (Math.abs(this.position.y - y) <= 1) && (Math.abs(this.position.x - x) <= 1);
		
	} // End of isNextTo
	
	/**
	 * Sets an entity's id
	 * 
	 * @param Id
	 *            The int ID to give to the entity
	 * 
	 */
	public void setID(int Id) {
		
		this.ID = Id;
		
	}// End of setID
	
	/**
	 * Returns an entity's int id
	 * 
	 * @return An int representing the entity's id
	 * 
	 */
	public int getID() {
		
		return this.ID;
		
	}// End of getID
	
	/**
	 * Returns a copy of the entity's string representation.
	 * 
	 * @return A copy of the entity's string representation.
	 */
	public String getStringRepr() {

		return new String(this.stringRepr);

	}
	
	/**
	 * Returns the path to the image file corresponding with the entity's current
	 * state
	 * 
	 * @return the String corresponding to the path to the entity sprite (default
	 *         rat sprite)
	 * 
	 */
	public Image getImage() {
		return new Image("file:///" + Utilities.getParentDir() + this.pathToImage + this.orientation.str + ".png");
		
	}// End of getImage
	
	/**
	 * Draw the entity to the screen at a position (x,y)
	 * 
	 * @param gc
	 *            Graphics Context
	 * @param position
	 *            Entity Position
	 */
	public void drawEntity(GraphicsContext gc, Point position) {
		this.updateImage();
		gc.drawImage(this.image, position.x, position.y);

	}

	/**
	 * Create a new image depending on the image directory and direction of the
	 * entity.
	 */
	public void updateImage() {

		this.image = new Image("file:///" + Utilities.getParentDir() + this.pathToImage + this.orientation.str + ".png");

	}
	
	
}
