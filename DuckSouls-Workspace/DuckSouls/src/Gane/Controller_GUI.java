package Gane;

import java.awt.Point;
import java.util.ArrayList;

import battle.BattleWorldTest;
import battle.DuckObject;
import battle.EnemyObject;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import levels.Level;
import story_map.Map;
import tiles.Stairs;
import utils.Orientation;
import utils.Utilities;

public class Controller_GUI extends Application{
	
	private int mapsize = 7;
	private int spritesize = 64;
	private GameWorld world = new Level();
	private Event event = new Event(Event_type.NOEVENT);

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
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
		this.drawMap(gc, world.getImages(mapsize));
		
		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.W) {
				
				//Runs the turn with this input
				event.setEvent(world.runTurn("W"));
				//Redraws the map
				this.drawMap(gc, world.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.A) {
				
				//Runs the turn with this input
				event.setEvent(world.runTurn("A"));
				
				//Redraws the map
				this.drawMap(gc, world.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.S) {
				
				//Runs the turn with this input
				event.setEvent(world.runTurn("S"));
				
				//Redraws the map
				this.drawMap(gc, world.getImages(mapsize));
				
			}
			else if (key.getCode() == KeyCode.D) {
				
				//Runs the turn with this input
				event.setEvent(world.runTurn("D"));
				
				//Redraws the map
				this.drawMap(gc, world.getImages(mapsize));
				
			}
			
			switch(event.getType()) {
				case BATTLE: 
					break;
				case GETITEM:
					break;
				case NEXTWORLD:
					world.nextWorld(event.getNextworld());
					this.drawMap(gc, world.getImages(mapsize));
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
