//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 *
 */
public class Enemy_Object {
	
	/**		Public Variables	*/
	
	// x/y position: Where the enemy is drawn on the screen (0,0 = topmost left)
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
	 * Modifiable with different characteristics (attacks, stats, etc)
	 * 
	 * @param enemy
	 * 			The type of enemy to display (Will affect sprite used & move type)
	 */
	public Enemy_Object(String enemy) {
		enemyType = enemy;
	}
	
	
	/**
	 * Main class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	
	/**
	 * Prints the required sprite from a text file.
	 *  
	 * @param enemySprite
	 * 					The type of sprite to print.
	 * @throws FileNotFoundException 
	 */
	public void getSprite(String enemySprite) throws FileNotFoundException 
	{
		//Update position on screen using newlines and spaces
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		//Select the sprite frame to print based on the argument "enemySprite"
		//Uses 'enemyType' string to decide which folder to choose.
		switch(enemySprite) 
		{
			//Fight banner to be replaced with stats
			case("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");;
				break;
			
			case("stand"):
				Utilities.printSprite(enemyType + "/Stand/stand_" + direction, xPadding, yPadding);
				break;
			
			case("taunt1"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_One_" + direction, xPadding, yPadding);
				break;
				
			case("taunt2"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_Two_" + direction, xPadding, yPadding);
				break;
		
			case("hurt"):
				Utilities.printSprite(enemyType + "/Hurt/hurt_" + direction, xPadding, yPadding);
				break;
			
			case("attack1"):
				Utilities.printSprite(enemyType + "/Attack/attack_One", xPadding, yPadding);
				break;
				
			case("attack2"):
				Utilities.printSprite(enemyType + "/Attack/attack_Two", xPadding, yPadding);
				break;
				
			case("attack3"):
				Utilities.printSprite(enemyType + "/Attack/attack_Three", xPadding, yPadding);
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
		
		if (move.contains("taunt")) {
			taunt(player);
		}else if (move.contains("attack")) {
			attack(player);
		}
		
	}//End of enemyMove
	
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	public void swipe(Duck_Object player) throws IOException, InterruptedException {
		
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack1");
		player.getSprite("stand");
		Utilities.waitMilliseconds(300);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack2");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack3");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
	}//End of swipe
	
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
			
			getSprite("fight");
			getSprite("run_1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("run_2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}
		
	}//End of run
	
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	public void attack(Duck_Object player) throws IOException, InterruptedException 
	{
		run(13, -1, 0, player);
		swipe(player);
		run(13, +1, 0, player);
		run(0, -1, 0, player);
	}//End of attack
	
	/**
	 * 
	 * @param player
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Duck_Object player) throws IOException, InterruptedException
	{
		getSprite("fight");
		getSprite("stand");
		player.getSprite("stand");
		Utilities.waitMilliseconds(400);
		Utilities.clearConsole();
		
		for(int i = 0; i <= 3; i++)
		{	
			getSprite("fight");
			getSprite("taunt1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50	);
			Utilities.clearConsole();
		}
	}
	
}
