package battle;

import entities.Enemy;
import entities.Player;

public class BattleLoop {

	public static void battleLoop(Player player, Enemy enemy, boolean isGUI) {
		
		boolean inBattle = true;
		boolean choiceEnd = false;
		boolean run = false;
		int startingPerson = 1;
		int reChoice = 2;
		int playerCommand;
		int enemyCommand;
		
		if (player.getSpeed() >= enemy.getSpeed()) {
			startingPerson = 1;
		} else {
			startingPerson = 2;
		}
		
		while (inBattle) {
			
			playerCommand = player.choice(0, isGUI);
			enemyCommand = enemy.choice(0, isGUI);
			
			while (choiceEnd) {
				
				
				
				
			}
			
			
		}
		
		
		
		
		
	}
	
}
