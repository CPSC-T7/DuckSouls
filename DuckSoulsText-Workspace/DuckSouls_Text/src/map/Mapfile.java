package map;

import java.util.ArrayList;

import utils.Utilities;

/**
 * This class represents a map room read from a file.
 * 
 * @see map.Map.java
 * @author Colin Yeung
 */
public class Mapfile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private int								mapWidth;
	private ArrayList<ArrayList<String>>	map_2DArrayList	= new ArrayList<ArrayList<String>>(0);
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new MapFile from the text in a specified file.
	 * 
	 * @param fileName
	 *            The file that contains the data for the map.
	 */
	public Mapfile(String fileName) {
		
		// Read the file containing the map data
		String[] lines = Utilities.readLines(fileName);
		
		// First line indicates the map size
		this.mapWidth = Integer.parseInt(lines[0]);
		
		// For every other line...
		for (int i = 1; i < lines.length; i++) {
			
			// Make a new list of strings that represent the tiles to the map
			map_2DArrayList.add(new ArrayList<String>(mapWidth));
			
			// Fill that list with each tile string from the file
			String[] tileStrings = lines[i].trim().split(":");
			for (String tileString : tileStrings) {
				
				map_2DArrayList.get(i - 1).add(tileString);
				
			}
		}
		
	}// End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Print the MapFile to the console.
	 */
	public void print() {
		
		// For each position in the map array...
		for (int y = 0; y < this.map_2DArrayList.size(); y++) {
			for (int x = 0; x < this.map_2DArrayList.get(y).size(); x++) {
				
				// Print out the character
				System.out.print(this.map_2DArrayList.get(y).get(x) + " ");
				
			}
			
			// Current line printed, wrap to a new line.
			System.out.println();
			
		}
		
	}// End of print
	
	/**
	 * Unlocks the door located at the given position
	 * 
	 * @param x
	 *            The X co-ordinate of the door.
	 * @param y
	 *            The Y co-ordinate of the door.
	 */
	public void unlockDoor(int x, int y) {
		
		// If the tile is actually a door...
		if (this.map_2DArrayList.get(y).get(x).charAt(0) == 'D') {
			
			// If the door is actually locked...
			if (this.map_2DArrayList.get(y).get(x).charAt(1) == 'L') {
				
				// Replace the locked door with an unlocked one
				String unlockedDoor = this.map_2DArrayList.get(x).get(y).replace('L', 'U');
				this.map_2DArrayList.get(y).set(x, unlockedDoor);
				
			}
			
		}
		
	} // End of unlockDoor
	
	/**
	 * Returns the 2D array list of the map.
	 * 
	 * @return 2D array list of the map.
	 */
	/*
	 * TODO: Privacy leak. This returns a reference to a private variable.
	 */
	public ArrayList<ArrayList<String>> getMap() {
		
		return map_2DArrayList;
		
	} // End of getMap
	
	/**
	 * Returns whether the player is in the current map or not.
	 * 
	 * @return A boolean stating whether the player is in the current map or not.
	 */
	public boolean mapContainsPlayer() {
		
		// For each position in the map...
		for (int y = 0; y < this.map_2DArrayList.size(); y++) {
			for (int x = 0; x < this.map_2DArrayList.get(y).size(); x++) {
				
				// Return true if the player is at that position
				if (this.map_2DArrayList.get(y).get(x) == "@") {
					
					return true;
					
				}
				
			}
			
		}
		
		// Otherwise return false
		return false;
		
	}// End of mapContainsPlayer
	
}
