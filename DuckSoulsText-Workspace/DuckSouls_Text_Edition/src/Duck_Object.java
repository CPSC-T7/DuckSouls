//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class Duck_Object {
	
	//Public Variables

	//Current Sprite
	public String sprite = "duck";
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	public int xPosition = 0;
	public int yPosition = 0;	
	
	
	//Private Variables

	// x/y padding: Converts x/y position into spaces/tabs
	private String xPadding = Utilities.multiplyString("  ", xPosition);
	private String yPadding = Utilities.multiplyString("\n", yPosition);
	//The direction that the sprite is facing
	private static String direction = "Right";
	//Get user input
	private static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * @return sprite
	 */
	public String playerSprite() {
		return sprite;
	}
	
	/**
	 * 
	 * @param duckSprite
	 * @return
	 * @throws FileNotFoundException 
	 */
	public void getSprite(String duckSprite) throws FileNotFoundException 
	{
		//update x position on screen
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		//Better way to store/move sprites?
		
		//Attack Banner
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
		switch(duckSprite) 
		{
			case("fightBanner"):
				System.out.println(fightBanner);
				break;
			case("duck"):
				Utilities.printSprite("Stand/duckStand_" + direction, xPadding, yPadding);
				break;
		
			case("taunt"):
				Utilities.printSprite("Taunt/duckDance_" + direction, xPadding, yPadding);
				break;
			
			case("attack1"):
				Utilities.printSprite("Attack/duckAttack_One_" + direction, xPadding, yPadding);
				break;
				
			case("attack2"):
				Utilities.printSprite("Attack/duckAttack_Two_" + direction, xPadding, yPadding);
				break;
			
			case("quack"):
				Utilities.printSprite("Quack/duckQuack_" + direction, xPadding, yPadding);
				break;
			
			case("run_1"):
				Utilities.printSprite("Run/duckRun_One_" + direction, xPadding, yPadding);
				break;
			
			case("run_2"):
				Utilities.printSprite("Run/duckRun_Two_" + direction, xPadding, yPadding);
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
	public void playerMove(Enemy_Object enemy) throws IOException, InterruptedException {
		
		String move = scanner.nextLine();
		move = move.toLowerCase();
		
		if (move.contains("quack")) {
			quack(enemy);
		}else if (move.contains("attack")) {
			attack(enemy);
		}else if (move.contains("taunt")) {
			taunt(enemy);
		}
	}
	
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(Enemy_Object enemy) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		
		getSprite("fightBanner");
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		getSprite("quack");
		Utilities.waitMilliseconds(1000);
		Utilities.clearConsole();
		
		getSprite("fightBanner");
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		getSprite("duck");
		Utilities.clearConsole();
	}
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void run(int numTimes, int xDirection, int yDirection, Enemy_Object enemy) throws IOException, InterruptedException 
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			getSprite("run_1");
			Utilities.waitMilliseconds(10);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			getSprite("run_2");
			Utilities.waitMilliseconds(10);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}		
	}
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void attack(Enemy_Object enemy) throws IOException, InterruptedException {
		run(13, +1, 0, enemy);
		peck(enemy);
		run(13, -1, 0, enemy);
		run(0, +1, 0, enemy);
		
	}
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void peck(Enemy_Object enemy) throws IOException, InterruptedException {
		
		for (int i = 0; i <= 1; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			getSprite("attack1");
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
			getSprite("fightBanner");
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			getSprite("attack2");
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
		}
	}
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Enemy_Object enemy) throws IOException, InterruptedException 
	{
		
		for (int i = 0; i <= 2; i++) 
		{
			
				Utilities.clearConsole();
				
				getSprite("fightBanner");
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				getSprite("duck");
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
				getSprite("fightBanner");
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				getSprite("taunt");
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
		}
	}
}
