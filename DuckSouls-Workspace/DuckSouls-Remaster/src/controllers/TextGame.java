package controllers;

import java.awt.Point;

import entities.Player;
import utils.GameEventQue;
import world.Level;

public class TextGame {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	private static boolean	isStory;
	
	private static int		levelNum	= 1;
	private static Player	player;
	private static Level	currentLevel;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public void initialize(boolean isStory) {
		
		TextGame.isStory = isStory;
		
		if (TextGame.isStory) {
			
			// TODO: Load Story
			
		} else {
			
			player = new Player(new Point(1, 1));
			currentLevel = new Level(levelNum, player, new Point(0, 0));
			
		}
		
	}
	
	public void mainLoop() {
		
	}
	
	private void handleEvents() {
		
		while (GameEventQue.hasEvent()) {
			
			switch (GameEventQue.handleNextEvent()) {
				
				case BATTLE:
					// TODO: Run Battle
					// currentLevel.currentRoom.removeEnemy(player.getPosition());
					break;
				
				case LEVEL_CHANGE:
					levelNum++;
					currentLevel = new Level(levelNum, player, currentLevel.getCurrentRoomPoint());
					break;
				
			}
			
		}
		
	}
	
}
