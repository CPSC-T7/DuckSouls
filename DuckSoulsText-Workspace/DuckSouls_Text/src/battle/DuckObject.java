package battle;

import java.util.Scanner;
import utils.Utilities;
import java.util.Random;

/**
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 */
public class DuckObject {
	
	// Private Variables
	
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	private int				xPosition			= 0;
	private int				yPosition			= 0;
	
	// Player Stats (All fixed values for DEMO 1)
	// Stats for resetting (Will probably be set through a constructor later)
	private double			HEALTH_POINTS		= 20;
	private double			MANA_POINTS			= 15;
	private double			ATTACK_POINTS		= 5;
	private double			DEFENCE_POINTS		= 5;
	private double			SPEED_POINTS		= 5;
	private double			ACCURACY_POINTS		= 70;
	private double			CRITICAL_HIT_POINTS	= 16;
	
	// Stats that will change during the battle
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
	
	// Convert x/y position into spaces/tabs
	private String			xPadding			= Utilities.multiplyString("  ", xPosition);
	private String			yPadding			= Utilities.multiplyString("\n", yPosition);
	
	private static String	direction			= "Right";	// The direction that the sprite is facing

	private static Scanner	scanner				= new Scanner(System.in);	// Scanner to get user input
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * Gets the required sprite from a text file.
	 * 
	 * @param duckSprite
	 *            The sprite to print.
	 */
	
	public void getSprite(String duckSprite) {
		// update x position on screen
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		// Select the sprite frame to return based on method argument "duckSprite"
		switch (duckSprite) {
		
			case ("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");
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
	 * Gets information on which move to make from the user.
	 * 
	 * @param enemy
	 */
	public boolean playerMove(EnemyObject enemy) {
		
		boolean selection = true;
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
	 */
	public void quack(EnemyObject enemy) {
		
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("quack");
		Utilities.waitMilliseconds(1000);
		Utilities.clearConsole();
		
		getSprite("fight");
		enemy.getSprite("stand");
		getSprite("stand");
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
		}
		
	}// End of run
	
	/**
	 * 
	 * @param enemy
	 */
	public void attack(EnemyObject enemy) {
		
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
		
		//Wait for half a second before attacking
		Utilities.waitMilliseconds(500);
		
		//Run to the enemy, attack, then run back and turn around
		run(13, +1, 0, enemy);
		peck(enemy, 1);
		run(13, -1, 0, enemy);
		run(0, +1, 0, enemy);	
		
		//Enemy flinches
		enemy.flinch(this);
		
		//If the hit was unsuccessful, tell the player
		if (!landed) {

			System.out.println("You missed!");
		
		//If the hit was successful, take damage from the enemy and tell the player
		}else if (landed) {
			
			if (critical) {
				
				System.out.println("It's a critical hit!");
			}
			
			System.out.print("You dealt ");
			System.out.print(Math.round(damage));
			System.out.print(" damage to the enemy!");
		}
		
		//Wait before clearing the console
		Utilities.waitMilliseconds(2000);
		Utilities.clearConsole();
		
	}//End of attack
	
	/**
	 * 
	 * @param enemy
	 * @param numTimes
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
		}
		
	}// End of peck
	
	/**
	 * 
	 * @param enemy
	 */
	public void taunt(EnemyObject enemy) {
		
		double enemyAttack = enemy.getAttack();
		double enemyDefence = enemy.getDefence();
		enemy.setAttack(enemyAttack + 5);
		enemy.setDefence(enemyDefence - 5);
		
		for (int i = 0; i <= 2; i++) {
			
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
		}
		
		System.out.println("The enemy's attack has increased!");
		System.out.println("The enemy's defence has decreased!");
		Utilities.waitMilliseconds(2000);
		Utilities.clearConsole();
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
	 */
	
	private boolean finishBattle(EnemyObject enemy, String move) {
		
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
			getSprite("fight");
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
		
		//If the enemy is still alive
		else {
			return true;
		}
		
	}
	
	/**
	 * Level up the player and increase their stats as a reward.
	 */
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
			Utilities.clearConsole();
			
		}
		
	}
	
	public static void cleanup() {
		scanner.close();
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
