package controllers;

import entities.Enemy;

public interface Controller {
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	public abstract void mainLoop();
	
	public abstract void handleBattleEvent(Enemy enemyToBattle);
	
	public abstract void handleLevelChangeEvent();
	
	public abstract void handleAllEvents();
	
}
