package controllers;

public class Controller {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static boolean	isGUI;
	private static boolean	isStory;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public void initialize(boolean isGUI, boolean isStory) {
		
		Controller.isGUI = isGUI;
		Controller.isStory = isStory;
		
	}
	
	public void mainLoop() {
		
		if (isGUI) {
			
		} else {
			
		}
		
	}
	
}
