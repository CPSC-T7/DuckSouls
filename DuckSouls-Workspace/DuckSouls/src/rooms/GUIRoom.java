package rooms;

import java.awt.Point;

import entities.*;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class represents a GUI-based room in DuckSouls. Each room is a rectangle
 * of a desired width and height, and can hold one item and entity in each inner
 * tile.
 * 
 * @author Matthew Allwright
 * @author Wylee McAndrews
 * @version 2.0
 */
public class GUIRoom extends Room {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	public static final boolean	IS_GUI		= true;
	private static final int	TILE_SIZE	= 64;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public GUIRoom(int size, int enemySpawnChance) {
		
		super(IS_GUI, size, enemySpawnChance);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Generates and appends a tile array to the instance. Makes the tile array 2
	 * units larger in both the x and y direction from the internal size to
	 * accommodate walls. Also automatically fills the edges with the correct wall
	 * tiles.
	 */
	@Override
	protected void genTileArray() {
		
		super.genTileArray();
		
		// For each position...
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				this.tileArray[x][y].updateImage();
				
			}
			
		}
		
	}
	
	/**
	 * Draws the room to the console
	 */
	public void draw(GraphicsContext gc) {
		
		Point position;
		
		// For each position...
		for (int y = 0; y < this.internalHeight + 2; y++) {
			for (int x = 0; x < this.internalWidth + 2; x++) {
				
				// Get the tile position
				position = new Point(x * 64, y * 64);
				
				// Print the tile
				this.tileArray[x][y].drawTile(gc, position);
				
				// Print Entities
				if (entityAt(new Point(x, y)) != null) {
					Entity entity = entityAt(new Point(x, y));
					entity.drawEntity(gc, new Point(x * TILE_SIZE, y * TILE_SIZE));
				}
				
			}
			
		}
		
	}
	
	@Override
	public void placeEntity(Point position, Entity entity) {
		
		super.placeEntity(position, entity);

		if (entity != null)
			this.entityArray[position.x][position.y].updateImage();
		
	}
	
}
