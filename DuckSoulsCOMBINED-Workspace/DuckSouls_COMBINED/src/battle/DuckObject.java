package battle;

import java.util.Scanner;
import utils.Utilities;
import java.util.Random;

/**
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 */
public class DuckObject extends CharacterBattle {
	

	// Private Variables
	
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	private int				xPosition			= 0;
	private int				yPosition			= 0;
	
	// Convert x/y position into spaces/tabs
	private String			xPadding			= Utilities.multiplyString("  ", xPosition);
	private String			yPadding			= Utilities.multiplyString("\n", yPosition);
	
	//private double  acc = 71;

	private int				level				= 1;
	private int				experience			= 0;
	private int				money				= 0;
	
	
	
	//private boolean hasWeapon = false;
	//private boolean hasArmour = false;
	
	private static String	direction			= "Right";	// The direction that the sprite is facing

	private static Scanner	scanner				= new Scanner(System.in);	// Scanner to get user input
	
	public static void main(String[] args) {
		
	}
	
	
	
	public DuckObject(double health, double mana, double attack, double defence, double speed, double accuracy,	double crit) {
		super(health, mana, attack, defence, speed, accuracy, crit);
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
		//Tell the player when to make a move
		System.out.print("\nEnter a move: ");
		
		while (selection) {
			move = scanner.nextLine();
			move = move.toLowerCase();
			//Get input from the user
			
			if (move.equals("quack")) {
				quack(enemy);
				selection = false;
			} else if (move.equals("attack")) {
				attack(enemy);
				selection = false;
			} else if (move.equals("taunt")) {
				taunt(enemy);
				selection = false;
			} else if (move.equals("fly")) {
				selection = false;
			} else if (move.equals("help")) {
				System.out.println(" 'attack', 'taunt', 'quack': ");
				System.out.print("\nEnter a move: ");
			}else {
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
		//Create a random object
		int accuracyChance = rand.nextInt(100) + 1;
		int criticalHitChance = rand.nextInt(100) + 1;
		//Get random numbers
		boolean landed = true;
		boolean critical = true;

		
		if (accuracyChance <= this.getStats("accuracyPoints")) {
			landed = true;
		} else {
			landed = false;
		}
		//To see if it will be a successful attack or not

		
		
		if (criticalHitChance <= getStats("criticalHitPoints")) {
			critical = true;
		} else {
			critical = false;
		}
		//To see if it will deal bonus damage or not
		
		double damage;
		damage = (getStats("attackPoints") * 2.5) - enemy.getStats("defencePoints");
		
		damage = attackBonus(damage);
		//Temporary damage formula
		double enemyHealth = enemy.getStats("healthPoints");
		//Gets enemy's health
		if (critical) {
			damage = damage * 1.5;
			//if extra damage then increase it
		}
		if (landed) {
			double newHealth = enemyHealth - damage;
			enemy.setStats("healthPoints", (Math.round(newHealth)));
			//If successful attack then minus the enemy health
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
		
		if (landed) {
			if (critical) {
				System.out.println("It's a critical hit!");
			}
			System.out.print("You dealt ");
			System.out.print(Math.round(damage));
			System.out.println(" damage to the enemy!");
		}
		
		else {
			System.out.println("You missed!");
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
			//A pecking animation for the duck
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
		
		double enemyAttack = enemy.getStats("attackPoints");
		double enemyDefence = enemy.getStats("defencePoints");
		enemy.setStats("attackPoints", (enemyAttack + 5));
		enemy.setStats("defencePoints", (enemyDefence - 5));
		//Increases and decreases the enemy's stats
		
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
	

	
	/**
	 * 
	 * @param enemy
	 * @param move
	 * 
	 */
	
	private boolean finishBattle(EnemyObject enemy, String move) {
		//Two ways of the battles finishing on player's turn
		double enemyHealth = enemy.getStats("healthPoints");
		
		if (move.equals("fly")) {
			//Player runs away
			
			System.out.println("You flew away from battle...");
			super.resetStats();
			enemy.resetStats();
			//resets both player's and enemy's stats
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			
			return false;
		}
		
		else if (enemyHealth <= 0) {
			//Enemy's health reaches zero
			
			Utilities.clearConsole();
			getSprite("fight");
			enemy.getSprite("dead");
			getSprite("quack");
			System.out.println("You have beaten the enemy!");
			
			int gainedXP = enemy.getXP();
			int gainedMoney = enemy.getMoney();
			experience += gainedXP;
			//Add experience to the player
			
			System.out.print("You have gained ");
			System.out.print(gainedXP);
			System.out.println(" experience points!");
			Utilities.waitMilliseconds(800);
			
			money += gainedMoney;
			System.out.print("You have gained ");
			System.out.print(gainedMoney);
			System.out.println(" moneys!");
			//Add money to the player
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
			setStats("healthPointsStatic", (getStats("healthPointsStatic") + 2));
			setStats("manaPointsStatic", (getStats("manaPointsStatic") + 2));
			setStats("attackPointsStatic", (getStats("attackPointsStatic") + 1));
			setStats("defencePointsStatic", (getStats("defencePointsStatic") + 1));
			setStats("speedPointsStatic", (getStats("speedPointsStatic") + 1));
			setStats("accuracyPointsStatic", (getStats("accuracyPointsStatic") + 1));
			setStats("criticalHitPointsStatic", (getStats("criticalHitPointsStatic") + 1));
			
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
	
	public int getMoney() {
		return money;
	}
	
	
}