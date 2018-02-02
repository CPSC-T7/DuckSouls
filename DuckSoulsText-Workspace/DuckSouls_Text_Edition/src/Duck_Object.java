//IOException for use with CMD in Windows
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
	public String sprite = "duck";
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
	 */
	public String getSprite(String duckSprite) 
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
		
		//standing Duck
		String duck_Left = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "       _0 0|\r\n" + 
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --\n";
		
		String duck_Right = yPadding +
				xPadding +  "                __\r\n" + 
				xPadding +  "               |0 0_\r\n" + 
				xPadding +  "               |  --\r\n" + 
				xPadding +  "        \\------|  |\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --\n";
		
		//Duck taunt (Mix with ing duck)
		String taunt_Left = yPadding +
				xPadding +  "          __\r\n" + 
				xPadding +  "        _0 0|\r\n" + 
				xPadding +  "        --  /\r\n" + 
				xPadding +  "        /  /------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --\n";
		
		String taunt_Right = yPadding +
				xPadding +  "               __\r\n" + 
				xPadding +  "              |0 0_\r\n" + 
				xPadding +  "              \\  --\r\n" + 
				xPadding +  "        \\------\\  \\\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --\n";
		
		//Duck taunt (Mix with ing duck)
		String attack_One_Left = yPadding +
				xPadding +  "          __\r\n" + 
				xPadding +  "        _> <|\r\n" + 
				xPadding +  "        --  /\r\n" + 
				xPadding +  "        /  /------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --\n";
		
		String attack_One_Right = yPadding +
				xPadding +  "               __\r\n" + 
				xPadding +  "              |> <_\r\n" + 
				xPadding +  "              \\  --\r\n" + 
				xPadding +  "        \\------\\  \\\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --\n";
		
		//Duck Run (Frame 1)
		String attack_Two_Left = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _> <\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             /|\r\n" + 
				xPadding +  "            `-\n";
		
		//Duck Run Right (Frame 1)
		String attack_Two_Right = yPadding +
				xPadding +  "                  __\r\n" + 
				xPadding +  "                 /> <_\r\n" + 
				xPadding +  "                /   --\r\n" + 
				xPadding +  "       \\-------/   /\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             |\\\r\n" + 
				xPadding +  "              -`\n";
		
		//Duck Quack
		String quack_Left = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "Quak*  _0 0|\r\n" +
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --\n";
		
		String quack_Right = yPadding +
				xPadding +  "                __\r\n" + 
				xPadding +  "               |0 0_  *Quak\r\n" + 
				xPadding +  "               |  --\r\n" + 
				xPadding +  "        \\------|  |\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --\n";	
		
		//Duck Run (Frame 1)
		String run_Left_1 = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             /|\r\n" + 
				xPadding +  "            `-\n";
		
		//Duck Run (Frame 2)
		String run_Left_2 = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             |/\r\n" + 
				xPadding +  "             `\n";
		
		//Duck Run Right (Frame 1)
		String run_Right_1 = yPadding +
				xPadding +  "                  __\r\n" + 
				xPadding +  "                 /0 0_\r\n" + 
				xPadding +  "                /   --\r\n" + 
				xPadding +  "       \\-------/   /\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             |\\\r\n" + 
				xPadding +  "              -`\n";
		
		//Duck Run Right (Frame 2)
		String run_Right_2 = yPadding +
				xPadding +  "                  __\r\n" + 
				xPadding +  "                 /0 0_\r\n" + 
				xPadding +  "                /   --\r\n" + 
				xPadding +  "       \\-------/   /\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             \\|\r\n" + 
				xPadding +  "              `-\n";
		
		//Select the sprite frame to return based on method argument "duckSprite"
		if (duckSprite == "fightBanner") {
			return fightBanner;
		}
		if (direction == "Left") {
			switch(duckSprite) 
			{
				case("duck"):
					return duck_Left;
			
				case("taunt"):
					return taunt_Left;
				
				case("attack1"):
					return attack_One_Left;
				
				case("attack2"):
					return attack_Two_Left;
				
				case("quack"):
					return quack_Left;
				
				case("run_1"):
					return run_Left_1;
				
				case("run_2"):
					return run_Left_2;
				
				default:
					return("Error: no sprite found.");
			}
		}else {
			switch(duckSprite) 
			{
				case("duck"):
					return duck_Right;
			
				case("taunt"):
					return taunt_Right;
				
				case("attack1"):
					return attack_One_Right;
				
				case("attack2"):
					return attack_Two_Right;
				
				case("quack"):
					return quack_Right;
				
				case("run_1"):
					return run_Right_1;
				
				case("run_2"):
					return run_Right_2;
				
				default:
					return("Error: no sprite found.");
			}
		}
	}
	
	/**
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
	}
	
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(Enemy_Object enemy) throws IOException, InterruptedException {
		

		
		Utilities.clearConsole();
		
		System.out.print(getSprite("fightBanner"));
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		System.out.print(getSprite("quack"));
		System.out.println("You quacked at the enemy...");
		Thread.sleep(1000);
		Utilities.clearConsole();
		
		System.out.print(getSprite("fightBanner"));
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		System.out.print(getSprite("duck"));
		System.out.println("You quacked at the enemy...");
		Thread.sleep(500);
		System.out.println("That move did absolutely nothing!");
		Thread.sleep(1500);
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
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("run_1"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("run_2"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}		
		
		System.out.print(getSprite("duck"));
	}
	
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
		
		run(12, +1, 0, enemy);
		peck(enemy);
		run(12, -1, 0, enemy);
		run(0, +1, 0, enemy);
		
		System.out.print("You dealt ");
		System.out.print(Math.round(damage));
		System.out.println(" damage to the enemy!");
		Thread.sleep(2500);
		
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
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("attack1"));
			Thread.sleep(100);
			Utilities.clearConsole();
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("attack2"));
			Thread.sleep(100);
			Utilities.clearConsole();
			
		}
	}
	
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
				
				System.out.print(getSprite("fightBanner"));
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				System.out.print(getSprite("duck"));
				System.out.println("You taunted the enemy...");
				Thread.sleep(100);
				Utilities.clearConsole();
				
				System.out.print(getSprite("fightBanner"));
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				System.out.print(getSprite("taunt"));
				System.out.println("You taunted the enemy...");
				Thread.sleep(100);
				Utilities.clearConsole();
				
		}
		
		System.out.println("The enemy's attack has increased!");
		System.out.println("The enemy's defence has decreased!");
		Thread.sleep(2500);
	}
	
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
