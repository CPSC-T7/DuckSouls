package battle;

import entities.Player;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;

public class Loop {
	

	public static void battleLoop(Player player, Enemy enemy, boolean isGUI) {
		
		//TODO Create texts saying things that happened during the battle
	
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
			
			playerCommand = player.choice(0, isGUI);
			enemyCommand = enemy.choice(0, isGUI);
			
			while (choiceEnd) {
				
				if(startingPerson == 1) {
					run = runCommand(playerCommand, player, enemy, isGUI, run);
					inBattle = checkDeath(enemy, playerCommand, isGUI, run);
					startingPerson ++;
				}
				
				else {
					runCommand(enemyCommand, enemy, player, isGUI);
					inBattle = checkDeath(player, playerCommand, isGUI, run);
					startingPerson --;
				}
				
				if (startingPerson == reChoice) {
					choiceEnd = false;
				}
				
			}

		
		}
		postBattle(startingPerson, player, enemy, isGUI, run);
		
	}
	
	private static boolean runCommand(int command, Player player, Enemy enemy, boolean isGUI, boolean run) {
		switch (command) {
		case 0:
			PrintBattleText.attackingText(true, isGUI);
			double damage;
			damage = player.sendAttack();
			damage = player.receiveAttack(damage);
			if (damage == 0) {
				PrintBattleText.missedText(true, isGUI);
			}
			else {
				PrintBattleText.damageText(true, damage, isGUI);
			}
			break;
		case 1:
			enemy.taunted();
			PrintBattleText.tauntedText(true, isGUI);
			break;
		case 2:
			//item
			break;
		case 3:
			enemy.setHealth(0);
			return player.run();
		}
		return false;
	}
	
	private static void runCommand(int command, Enemy enemy, Player player,  boolean isGUI) {
		switch (command) {
		case 0:
			PrintBattleText.attackingText(false, isGUI);
			double damage;
			damage = enemy.sendAttack();
			damage = player.receiveAttack(damage);
			if (damage == 0) {
				PrintBattleText.missedText(false, isGUI);
			}
			else {
				PrintBattleText.damageText(false, damage, isGUI);
			}
			break;
		case 1:
			player.taunted();
			PrintBattleText.tauntedText(false, isGUI);
			break;
		}
	}
	
	private static boolean checkDeath(Entity entity, int startingPerson, boolean isGUI, boolean run) {
		if (entity.getHealth() <= 0) {
			if (!run) {
				if (startingPerson == 1) {
					PrintBattleText.slainEntity(true, isGUI);
				}
				else {
					PrintBattleText.slainEntity(false, isGUI);
				}

			}
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void postBattle(int startingPerson, Player player, Enemy enemy,
			boolean isGUI, boolean run) {	
		if (startingPerson == 1) {
			PrintBattleText.gameOver(isGUI);
			System.exit(0);
		}
		else {
			player.addExperiece(enemy.getExperienceGiven());
			player.setScore(enemy.getScore());
		}		
	}
}
