package battle;

//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;

import utils.Utilities;

import java.io.FileNotFoundException;

/**
 * 
 * |||REMEMBER||| For animations to work properly, use the console to run the
 * program. If you are just testing, maximizing the eclipse console (or other)
 * will still work.
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 */
public class BattleWorldTest {
	
	private static DuckObject	Player	= new DuckObject();
	private static EnemyObject	Enemy	= new EnemyObject("Rat");
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Utilities.clearConsole();
		battleLoop();
	}
	
	public static void drawSprites() throws FileNotFoundException {
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
	}
	
	public static void battleLoop() throws IOException, InterruptedException {
		
		boolean inBattle;
		inBattle = true;
		
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
