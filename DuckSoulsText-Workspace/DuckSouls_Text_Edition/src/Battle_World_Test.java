//IOException for use with CMD in Windows
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 */
public class Battle_World_Test {
	
	private static Duck_Object Player = new Duck_Object();
	private static Enemy_Object Enemy = new Enemy_Object("Rat");
	

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		battleLoop();
	}
	
	public static void drawSprites()
	{
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
		
		
	}
	public static void battleLoop() throws IOException, InterruptedException {
		
		boolean inBattle;
		inBattle = true;
		
		while(inBattle) {
			drawSprites();
			Utilities.waitMilliseconds(10);
			
			inBattle = Player.playerMove(Enemy);
			drawSprites();
			Utilities.waitMilliseconds(1000);
			Enemy.enemyMove(Player, "attack");
			drawSprites();
			
			Utilities.clearConsole();
			
		}
		
		System.out.println("The battle has ended.");
		
	}

}
