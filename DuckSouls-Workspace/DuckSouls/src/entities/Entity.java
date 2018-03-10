package entities;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Point;
import java.util.ArrayList;

import items.Clothes;
import items.Item;
import items.Unarmed;
import utils.Orientation;
import utils.Utilities;

/**
 * This class is used to represent living entities within DuckSouls. Each entity
 * has a position, string representation, orientation, and inventory.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @author Nadhif Satriana
 * @version 1.4
 */
public class Entity {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */

	private Point position = new Point();
	private String stringRepr = "EER";
	private Orientation orientation = Orientation.SOUTH;
	private ArrayList<Item> inventory = new ArrayList<Item>();

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

	/*
	 * 
	 * METHODS
	 * 
	 */

	/**
	 * Draw the entity to the screen at a position (x,y)
	 * 
	 * @param gc
	 *            Graphics Context
	 * @param position
	 *            Entity Position
	 */
	public void drawEntity(GraphicsContext gc, Point position) {

		gc.drawImage(this.image, position.x, position.y);

	}

	/**
	 * Create a new image depending on the image directory and direction of the
	 * entity.
	 */
	public void updateImage() {

		this.image = new Image("file:///" + Utilities.getParentDir() + this.pathToImage + this.orientation.str + ".png");

	}

	/**
	 * Returns a copy of the entity's string representation.
	 * 
	 * @return A copy of the entity's string representation.
	 */
	public String getStringRepr() {

		return new String(this.stringRepr);

	}

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

}
