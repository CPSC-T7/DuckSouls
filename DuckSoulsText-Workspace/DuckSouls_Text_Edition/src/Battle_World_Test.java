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
	
	//Attack Banner
	static String fightBanner = 
			" +-----------------------------------------------+ \r\n" + 
			"    _______ _________ _______          _________  \r\n" + 
			"   (  ____ \\\\__   __/(  ____ \\+\\     /+\\__   __/  \r\n" + 
			"   + (    \\/   ) (   + (    \\/| )   ( |   ) (     \r\n" + 
			"   | (__       + +   | +      | (___) |   + +     \r\n" + 
			"   |  __)      | |   | | ____ |  ___  |   | |     \r\n" + 
			"   | (         + +   | + \\_  )| (   ) |   | |     \r\n" + 
			"   | )      ___) (___+ (___) ++ )   ( |   + +     \r\n" + 
			"   +/       \\_______/(_______)+/     \\+   )_(     \r\n" + 
			"                                                  \r\n" + 
			" +-----------------------------------------------+ \r\n" + 
			"\n\n";
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		battleLoop();
	}
	
	public static void drawSprites()
	{
		System.out.print(fightBanner);
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
