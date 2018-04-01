package battle;

import entities.Player;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;

public class Loop {

	public static void battleLoop(Player player, Enemy enemy, boolean isGUI) {
		
		//TODO Create texts saying things that happened during the battle
	
		boolean inBattle = true;
		int startingPerson = 1;
		int command;
		
		if (player.getSpeed() >= enemy.getSpeed()) {
			startingPerson = 1;
		} else {
			startingPerson = 2;
		}
		
		while (inBattle) {			

			if(startingPerson == 1) {
				command = player.choice(0);
				runCommand(command, startingPerson, player, enemy, isGUI);
				inBattle = checkDeath(enemy);
				startingPerson ++;
			}
			
			else {
				command = enemy.choice(0);
				runCommand(command, startingPerson, player, enemy, isGUI);
				inBattle = checkDeath(player);
				startingPerson --;
			}			
		}
		postBattle(startingPerson, player, enemy);

		
		
	}
	
	private static void runCommand(int command, int startingPerson, Player player, Enemy enemy, boolean isGUI) {
		Entity attacker = player;
		Entity defender = enemy;
		if (startingPerson == 2) {
			attacker = enemy;
			defender = player;
		}
		
		switch (command) {
		case 0:
			double damage = attacker.sendAttack();
			damage = defender.receiveAttack(damage);
			if (isGUI) {
				//TODO: make gui text
			}
			else {
				PrintBattleText.damageText(attacker, damage);
			}
			break;
		case 1:
			defender.taunted();
			break;
		case 2:
			//item
			break;
		case 3:
			//run
			break;
		}
	}
	
	private static boolean checkDeath(Entity entity) {
		if (entity.getHealth() <= 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void postBattle(int startingPerson, Player player, Enemy enemy) {	
		if (startingPerson == 1) {
			System.exit(0);
		}
		else {
			player.addExperiece(enemy.getExperienceGiven());
			player.setScore(enemy.getScore());
		}		
	}
}
