package battle;

import entities.Player;
import items.Consumable;
import utils.Utilities;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;
import controllers.GameData;

/**
 * Handle all of the battle stats and logic
 * 
 * @author Wylee McAndrews
 * @author Nadhif Satriana
 *
 */
public class BattleLoop {
	
	
	/**
	 * A battle loop for the text version
	 * 
	 * @param player
	 * 					the player entity
	 * @param enemy
	 * 					the enemy entity
	 */
	
	public static void battleLoop(Player player, Enemy enemy) {
				
		boolean battleEnd = false;
		boolean choiceEnd = true;
		boolean run = false;		// If the player decides to run
		boolean playerFirst = true;
		boolean endsPlayer = true;  // If the player has the last move
		String playerCommand;
		String enemyCommand;
		
		// Decide who goes first
		playerFirst = playerFirst(player, enemy);
		if (!playerFirst) {
			endsPlayer = false;
		}
		
		// While the battle is running
		while (!battleEnd) {
			// Get the players move
			playerCommand = player.choice(0);
			if (playerCommand.equals("Item")) {
				playerCommand = player.chooseItem();
			}
			// Get the enemy move
			enemyCommand = enemy.choice(0);
			choiceEnd = true;
			
			// After the moves are chosen, run them
			while (choiceEnd) {
				// Run the player's move, then the enemie's
				if (playerFirst) {
					run = executeMove(true,  playerCommand,  player,  enemy);
					battleEnd = checkDeath(enemy, false, run);
					playerFirst = false;
					if (battleEnd) {
						choiceEnd = false;
					}
				}
				// Run the enemie's move, then the player's
				else {
					executeMove(false,  enemyCommand,  player,  enemy);
					battleEnd = checkDeath(player, true, run);
					playerFirst = true;
					if (battleEnd) {
						choiceEnd = false;
					}
				}
				// Choose the next set of moves
				if (playerFirst == endsPlayer) {
					choiceEnd = false;
				}
				
			}
			
		}
		// End the battle
		postBattle(playerFirst, player, enemy, run);
		Utilities.waitMilliseconds(2000);
		
	}
	
	/**
	 * Decide if the player will go first.
	 * 
	 * @param player
	 * 					the player entity
	 * @param enemy
	 * 					the enemy entity
	 * 
	 * @return	true if the player goes first
	 * 			false if the enemy goes first
	 */
	public static boolean playerFirst(Player player, Enemy enemy) {
		
		if (player.getSpeed() >= enemy.getSpeed()) {
			return true;
		} else {
			return false;
		}
		
	}// End of playerFirst
	
	
	/**
	 * Execute a player or enemy move.
	 * 
	 * @param isPlayer
	 * 					If the entity moving is the player
	 * @param move
	 * 					The move-type to execute
	 * @param player
	 * 					the player entity
	 * @param enemy
	 * 					the enemy entity
	 */
	public static boolean executeMove(boolean isPlayer, String move, Player player, Enemy enemy) {

		double healed; // If the player has healed or not
		// Do the player's move
		if(isPlayer) {
			switch (move) {
				case "Attack":
					PrintBattleText.attackingText(true);
					
					double damage = player.sendAttack();
					damage = enemy.receiveAttack(damage);
					
					if (damage == 0) { 	// If the attack missed
						PrintBattleText.missedText(true);
					} else {			// If the attack hit
						PrintBattleText.damageText(true, damage);
					}
					break;
					
				case "Taunt":
					enemy.taunted();
					PrintBattleText.tauntedText(true);
					break;
					
				case "Fly":
					enemy.setHealth(0);
					return player.run();
					
				/* 
				 * The rest of these cases heal the player
				 * depending on the item they choose.
				 * 
				 */	
				case "Crouton":
					healed = player.useConsumable(Consumable.CROUTON);
					PrintBattleText.itemText(Consumable.CROUTON, healed);
					break;
				
				case "Goo":
					healed = player.useConsumable(Consumable.GOO);
					PrintBattleText.itemText(Consumable.GOO, healed);
					break;

				case "Fish":
					healed = player.useConsumable(Consumable.FISH);
					PrintBattleText.itemText(Consumable.FISH, healed);
					break;

				case "Bugs":
					healed = player.useConsumable(Consumable.BUGS);
					PrintBattleText.itemText(Consumable.BUGS, healed);
					break;	

			}
			
		}else { // Do the enemy move
			
			switch (move) {
				case "Attack":
					
					PrintBattleText.attackingText(false);
					
					double damage = enemy.sendAttack();
					damage = player.receiveAttack(damage);
					
					if (damage == 0) { 	// If the enemy misses
						PrintBattleText.missedText(false);
					} else {			// If the enemy hits
						PrintBattleText.damageText(false, damage);
					}
					break;
					
				case "Taunt":
					
					player.taunted();
					PrintBattleText.tauntedText(false);
					break;
			}
			
		}
		return false;
		
	}// End of executeMove
	
	/**
	 * Checks if one of the entities have died
	 * 
	 * @param entity
	 * 				whether it is the player or the enemy entity
	 * @param isPlayer
	 * 				check which entity is being checked for death
	 * @param run
	 * 				whether the player chose the fly command
	 * @return
	 * 				returns a boolean that ends the battle loop
	 */
	public static boolean checkDeath(Entity entity, boolean isPlayer, boolean run) {
		// If the entity health is less than zero
		if (entity.getHealth() <= 0) {
			if (!run && GameData.IS_GUI) {
				if (isPlayer) { // Print player death
					PrintBattleText.slainEntity(true);
					entity.setDead(true);
				} else {		// Print enemy death
					PrintBattleText.slainEntity(false);
				}
			}
			return true;
		} else {
			return false;
		}
	}//End of checkDeath
	
	
	/**
	 * Evaluate the end of the battle, including whether to exit the game
	 * or add xp and score for the player and resetting their stats
	 * 
	 * @param isPlayer
	 * 					check if this is run after the player's or enemy's death
	 * @param player
	 * 					the player entity
	 * @param enemy
	 * 					the enemy entity
	 * @param run
	 * 					whether or not the player chose the fly command to exit battle
	 */
	
	public static void postBattle(boolean isPlayer, Player player, Enemy enemy, boolean run) {
		if (isPlayer) {
			PrintBattleText.gameOver();
		}
		else if (run){
			
		}
		else { // Add to the player stats
			player.setStatsForLevel();
			int currentLevel = player.getLevel() + 0;
			PrintBattleText.xpGain(enemy);
			player.addExperiece(enemy.getExperienceGiven());
			if (currentLevel != player.getLevel()) {
				PrintBattleText.levelUp(player);
			}

			player.addScore(enemy.getScoreGiven());
		}
	}//End of postBattle
}
