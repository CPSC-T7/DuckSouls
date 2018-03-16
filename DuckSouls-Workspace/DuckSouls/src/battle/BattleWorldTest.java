package battle;

import entities.Entity;
import utils.Utilities;
import items.Item;

/**
 * Called when the player walks over an enemy tile, this class prints a battle
 * screen where the player can fight an enemy. The loop will allow the player to
 * attack, heal, defend, etc first, and then the enemy. The loop ends when
 * either the player or enemy dies.
 * 
 * For animations to work properly, use the OS console to run the program
 * (Unless on an Apple device).
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 */
public class BattleWorldTest {
	
	// Create player and enemy objects.
	// private static DuckObject Player = new DuckObject(20, 15, 5, 5, 5, 78, 16);
	// private static EnemyObject Enemy = new EnemyObject("Rat", 10, 15, 5, 5, 5,
	// 70, 16); //Enemy will be more random in later versions
	
	/**
	 * Clear the console on startup and start the battle loop.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Utilities.clearConsole();
		DuckObject Player = new DuckObject(20, 15, 5, 5, 5, 78, 16, 'T');
 		EnemyObject Enemy = new EnemyObject("Rat", 10, 15, 5, 5, 5, 70, 16, 'T');
		battleLoop(Player, Enemy, null, null);
	}
	
	/**
	 * Draw the move options ("Fight" banner for now), Enemy, and player in default
	 * positions
	 */
	public static void drawSprites(DuckObject Player, EnemyObject Enemy) {
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
	}
	
	/**
	 * While the player and enemy are alive, keep the battle going. The speed
	 * determines who moves first
	 */
	public static void battleLoop(DuckObject Player, EnemyObject Enemy, Item wep, Item arm) {
		
		// System.out.print(wep);
		// System.out.print(arm);
		// Utilities.waitMilliseconds(5000);
		
		// Add stats from weapon and armour before the battle, it gets reset at the end
		if(wep != null) {
		Player.setStats("attackPoints", (Player.getStats("attackPoints") + wep.getExtraStats("attack")));
		Player.setStats("accuracyPoints", (Player.getStats("accuracyPoints") + wep.getExtraStats("accuracy")));
		Player.setStats("speedPoints", (Player.getStats("speedPoints") + wep.getExtraStats("speed")));
		Player.setStats("criticalHitPoints", (Player.getStats("criticalHitPoints") + wep.getExtraStats("critChance")));
		
		Player.setStats("defencePoints", (Player.getStats("defencePoints") + arm.getExtraStats("defense")));
		}
		
		boolean inBattle = true;
		int startingPerson = 1;
		
		if (Player.getStats("speedPointsStatic") >= Enemy.getStats("speedPointsStatic")) {
			startingPerson = 1;
		} else {
			startingPerson = 2;
		}
		
		while (inBattle) {
			
			// Start the battle in default positions, depending on GUI or Text version
			drawSprites(Player, Enemy);
			Utilities.waitMilliseconds(10);
			
			if (startingPerson == 1) {
				inBattle = Player.playerMove(Enemy, "");
				startingPerson = 2;
			} else {
				inBattle = Enemy.enemyMove(Player);
				startingPerson = 1;
			}
			
		}
	}
}