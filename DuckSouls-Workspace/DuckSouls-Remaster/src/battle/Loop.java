package battle;

import entities.Player;
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
	
	public static void battleLoop(Player player, Enemy enemy) {
		
		// TODO Create texts saying things that happened during the battle
		
		boolean battleEnd = false;
		boolean choiceEnd = true;
		boolean run = false;
		boolean playerFirst = true;
		boolean endsPlayer = true;
		String playerCommand;
		String enemyCommand;
		
		
		/*
		if (player.getSpeed() >= enemy.getSpeed()) {
			startingPerson = 1;
			reChoice = 1;
		} else {
			startingPerson = 2;
			reChoice = 2;
		}*/
		
		playerFirst = playerFirst(player, enemy);
		if (!playerFirst) {
			endsPlayer = false;
		}
		
		while (!battleEnd) {
			
			playerCommand = player.choice(0, GameData.IS_GUI);
			enemyCommand = enemy.choice(0, GameData.IS_GUI);
			choiceEnd = true;
			
			while (choiceEnd) {
				
				if (playerFirst) {
					run = executeMove(true,  playerCommand,  player,  enemy);
					battleEnd = checkDeath(enemy, false, run);
					playerFirst = false;
					if (!battleEnd) {
						choiceEnd = false;
					}
				}
				
				else {
					executeMove(false,  enemyCommand,  player,  enemy);
					battleEnd = checkDeath(player, true, run);
					playerFirst = true;
					if (!battleEnd) {
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
	 */
	public static boolean executeMove(boolean isPlayer, String move, Player player, Enemy enemy) {
		//TODO: Set GameData to true for GUI (Currently false for testing)
		
		if(isPlayer) {
			switch (move) {
				case "Attack":
					PrintBattleText.attackingText(true, !GameData.IS_GUI);
					
					double damage = player.sendAttack();
					damage = enemy.receiveAttack(damage);
					
					if (damage == 0) {
						PrintBattleText.missedText(true, !GameData.IS_GUI);
					} else {
						PrintBattleText.damageText(true, damage, !GameData.IS_GUI);
					}
					break;
					
				case "Taunt":
					enemy.taunted();
					PrintBattleText.tauntedText(true, !GameData.IS_GUI);
					break;
					
				case "Item":
					// item
					break;
					
				case "Run":
					enemy.setHealth(0);
					return player.run();
			}
			
		}else {
			
			switch (move) {
				case "Attack":
					
					PrintBattleText.attackingText(false, !GameData.IS_GUI);
					
					double damage = enemy.sendAttack();
					damage = player.receiveAttack(damage);
					
					if (damage == 0) {
						PrintBattleText.missedText(false, !GameData.IS_GUI);
					} else {
						PrintBattleText.damageText(false, damage, !GameData.IS_GUI);
					}
					break;
					
				case "Taunt":
					
					player.taunted();
					PrintBattleText.tauntedText(false, !GameData.IS_GUI);
					break;
			}
			
		}
		return false;
		
	}// End of executeMove
	
	/*
	private static boolean runCommand(int command, Player player, Enemy enemy, boolean run) {
		switch (command) {
			case 0:
				PrintBattleText.attackingText(true, GameData.IS_GUI);
				double damage;
				damage = player.sendAttack();
				damage = enemy.receiveAttack(damage);
				if (damage == 0) {
					PrintBattleText.missedText(true, GameData.IS_GUI);
				} else {
					PrintBattleText.damageText(true, damage, GameData.IS_GUI);
				}
				break;
			case 1:
				enemy.taunted();
				PrintBattleText.tauntedText(true, GameData.IS_GUI);
				break;
			case 2:
				// item
				break;
			case 3:
				enemy.setHealth(0);
				return player.run();
		}
		return false;
	}
	
	private static void runCommand(int command, Enemy enemy, Player player) {
		switch (command) {
			case 0:
				PrintBattleText.attackingText(false, GameData.IS_GUI);
				double damage;
				damage = enemy.sendAttack();
				damage = player.receiveAttack(damage);
				if (damage == 0) {
					PrintBattleText.missedText(false, GameData.IS_GUI);
				} else {
					PrintBattleText.damageText(false, damage, GameData.IS_GUI);
				}
				break;
			case 1:
				player.taunted();
				PrintBattleText.tauntedText(false, GameData.IS_GUI);
				break;
		}
	}*/
	
	public static boolean checkDeath(Entity entity, boolean isPlayer, boolean run) {
		if (entity.getHealth() <= 0) {
			if (!run) {
				if (isPlayer) {
					PrintBattleText.slainEntity(true, !GameData.IS_GUI);
				} else {
					PrintBattleText.slainEntity(false, !GameData.IS_GUI);
				}
				
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static void postBattle(boolean isPlayer, Player player, Enemy enemy, boolean run) {
		if (isPlayer) {
			PrintBattleText.gameOver(!GameData.IS_GUI);
			System.exit(0);
		}
		else if (run){
			
		}
		else {
			player.addExperiece(enemy.getExperienceGiven());
			player.setScore(enemy.getScore());
		}
	}
}
