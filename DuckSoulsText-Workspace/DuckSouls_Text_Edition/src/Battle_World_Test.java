//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * 
 * |||REMEMBER|||
 * For animations to work properly, use the console to run the program.
 * If you are just testing, maximizing the eclipse console (or other) will still work.
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 */
public class Battle_World_Test {
	
	private static Duck_Object Player = new Duck_Object();
	private static Enemy_Object Enemy = new Enemy_Object("Rat");
<<<<<<< HEAD
=======
	
>>>>>>> master
	

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		battleLoop();
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> master
	public static void drawSprites() throws FileNotFoundException
	{
		Enemy.getSprite("fight");
		Enemy.getSprite("stand");
		Player.getSprite("stand");
<<<<<<< HEAD
		
		
	}
	public static void battleLoop() throws IOException, InterruptedException {
		
		boolean inBattle;
		inBattle = true;
		
		while(inBattle) {
			drawSprites();
			Utilities.waitMilliseconds(10);
			
			inBattle = Player.playerMove(Enemy);
			if (inBattle) {
				drawSprites();
				Utilities.waitMilliseconds(1000);
				inBattle = Enemy.enemyMove(Player);
				drawSprites();
			}
=======
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
>>>>>>> master
			
			Utilities.clearConsole();
			
		}
<<<<<<< HEAD
				
=======
>>>>>>> master
	}
}
