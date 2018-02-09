package battle;

//IOException for use with CMD in Windows
import java.io.IOException;
import java.util.Random;

import utils.Utilities;

/**
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 *
 */
public class EnemyObject {
	
	/** Public Variables */
	
	// x/y position: Where the enemy is drawn on the screen (0,0 = topmost left)
	public int				xPosition			= 35;
	public int				yPosition			= 0;
	
	/** Private Variables */
	
	// Enemy Default Stats
	final private double	HEALTH_POINTS		= 10;
	final private double	MANA_POINTS			= 15;
	final private double	ATTACK_POINTS		= 5;
	final private double	DEFENCE_POINTS		= 5;
	final private double	SPEED_POINTS		= 5;
	final private double	ACCURACY_POINTS		= 70;
	final private double	CRITICAL_HIT_POINTS	= 16;
	
	// Enemy Battle Stats (will change with the game)
	private double			healthPoints		= 10;
	private double			manaPoints			= 15;
	private double			attackPoints		= 5;
	private double			defencePoints		= 5;
	private double			speedPoints			= 5;
	private double			accuracyPoints		= 70;
	private double			criticalHitPoints	= 16;
	
	//Amount of XP and Money to give the player upon death
	private int				giveXP				= 25;
	private int				giveMoney			= 100;
	
	private String			enemyType; 						//The type of enemy (will be changed via constructor)
	
	// x/y padding: Converts x/y position into spaces/tabs
	private String			xPadding			= Utilities.multiplyString("  ", xPosition);
	private String			yPadding			= Utilities.multiplyString("\n", yPosition);
	
	private static String	direction			= "Left";	// The direction that the sprite is facing
	
	/**
	 * Object constructor: Define character type when a new Enemy_Object is created.
	 * (i.e Rat, Fish, etc) Modifiable with different characteristics (attacks,
	 * stats, etc)
	 * 
	 * @param enemy
	 *            The type of enemy to display (Will affect sprite used & move type)
	 */
	public EnemyObject(String enemy) {
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
	 *            The type of sprite to print (species of enemy)
	 */
	public void getSprite(String enemySprite) {
		
		// Update position on screen using newlines and spaces
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		// Select the sprite frame to print based on the argument "enemySprite"
		// Uses 'enemyType' string to decide which folder to choose.
		switch (enemySprite) {
			// Fight banner to be replaced with stats
			case ("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");
				;
				break;
			
			case ("stand"):
				Utilities.printSprite(enemyType + "/Stand/stand_" + direction, xPadding, yPadding);
				break;
			
			case ("taunt1"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_One_" + direction, xPadding, yPadding);
				break;
			
			case ("taunt2"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_Two_" + direction, xPadding, yPadding);
				break;
			
			case ("hurt"):
				Utilities.printSprite(enemyType + "/Hurt/hurt_" + direction, xPadding, yPadding);
				break;
			
			case ("attack1"):
				Utilities.printSprite(enemyType + "/Attack/attack_One", xPadding, yPadding);
				break;
			
			case ("attack2"):
				Utilities.printSprite(enemyType + "/Attack/attack_Two", xPadding, yPadding);
				break;
			
			case ("attack3"):
				Utilities.printSprite(enemyType + "/Attack/attack_Three", xPadding, yPadding);
				break;
			
			case ("dead"):
				Utilities.printSprite(enemyType + "/Dead/dead_" + direction, xPadding, yPadding);
				break;
			
			case ("run_1"):
				Utilities.printSprite(enemyType + "/Run/run_One_" + direction, xPadding, yPadding);
				break;
			
			case ("run_2"):
				Utilities.printSprite(enemyType + "/Run/run_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
	} // End of getSprite
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean enemyMove(DuckObject player) {
		
		Random random = new Random();
		int move = random.nextInt(2);
		//A random chance for the enemy to choose a move
		
		if (move == 0) {
			attack(player);
		} else if (move == 1) {
			taunt(player);
		}
		//Only two moves for now
		boolean inBattle = finishBattle(player, move);
		
		return inBattle;
		
	}// End of enemyMove
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param player
	 */
	public void run(int numTimes, int xDirection, int yDirection, DuckObject player) {
		if (xDirection == 1) {
			direction = "Right";
		} else {
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
		}
		
	}// End of run
	
	/**
	 * 
	 * @param player
	 */
	public void swipe(DuckObject player) {
		
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
	}// End of swipe
	
	/**
	 * 
	 * @param player
	 */
	public void attack(DuckObject player) {
		
		
		//Same stuff as in the DuckObject class
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
		damage = (attackPoints * 2.5) - player.getDefence();
		double playerHealth = player.getHealth();
		if (critical) {
			damage = damage * 1.5;
		}
		if (landed) {
			double newHealth = playerHealth - damage;
			player.setHealth(Math.round(newHealth));
		}
		
		System.out.println("The enemy attacked you...");
		Utilities.waitMilliseconds(500);
		
		run(13, -1, 0, player);
		swipe(player);
		run(13, +1, 0, player);
		run(0, -1, 0, player);
		
		if (landed) {
			if (critical) {
				System.out.println("It's a critical hit!");
			}
			System.out.print("The enemy dealt ");
			System.out.print(Math.round(damage));
			System.out.println(" damage to you!");
		}
		
		else {
			System.out.println("The enemy missed!");
		}
		Utilities.waitMilliseconds(2000);
		Utilities.clearConsole();
	}// End of attack
	
	/**
	 * 
	 * @param player
	 */
	public void taunt(DuckObject player) {
		
		double playerAttack = player.getAttack();
		double playerDefence = player.getDefence();
		player.setAttack(playerAttack + 5);
		player.setDefence(playerDefence - 5);
		//Increases and decreases the player's stats
		
		for (int i = 0; i <= 3; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
		}
		
		//End of taunt sprites
		Utilities.clearConsole();
		getSprite("fight");
		getSprite("stand");
		player.getSprite("stand");
		
		System.out.println("The enemy taunted you...");
		System.out.println("Your attack has increased!");
		System.out.println("Your defence has decreased!");
		Utilities.waitMilliseconds(2000);
		Utilities.clearConsole();
	}
	
	/**
	 * 
	 * @param player
	 */
	public void flinch(DuckObject player) {
		
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("stand");
		player.getSprite("stand");
		Utilities.waitMilliseconds(400);
		
		for (int i = 0; i <= 3; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
		}
	}
	
	/**
	 * Resets stats to original values.
	 */
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
	 * @param player
	 * @param move
	 * @return
	 */
	private boolean finishBattle(DuckObject player, int move) {
		
		double playerHealth = player.getHealth();
		
		/*
		 * if (move == 2) { System.out.println("The enemy ran away from battle...");
		 * resetStats(); player.resetStats(); return false; }
		 */
		
		/* else */ if (playerHealth <= 0) {
			Utilities.clearConsole();
			getSprite("stand");
			player.getSprite("dead");
			System.out.println("The enemy knocked you out!");
			Utilities.waitMilliseconds(1000);
			System.out.println("Your consciousness slowly fades from this world...");
			Utilities.waitMilliseconds(1000);
			// resetStats();
			// player.resetStats();
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			System.exit(0);
			//if the player dies then exit from the game
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	//Getters and setters for the stats
	
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
	
	public int getXP() {
		return giveXP;
	}
	
	public int getMoney() {
		return giveMoney;
	}
	
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
		Utilities.waitMilliseconds(50);
		Utilities.clearConsole();
	}
	
}
