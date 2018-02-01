//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * SUPER SUPER SUPER EARLY ENEMY OBJECT:
 * Pretty much a direct copy of the duck sprite.
 * Only used as a placeholder until further enemies and
 * enemy attacks are created.
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 *
 */
public class Enemy_Object {
	
	/**		Public Variables	*/
	
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	public int xPosition = 35;
	public int yPosition = 0;	
	
	/**		Private Variables	*/
	
	private String enemyType;		//The type of enemy (Will be added via a constructor)
	
	// x/y padding: Converts x/y position into spaces/tabs
	private String xPadding = Utilities.multiplyString("  ", xPosition);
	private String yPadding = Utilities.multiplyString("\n", yPosition);
	
	private static String direction = "Left";		//The direction that the sprite is facing
	
	/**
	 * Object constructor:
	 * Define character type when a new Enemy_Object is created. (i.e Rat, Fish, etc)
	 * 
	 * @param enemy
	 * 			The type of enemy to display (Will affect sprite used & move type)
	 */
	public Enemy_Object(String enemy) {
		enemyType = enemy;
	}
	
	
	/**
	 * Calls the class constructor
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	/**
	 * Gets the required sprite from a text file.
	 * 
	 * @param enemySprite
	 * 					The sprite to print.
	 * @throws FileNotFoundException 
	 */
	public void getSprite(String enemySprite) throws FileNotFoundException 
	{
		//update x position on screen
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		//Attack Banner (Will be replaced with stats)
		String fightBanner = 
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
				
		
		//Select the sprite frame to return based on method argument "duckSprite"
		switch(enemySprite) 
		{
			case("fightBanner"):
				System.out.println(fightBanner);
				break;
			
			case("stand"):
				Utilities.printSprite(enemyType + "/Stand/stand_" + direction, xPadding, yPadding);
				break;
		
			case("hurt"):
				Utilities.printSprite(enemyType + "/Hurt/hurt_" + direction, xPadding, yPadding);
				break;
			
			case("attack1"):
				Utilities.printSprite(enemyType + "/Attack/attack_One_" + direction, xPadding, yPadding);
				break;
				
			case("attack2"):
				Utilities.printSprite(enemyType + "/Attack/attack_Two_" + direction, xPadding, yPadding);
				break;
			
			case("run_1"):
				Utilities.printSprite(enemyType + "/Run/run_One_" + direction, xPadding, yPadding);
				break;
			
			case("run_2"):
				Utilities.printSprite(enemyType + "/Run/run_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void enemyMove(Duck_Object player, String move) throws IOException, InterruptedException {
		
		move = move.toLowerCase();
		
		if (move.contains("test")) {
			run(13, -1, 0, player);
			run(13, +1, 0, player);
		}else if (move.contains("example defend")) {
			System.out.println("This would make an enemy defend");
		}
		
	}//End of enemyMove
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void run(int numTimes, int xDirection, int yDirection, Duck_Object player) throws IOException, InterruptedException 
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			getSprite("run_1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(10);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			getSprite("run_2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(10);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}
		
	}//End of run
}
