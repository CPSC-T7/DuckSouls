package game;

import java.util.ArrayList;

import items.Item;
import javafx.scene.image.Image;

public interface GameWorld {
	
	public Event runTurn(String input);
	
	public void nextWorld(String next);
	
	public ArrayList<ArrayList<ArrayList<Image>>> getImages(int mapsize);
	
	public ArrayList<ArrayList<String>> getStrings();
	
	public ArrayList<Item> getInventory();
		
}
