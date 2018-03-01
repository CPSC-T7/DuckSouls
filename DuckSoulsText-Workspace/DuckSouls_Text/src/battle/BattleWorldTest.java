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
	private static DuckObject	Player	= new DuckObject(20, 15, 5, 5, 5, 30, 16);
	private static EnemyObject	Enemy	= new EnemyObject("Rat", 10, 15, 5, 5, 5, 29, 16); //Enemy will be more random in later versions
	
	/**
	 * Clear the console on startup and start the battle loop.
	 * 
	 * @param args
	 */
	/*public static void main(String[] args) {
		Utilities.clearConsole();
		battleLoop();
	}*/
	
	/**
	 * Draw the move options ("Fight" banner for now), Enemy, and player in default positions
	 */
	public static void drawSprites() {
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
	}
	
	/**
	 * While the player and enemy are alive, keep the battle going.
	 * The player takes the first move, then if the enemy is still alive
	 * it will move.
	 */
	public static void battleLoop() {
		
		boolean inBattle = true;
		
		while (inBattle) {
			
			//Start the battle in default positions
			drawSprites();
			Utilities.waitMilliseconds(10);
			
			//Tell the player when to make a move
			System.out.print("\nEnter a move: ");
			
			//Player moves, then becomes True if the enemy is still alive or False if the enemy dies
			inBattle = Player.playerMove(Enemy);
			
			if (inBattle) {
				//Wait a second in default positions before the enemy attacks the player
				drawSprites();
				Utilities.waitMilliseconds(1000);
				
				//If the enemy kills the player, the battle will end
				inBattle = Enemy.enemyMove(Player);
			}
		}
	}
}
