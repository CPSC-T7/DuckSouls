package map;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

public class GUImap {

	private Map t1 = new Map();
	private int mapsize = 7;
	private int spritesize = 64;
	
	public GUImap() {
		t1.initalization(0,2);
	}
	
	public void mainloop(GraphicsContext gc, Scene scene) {
		this.drawMap(gc, t1.getImages(mapsize));
		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.W) {
				t1.runTurn("w");
				this.drawMap(gc, t1.getImages(mapsize));
			}
			else if (key.getCode() == KeyCode.A) {
				t1.runTurn("a");
				this.drawMap(gc, t1.getImages(mapsize));
			}
			else if (key.getCode() == KeyCode.S) {
				t1.runTurn("s");
				this.drawMap(gc, t1.getImages(mapsize));
			}
			else if (key.getCode() == KeyCode.D) {
				t1.runTurn("d");
				this.drawMap(gc, t1.getImages(mapsize));
			}
			
		});	
		
		


	}
	
	public void drawSprite(GraphicsContext gc, int[] position, String image) {
		Image IMAGE = new Image(image);
		gc.drawImage(IMAGE, position[0], position[1]);
		
	}
	
	public void drawMap(GraphicsContext gc, ArrayList<ArrayList<ArrayList<String>>> Map) {
		for(int y = 0; y<Map.size(); y++) {
			for(int x = 0; x<Map.get(y).size(); x++) {
				
				int[] position = new int[] {x*64, y*64};
				
				for(int i = 0; i<Map.get(y).get(x).size(); i++) {
					this.drawSprite(gc, position, Map.get(y).get(x).get(i));
				}
			}
		}
	}

}
