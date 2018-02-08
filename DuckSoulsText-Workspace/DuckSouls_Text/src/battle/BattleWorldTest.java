package battle;

import utils.Utilities;

/**
 * Called when the player walks over an enemy tile, this class prints
 * a battle screen where the player can fight an enemy. The loop will allow
 * the player to attack, heal, defend, etc first, and then the enemy. The loop ends
 * when either the player or enemy dies.
 * 
 * For animations to work properly, use the OS console to run the
 * program (Unless on an Apple device).
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 */
public class BattleWorldTest {
	//Create player and enemy objects.
	private static DuckObject	Player	= new DuckObject();
	private static EnemyObject	Enemy	= new EnemyObject("Rat");
	
	public static void main(String[] args) {
		Utilities.clearConsole();
		battleLoop();
	}
	
	public static void drawSprites() {
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
	}
	
	public static void battleLoop() {
		
		boolean inBattle = true;
		
		while (inBattle) {
			drawSprites();
			Utilities.waitMilliseconds(10);
			
			inBattle = Player.playerMove(Enemy);
			if (inBattle) {
				drawSprites();
				Utilities.waitMilliseconds(1000);
				inBattle = Enemy.enemyMove(Player);
				drawSprites();
			}
		}
	}
	
}
