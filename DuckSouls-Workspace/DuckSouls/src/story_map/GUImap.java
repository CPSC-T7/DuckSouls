package story_map;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

public class GUImap {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Map t1 = new Map();
	private int mapsize = 7;
	private int spritesize = 64;
	
	/*
	 * 
	 * CONSTRUCTORS 
	 * 
	 */
	
	
	public GUImap() {
		t1.initalization(0,2);
	}
	
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void mainloop(GraphicsContext gc, Scene scene) {
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		
		//Draws the first iteration of the room
		this.drawMap(gc, t1.getImages(mapsize));
		
		
		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.W) {
				
				//Runs the turn with this input
				t1.runTurn("w");
				
				//Redraws the map
				this.drawMap(gc, t1.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.A) {
				
				//Runs the turn with this input
				t1.runTurn("a");
				
				//Redraws the map
				this.drawMap(gc, t1.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.S) {
				
				//Runs the turn with this input
				t1.runTurn("s");
				
				//Redraws the map
				this.drawMap(gc, t1.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.D) {
				
				//Runs the turn with this input
				t1.runTurn("d");
				
				//Redraws the map
				this.drawMap(gc, t1.getImages(mapsize));
				
			}
			
		});	

	} // End of mainloop
	
	/**
	 * Draw the sprite to the screen at a position (x,y)
	 * 
	 * @param gc
	 * 			Graphics Context
	 * @param position
	 * 			Entity Position
	 * @param image
	 * 			the path for the image to be drawn
	 *
	 */
	public void drawSprite(GraphicsContext gc, int[] position, Image image) {
		
		gc.drawImage(image, position[0], position[1]);
		
	}//End of drawSprite
	
	
	/**
	 * Draw all the sprite in the map to the screen at a position (x,y)
	 * 
	 * @param gc
	 * 			Graphics Context
	 * @param Map
	 * 			A 3D arraylist containing Strings for the paths to sprites

	 *
	 */
	public void drawMap(GraphicsContext gc, ArrayList<ArrayList<ArrayList<Image>>> Map) {
		
		//For each column...
		for(int y = 0; y<Map.size(); y++) {
			
			//For each row...
			for(int x = 0; x<Map.get(y).size(); x++) {
				
				//Set the current position to that row and column
				int[] position = new int[] {x*64, y*64};
				
				
				//Draw each sprite in that row and column on top of each other
				for(int i = 0; i<Map.get(y).get(x).size(); i++) {
					this.drawSprite(gc, position, Map.get(y).get(x).get(i));
				}
			}
		}
	}// End of drawMap

}