//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 * @author Rahmanta Satriana
 */
public class Duck_Object {
	
	//Public Variables

	//Current Sprite
	public String sprite = "stand";
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	public int xPosition = 0;
	public int yPosition = 0;	
	
	
	//Private Variables
	
	//Player Stats, it's fixed for DEMO 1
	final private double HEALTH_POINTS = 20;
	final private double MANA_POINTS = 15;
	final private double ATTACK_POINTS = 5;
	final private double DEFENCE_POINTS = 5;
	final private double SPEED_POINTS = 5;
	final private double ACCURACY_POINTS = 70;
	final private double CRITICAL_HIT_POINTS = 16;
		
		
	private double healthPoints = 20;
	private double manaPoints = 15;
	private double attackPoints = 5;
	private double defencePoints = 5;
	private double speedPoints = 5;
	private double accuracyPoints = 70;
	private double criticalHitPoints = 16;	
	
	private int experience = 0;
	private int money = 0;

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
	 * Gets the required sprite from a text file.
	 * 
	 * @param duckSprite
	 * 					The sprite to print.
	 * @throws FileNotFoundException 
	 */
	
	
	public void getSprite(String duckSprite) throws FileNotFoundException 
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
	public boolean playerMove(Enemy_Object enemy) throws IOException, InterruptedException {
		
		boolean selection;
		selection = true;
		String move = "";
		
		while (selection) {
			move = scanner.nextLine();
			move = move.toLowerCase();
		
			if (move.contains("quack")) {
				quack(enemy);
				selection = false;
			}else if (move.contains("attack")) {
				attack(enemy);
				selection = false;
			}else if (move.contains("taunt")) {
				taunt(enemy);
				selection = false;
			}else if (move.contains("fly")) {
				selection = false;
			}else {
				System.out.println("That is not a valid command.");
			}
		}
		
		boolean inBattle = finishBattle(enemy, move);
		
		return inBattle;
		
	}//End of playerMove
	
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(Enemy_Object enemy) throws IOException, InterruptedException {
		

		
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("quack");
		System.out.println("You quacked at the enemy...");
		Utilities.waitMilliseconds(1000);
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("attack");
		System.out.println("That move did absolutely nothing!");
		Utilities.waitMilliseconds(1000);
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
	public void run(int numTimes, int xDirection, int yDirection, Enemy_Object enemy) throws IOException, InterruptedException 
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
	public void attack(Enemy_Object enemy) throws IOException, InterruptedException {
		
		double damage;
		damage = (attackPoints * 2.5) - enemy.getDefence();
		double enemyHealth = enemy.getHealth();
		double newHealth = enemyHealth - damage;
		enemy.setHealth(Math.round(newHealth));
		
		System.out.println("You attacked the enemy...");
		Thread.sleep(500);
		
		run(13, +1, 0, enemy);
		peck(enemy, 1);
		run(13, -1, 0, enemy);
		run(0, +1, 0, enemy);
		enemy.enemyMove(this ,"taunt");
		
		System.out.print("You dealt ");
		System.out.print(Math.round(damage));
		System.out.println(" damage to the enemy!");
		Thread.sleep(2500);
		
	}//End of attack

	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
public void peck(Enemy_Object enemy, int numTimes) throws IOException, InterruptedException {
		
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
	public void taunt(Enemy_Object enemy) throws IOException, InterruptedException  {
		
		double enemyAttack = enemy.getAttack();
		double enemyDefence = enemy.getDefence();
		enemy.setAttack(enemyAttack + 5);
		enemy.setDefence(enemyDefence - 5);
		
		for (int i = 0; i <= 2; i++) 
		{
			
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("stand");
			getSprite("stand");
			System.out.println("You taunted the enemy...");
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
			getSprite("fight");
			enemy.getSprite("stand");
			getSprite("taunt");
			System.out.println("You taunted the enemy...");
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
				
		}
		
		System.out.println("The enemy's attack has increased!");
		System.out.println("The enemy's defence has decreased!");
		Utilities.waitMilliseconds(2000);
	}//End of taunt
	
	private void resetStats() {
		
		healthPoints = HEALTH_POINTS + 0;
		manaPoints = MANA_POINTS + 0;
		attackPoints = ATTACK_POINTS + 0;
		defencePoints = DEFENCE_POINTS + 0;
		speedPoints = SPEED_POINTS + 0;
		accuracyPoints = ACCURACY_POINTS + 0;
		criticalHitPoints = CRITICAL_HIT_POINTS + 0;
		
	}

	private boolean finishBattle(Enemy_Object enemy, String move) {
		
		double enemyHealth = enemy.getHealth();
		
		if (move.equals("fly")) {
			System.out.println("You flew away from battle...");
			resetStats();
			return false;
		}
		
		else if (enemyHealth <= 0) {
			System.out.println("You have beaten the enemy!");
			resetStats();
			return false;
		}
		
		else {
			return true;
		}
		
		
	}
	
}
