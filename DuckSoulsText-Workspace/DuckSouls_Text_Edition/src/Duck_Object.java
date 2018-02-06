//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class Duck_Object 
{
	
	//Public Variables

	//Current Sprite
	public String sprite = "stand";
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
	

	public static void main(String[] args) 
	{
		
	}
	
	/**
	 * Gets the required sprite from a text file.
	 * 
	 * @param duckSprite
	 * 					The sprite to print.
	 * @throws FileNotFoundException 
	 */
	public void getSprite(String duckSprite) 
	{
		//update x position on screen
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		//Select the sprite frame to return based on method argument "duckSprite"
		switch(duckSprite) 
		{
			case("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");;
				break;
				
			case("stand"):
				Utilities.printSprite("Duck/Stand/duckStand_" + direction, xPadding, yPadding);
				break;
		
			case("taunt"):
				Utilities.printSprite("Duck/Taunt/duckDance_" + direction, xPadding, yPadding);
				break;
				
			case("quack"):
				Utilities.printSprite("Duck/Quack/duckQuack_" + direction, xPadding, yPadding);
				break;
				
			case("attack1"):
				Utilities.printSprite("Duck/Attack/duckAttack_One_" + direction, xPadding, yPadding);
				break;
				
			case("attack2"):
				Utilities.printSprite("Duck/Attack/duckAttack_Two_" + direction, xPadding, yPadding);
				break;
				
			case("hurt"):
				Utilities.printSprite("Duck/Hurt/duckHurt_" + direction, xPadding, yPadding);
				break;
				
			case("dead"):
				Utilities.printSprite("Duck/Hurt/duckDead_" + direction, xPadding, yPadding);
				break;
			
			case("run_1"):
				Utilities.printSprite("Duck/Run/duckRun_One_" + direction, xPadding, yPadding);
				break;
			
			case("run_2"):
				Utilities.printSprite("Duck/Run/duckRun_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
		
	}//End of getSprite
	
	/**
	 * Gets information on which move to make from the user.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void playerMove(Enemy_Object enemy)
	{
		
		String move = scanner.nextLine();
		move = move.toLowerCase();
		
		if (move.contains("quack")) {
			quack(enemy);
		}else if (move.contains("attack")) {
			attack(enemy);
		}else if (move.contains("taunt")) {
			taunt(enemy);
		}
		
	}//End of playerMove
	
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(Enemy_Object enemy)
	{
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("quack");
		Utilities.waitMilliseconds(1000);
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("attack");
		Utilities.clearConsole();
		
	}//End of quack
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void run(int numTimes, int xDirection, int yDirection, Enemy_Object enemy)
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("stand");
			getSprite("run_1");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("stand");
			getSprite("run_2");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}
		
	}//End of run
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void attack(Enemy_Object enemy)
	{	
		run(13, +1, 0, enemy);
		peck(enemy, 1);
		run(13, -1, 0, enemy);
		run(0, +1, 0, enemy);
		enemy.enemyMove(this ,"taunt");
		
	}//End of attack
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void peck(Enemy_Object enemy, int numTimes)
	{
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("stand");
			getSprite("attack1");
			Utilities.waitMilliseconds(200);
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("hurt");
			getSprite("attack2");
			Utilities.waitMilliseconds(400);
			Utilities.clearConsole();
			
		}
		
	}//End of peck
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Enemy_Object enemy)
	{
		
		for (int i = 0; i <= 2; i++) 
		{
			
				Utilities.clearConsole();
				
				getSprite("fight");
				enemy.getSprite("stand");
				getSprite("stand");
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
				getSprite("fight");
				enemy.getSprite("stand");
				getSprite("taunt");
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
		}
		
	}//End of taunt
}
