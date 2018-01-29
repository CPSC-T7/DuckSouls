//IOException for use with CMD in Windows
import java.io.IOException;

/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class Battle_World_Test {
	
	private static Duck_Object Player = new Duck_Object();
	private static Enemy_Object Enemy = new Enemy_Object();
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		battleLoop();
	}
	
	public static void drawSprites()
	{
		System.out.print(Enemy.getSprite(Enemy.enemySprite()));
		System.out.print(Player.getSprite(Player.sprite));
		
		
	}
	public static void battleLoop() throws IOException, InterruptedException 
	{
		while(true) {
			drawSprites();
			Thread.sleep(10);
			
			Player.playerMove(Enemy);
			
			Utilities.clearConsole();
			
		}
		
	}

}
