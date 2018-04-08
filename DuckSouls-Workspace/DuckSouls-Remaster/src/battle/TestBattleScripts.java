package battle;

import entities.Player;
import utils.Utilities;
import entities.Enemy;
import entities.Entity;
import battle.PrintBattleText;
import controllers.GameData;

//TODO Keep this incase nadhif fucks up as always

public class TestBattleScripts {
	
	private Player player;
	private Enemy enemy;
	
	/**
	 * Constructor:
	 * Set the player and enemy objects.
	 * 
	 * @param player
	 * 				Player object to set
	 * @param enemy
	 * 				Enemy object to set
	 */
	public TestBattleScripts(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}
	
	/**
	 * Decide if the player will go first.
	 * 
	 * @return	true if the player goes first
	 * 			false if the enemy goes first
	 */
	public boolean playerFirst() {
		
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
	public void executeMove(boolean isPlayer, String move) {
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
					player.run();
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
		
	}// End of executeMove
	
	public boolean isDead(boolean isPlayer) {
		
		//Set the entity to check
		Entity entity;
		if(isPlayer) { 	entity = player;}
		else		 {	entity = enemy;	}
	
		//Return if the entity is dead or not
		if(entity.getHealth() <= 0) {
			return(true);
		}else {
			return(false);	
		}
		
	}//End of checkDeath
	
	public void postBattle() {
		
		//If the player lost
		if (isDead(true)) {
			PrintBattleText.gameOver(GameData.IS_GUI);
			System.exit(0);
		}
		
		//If the player won
		else {
			player.addExperiece(enemy.getExperienceGiven());
			player.setScore(enemy.getScore());
		}
	}
}
