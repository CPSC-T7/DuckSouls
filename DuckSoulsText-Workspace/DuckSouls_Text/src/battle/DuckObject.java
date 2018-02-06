package battle;

//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import utils.Utilities;

import java.util.Random;

/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 * @author Rahmanta Satriana
 */
public class DuckObject {
	
	// Public Variables
	
	// Current Sprite
	public String			sprite				= "stand";
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	public int				xPosition			= 0;
	public int				yPosition			= 0;
	
	// Private Variables
	
	// Player Stats, it's fixed for DEMO 1
	// Stats for resetting (actual stats)
	private double			HEALTH_POINTS		= 20;
	private double			MANA_POINTS			= 15;
	private double			ATTACK_POINTS		= 5;
	private double			DEFENCE_POINTS		= 5;
	private double			SPEED_POINTS		= 5;
	private double			ACCURACY_POINTS		= 70;
	private double			CRITICAL_HIT_POINTS	= 16;
	
	// Stats used in battle
	private double			healthPoints		= 20;
	private double			manaPoints			= 15;
	private double			attackPoints		= 5;
	private double			defencePoints		= 5;
	private double			speedPoints			= 5;
	private double			accuracyPoints		= 70;
	private double			criticalHitPoints	= 16;
	
	private int				level				= 1;
	private int				experience			= 0;
	private int				money				= 0;
	
	// x/y padding: Converts x/y position into spaces/tabs
	private String			xPadding			= Utilities.multiplyString("  ", xPosition);
	private String			yPadding			= Utilities.multiplyString("\n", yPosition);
	// The direction that the sprite is facing
	private static String	direction			= "Right";
	// Get user input
	private static Scanner	scanner				= new Scanner(System.in);
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * Gets the required sprite from a text file.
	 * 
	 * @param duckSprite
	 *            The sprite to print.
	 * @throws FileNotFoundException
	 */
	
	public void getSprite(String duckSprite) {
		// update x position on screen
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		// Select the sprite frame to return based on method argument "duckSprite"
		switch (duckSprite) {
			case ("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");
				;
				break;
			
			case ("stand"):
				Utilities.printSprite("Duck/Stand/duckStand_" + direction, xPadding, yPadding);
				break;
			
			case ("taunt"):
				Utilities.printSprite("Duck/Taunt/duckDance_" + direction, xPadding, yPadding);
				break;
			
			case ("quack"):
				Utilities.printSprite("Duck/Quack/duckQuack_" + direction, xPadding, yPadding);
				break;
			
			case ("attack1"):
				Utilities.printSprite("Duck/Attack/duckAttack_One_" + direction, xPadding, yPadding);
				break;
			
			case ("attack2"):
				Utilities.printSprite("Duck/Attack/duckAttack_Two_" + direction, xPadding, yPadding);
				break;
			
			case ("hurt"):
				Utilities.printSprite("Duck/Hurt/duckHurt_" + direction, xPadding, yPadding);
				break;
			
			case ("dead"):
				Utilities.printSprite("Duck/Dead/duckDead_" + direction, xPadding, yPadding);
				break;
			
			case ("run_1"):
				Utilities.printSprite("Duck/Run/duckRun_One_" + direction, xPadding, yPadding);
				break;
			
			case ("run_2"):
				Utilities.printSprite("Duck/Run/duckRun_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
		
	}// End of getSprite
	
	/**
	 * Gets information on which move to make from the user. <<<<<<< HEAD
	 * 
	 * @param enemy
	 *            ======= >>>>>>> master
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public boolean playerMove(EnemyObject enemy) throws IOException, InterruptedException {
		
		boolean selection;
		selection = true;
		String move = "";
		
		while (selection) {
			move = scanner.nextLine();
			move = move.toLowerCase();
			
			if (move.contains("quack")) {
				quack(enemy);
				selection = false;
			} else if (move.contains("attack")) {
				attack(enemy);
				selection = false;
			} else if (move.contains("taunt")) {
				taunt(enemy);
				selection = false;
			} else if (move.contains("fly")) {
				selection = false;
			} else {
				System.out.println("That is not a valid command.");
			}
		}
		
		boolean inBattle = finishBattle(enemy, move);
		
		return inBattle;
		
	}// End of playerMove
	
	/**
	 * 
	 * @param enemy
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(EnemyObject enemy) throws IOException, InterruptedException {
		
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
	}// End of quack
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void run(int numTimes, int xDirection, int yDirection, EnemyObject enemy) {
		if (xDirection == 1) {
			direction = "Right";
		} else {
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
		
	}// End of run
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void attack(EnemyObject enemy) throws IOException, InterruptedException {
		
		Random rand = new Random();
		int accuracyChance = rand.nextInt(100) + 1;
		int criticalHitChance = rand.nextInt(100) + 1;
		boolean landed = true;
		boolean critical = true;
		
		if (accuracyChance <= accuracyPoints) {
			landed = true;
		} else {
			landed = false;
		}
		
		if (criticalHitChance <= criticalHitPoints) {
			critical = true;
		} else {
			critical = false;
		}
		
		double damage;
		damage = (attackPoints * 2.5) - enemy.getDefence();
		double enemyHealth = enemy.getHealth();
		if (critical) {
			damage = damage * 1.5;
		}
		if (landed) {
			double newHealth = enemyHealth - damage;
			enemy.setHealth(Math.round(newHealth));
		}
		
		System.out.println("You attacked the enemy...");
		Utilities.waitMilliseconds(500);
		
	}
	
	/**
	 * 
	 * @param enemy
	 * @param numTimes
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void peck(EnemyObject enemy, int numTimes) {
		
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
		
	}// End of peck
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(EnemyObject enemy) throws IOException, InterruptedException {
		
		double enemyAttack = enemy.getAttack();
		double enemyDefence = enemy.getDefence();
		enemy.setAttack(enemyAttack + 5);
		enemy.setDefence(enemyDefence - 5);
		
		for (int i = 0; i <= 2; i++) {
			
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
	}// End of taunt
	
	public void resetStats() {
		
		healthPoints = HEALTH_POINTS + 0;
		manaPoints = MANA_POINTS + 0;
		attackPoints = ATTACK_POINTS + 0;
		defencePoints = DEFENCE_POINTS + 0;
		speedPoints = SPEED_POINTS + 0;
		accuracyPoints = ACCURACY_POINTS + 0;
		criticalHitPoints = CRITICAL_HIT_POINTS + 0;
		
	}
	
	/**
	 * 
	 * @param enemy
	 * @param move
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 */
	
	private boolean finishBattle(EnemyObject enemy, String move)
			throws IOException, InterruptedException, FileNotFoundException {
		
		double enemyHealth = enemy.getHealth();
		
		if (move.equals("fly")) {
			System.out.println("You flew away from battle...");
			resetStats();
			enemy.resetStats();
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			return false;
		}
		
		else if (enemyHealth <= 0) {
			Utilities.clearConsole();
			enemy.getSprite("dead");
			getSprite("quack");
			System.out.println("You have beaten the enemy!");
			int gainedXP = enemy.getXP();
			int gainedMoney = enemy.getMoney();
			experience += gainedXP;
			System.out.print("You have gained ");
			System.out.print(gainedXP);
			System.out.println(" experience points!");
			Utilities.waitMilliseconds(800);
			money += gainedMoney;
			System.out.print("You have gained ");
			System.out.print(gainedMoney);
			System.out.println(" moneys!");
			Utilities.waitMilliseconds(800);
			levelUp();
			resetStats();
			enemy.resetStats();
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	private void levelUp() {
		
		if (experience >= 50) {
			level += 1;
			experience -= 50;
			HEALTH_POINTS += 2;
			MANA_POINTS += 2;
			ATTACK_POINTS += 1;
			DEFENCE_POINTS += 1;
			SPEED_POINTS += 1;
			ACCURACY_POINTS += 1;
			CRITICAL_HIT_POINTS += 1;
			System.out.println("You have levelled up!");
			Utilities.waitMilliseconds(800);
			System.out.print("You are now level ");
			System.out.println(level);
			Utilities.waitMilliseconds(800);
			System.out.println("Your stats have gone up!");
			Utilities.waitMilliseconds(800);
			
		}
		
	}
	
	public double getDefence() {
		return defencePoints;
	}
	
	public double getCriticalHit() {
		return criticalHitPoints;
	}
	
	public double getAttack() {
		return attackPoints;
	}
	
	public double getHealth() {
		return healthPoints;
	}
	
	public double getMana() {
		return manaPoints;
	}
	
	public double getSpeed() {
		return speedPoints;
	}
	
	public double getAccuracy() {
		return accuracyPoints;
	}
	
	/**
	 * 
	 * @param newValue
	 *
	 */
	
	public void setDefence(double newValue) {
		defencePoints = newValue;
	}
	
	public void setCriticalHit(double newValue) {
		criticalHitPoints = newValue;
	}
	
	public void setAttack(double newValue) {
		attackPoints = newValue;
	}
	
	public void setHealth(double newValue) {
		healthPoints = newValue;
	}
	
	public void setMana(double newValue) {
		manaPoints = newValue;
	}
	
	public void setSpeed(double newValue) {
		speedPoints = newValue;
	}
	
	public void setAccuracy(double newValue) {
		accuracyPoints = newValue;
	}
	
}
