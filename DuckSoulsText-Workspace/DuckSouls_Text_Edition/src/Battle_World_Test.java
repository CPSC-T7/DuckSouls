//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * |||REMEMBER|||
 * For animations to work properly, use the console to run the program.
 * If you are just testing, maximizing the eclipse console (or other) will still work.
 * 
 * @author Wylee McAndrews
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
	
	
	public static void drawSprites() throws FileNotFoundException
	{
		Enemy.getSprite("fight");s
		Enemy.getSprite("stand");
		Player.getSprite("stand");
	}
	
	
	public static void battleLoop() throws IOException, InterruptedException 
	{
		while(true) {
			drawSprites();
			Utilities.waitMilliseconds(10);
			
			Player.playerMove(Enemy);
			drawSprites();
			Utilities.waitMilliseconds(1000);
			Enemy.enemyMove(Player, "attack");
			drawSprites();
			
			Utilities.clearConsole();
			
		}
	}
}
