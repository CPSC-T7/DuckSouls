package controllers;

import entities.Enemy;

/**
 * This interface is used to define the needed methods for a controller object
 * to run the game.
 */
public interface Controller {
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Runs the game.
	 */
	public abstract void mainLoop();
	
	/**
	 * Deals with when the player initiates a battle.
	 * 
	 * @param enemyToBattle
	 *            The enemy object the player has encountered.
	 */
	public abstract void handleBattleEvent(Enemy enemyToBattle);
	
	/**
	 * Deals with when the player initiates a level change.
	 */
	public abstract void handleLevelChangeEvent();
	
	/**
	 * Handles all events in the event queue.
	 */
	public abstract void handleAllEvents();
	
}
