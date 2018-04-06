package battle;

import entities.Player;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;
import controllers.GameData;

public class Loop {
	
	public static void battleLoop(Player player, Enemy enemy) {
		
		// TODO Create texts saying things that happened during the battle
		
		boolean inBattle = true;
		boolean choiceEnd = false;
		boolean run = false;
		int startingPerson = 1;
		int reChoice = 1;
		int playerCommand;
		int enemyCommand;
		
		if (player.getSpeed() >= enemy.getSpeed()) {
			startingPerson = 1;
			reChoice = 1;
		} else {
			startingPerson = 2;
			reChoice = 2;
		}
		
		while (inBattle) {
			
			playerCommand = player.choice(0, GameData.IS_GUI);
			enemyCommand = enemy.choice(0, GameData.IS_GUI);
			
			while (choiceEnd) {
				
				if (startingPerson == 1) {
					run = runCommand(playerCommand, player, enemy, run);
					inBattle = checkDeath(enemy, playerCommand, run);
					startingPerson++;
				}
				
				else {
					runCommand(enemyCommand, enemy, player);
					inBattle = checkDeath(player, playerCommand, run);
					startingPerson--;
				}
				
				if (startingPerson == reChoice) {
					choiceEnd = false;
				}
				
			}
			
		}
		postBattle(startingPerson, player, enemy, run);
		
	}
	
	private static boolean runCommand(int command, Player player, Enemy enemy, boolean run) {
		switch (command) {
			case 0:
				PrintBattleText.attackingText(true, GameData.IS_GUI);
				double damage;
				damage = player.sendAttack();
				damage = player.receiveAttack(damage);
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
	}
	
	private static boolean checkDeath(Entity entity, int startingPerson, boolean run) {
		if (entity.getHealth() <= 0) {
			if (!run) {
				if (startingPerson == 1) {
					PrintBattleText.slainEntity(true, GameData.IS_GUI);
				} else {
					PrintBattleText.slainEntity(false, GameData.IS_GUI);
				}
				
			}
			return false;
		} else {
			return true;
		}
	}
	
	private static void postBattle(int startingPerson, Player player, Enemy enemy, boolean run) {
		if (startingPerson == 1) {
			PrintBattleText.gameOver(GameData.IS_GUI);
			System.exit(0);
		} else {
			player.addExperiece(enemy.getExperienceGiven());
			player.setScore(enemy.getScore());
		}
	}
}
