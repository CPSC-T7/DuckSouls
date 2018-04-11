package world;

import java.awt.Point;

import entities.Player;

public class LevelBuilder {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static Level buildArcadeLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return new Level(levelNum, player, currentRoomPoint);
		
	}
	
	public static Level buildStoryLevel(int levelNum, Player player, Point currentRoomPoint) {
		
		return LevelIO.loadLevelFromDir("../Levels/Story", levelNum, player, currentRoomPoint);
		
	}
	
}
