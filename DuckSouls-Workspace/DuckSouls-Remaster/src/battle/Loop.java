package battle;

import entities.Player;
import entities.Enemy;

public class Loop {

	public static void battleLoop(Player player, Enemy enemy) {
	
		boolean inBattle = true;
		int startingPerson = 1;
		
		if (player.getSpeed() >= enemy.getSpeed()) {
			startingPerson = 1;
		} else {
			startingPerson = 2;
		}
		
		while (inBattle) {			

			if(startingPerson == 1) {
				//TODO: Player's turn to move
				//inBattle = ...moveLoop
			}
			
			else {
				//TODO: Enemy's turn to move
				//inBattle = ...moveLoop
			}			
		}
		player.addExperiece(enemy.getExperienceGiven());
		//TODO: Implement this in a function pls, like aftermath battle:
		player.setScore(enemy.getScore());
		
		
	}
}
