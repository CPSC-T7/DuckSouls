package battle;

import entities.Player;
import items.Consumable;
import utils.Utilities;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;
import controllers.GameData;

/**
 * 
 * @author Wylee McAndrews
 * @author Nadhif Satriana
 *
 */
public class Loop {
	
	
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
		boolean run = false;
		boolean playerFirst = true;
		boolean endsPlayer = true;
		String playerCommand;
		String enemyCommand;
		
		playerFirst = playerFirst(player, enemy);
		if (!playerFirst) {
			endsPlayer = false;
		}
		
		while (!battleEnd) {
			
			playerCommand = player.choice(0);
			if (playerCommand.equals("Item")) {
				playerCommand = player.chooseItem();
			}
			enemyCommand = enemy.choice(0);
			choiceEnd = true;
			
			while (choiceEnd) {
				
				if (playerFirst) {
					run = executeMove(true,  playerCommand,  player,  enemy);
					battleEnd = checkDeath(enemy, false, run);
					playerFirst = false;
					if (battleEnd) {
						choiceEnd = false;
					}
				}
				
				else {
					executeMove(false,  enemyCommand,  player,  enemy);
					battleEnd = checkDeath(player, true, run);
					playerFirst = true;
					if (battleEnd) {
						choiceEnd = false;
					}
				}
				
				if (playerFirst == endsPlayer) {
					choiceEnd = false;
				}
				
			}
			
		}
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
		//TODO: Set GameData to true for GUI (Currently false for testing)
		double healed;
		if(isPlayer) {
			switch (move) {
				case "Attack":
					PrintBattleText.attackingText(true);
					
					double damage = player.sendAttack();
					damage = enemy.receiveAttack(damage);
					
					if (damage == 0) {
						PrintBattleText.missedText(true);
					} else {
						PrintBattleText.damageText(true, damage);
					}
					break;
					
				case "Taunt":
					enemy.taunted();
					PrintBattleText.tauntedText(true);
					break;
					
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
					
				case "Fly":
					enemy.setHealth(0);
					return player.run();
			}
			
		}else {
			
			switch (move) {
				case "Attack":
					
					PrintBattleText.attackingText(false);
					
					double damage = enemy.sendAttack();
					damage = player.receiveAttack(damage);
					
					if (damage == 0) {
						PrintBattleText.missedText(false);
					} else {
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
		if (entity.getHealth() <= 0) {
			if (!run && GameData.IS_GUI) {
				if (isPlayer) {
					PrintBattleText.slainEntity(true);
					entity.setDead(true);
				} else {
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
	 * 					whether or not the player chose the fly command
	 */
	
	public static void postBattle(boolean isPlayer, Player player, Enemy enemy, boolean run) {
		if (isPlayer) {
			PrintBattleText.gameOver();
		}
		else if (run){
			
		}
		else {
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
