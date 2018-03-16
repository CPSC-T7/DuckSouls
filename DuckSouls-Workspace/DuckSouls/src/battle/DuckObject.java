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
	
	//Text or GUI animation type
	private char animationType;
	
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left) only for Text version
	private int				xPosition			= 0;
	private int				yPosition			= 0;
	
	// Convert x/y position into spaces/tabs
	private String			xPadding			= Utilities.multiplyString("  ", xPosition);
	private String			yPadding			= Utilities.multiplyString("\n", yPosition);
	
	//private double  acc = 71;

	private int				level				= 1;
	private double			experience			= 0;
	private double			neededXP 			= 50;
	private int				money				= 0;
	private boolean			alreadyTaunted		= false;
	
	
	
	//private boolean hasWeapon = false;
	//private boolean hasArmour = false;
	
	private static String	direction			= "Right";	// The direction that the sprite is facing

	private static Scanner	scanner				= new Scanner(System.in);	// Scanner to get user input
	
	public static void main(String[] args) {
		
	}
	
	
	
	public DuckObject(double health, double mana, double attack, double defence, double speed, double accuracy,	double crit, char animationType) {
		super(health, mana, attack, defence, speed, accuracy, crit);
		this.animationType = animationType;
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
	public boolean playerMove(EnemyObject enemy, String move) {
		
		boolean selection = true;
		
		while (selection) {
			//If the program is text based, ask the user for input
			if (this.animationType == 'T') {
				System.out.print("\nEnter a move: ");
				move = scanner.nextLine();
			}
			//Chang the move to lowercase
			move = move.toLowerCase();
			
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
		//Create a random object
		Random rand = new Random();
		//Get random numbers
		int accuracyChance = rand.nextInt(100) + 1;
		int criticalHitChance = rand.nextInt(100) + 1;
		
		boolean landed = true;
		boolean critical = true;

		//To see if it will be a successful attack or not
		if (accuracyChance <= this.getStats("accuracyPoints")) {
			landed = true;
		} else {
			landed = false;
		}
		

		
		//To see if it will deal bonus damage or not
		if (criticalHitChance <= getStats("criticalHitPoints")) {
			critical = true;
		} else {
			critical = false;
		}
		
		
		double damage;
		
		//Temporary damage formula
		damage = (getStats("attackPoints") * 2.5) - enemy.getStats("defencePoints");
		
		damage = attackBonus(damage);
		
		double enemyHealth = enemy.getStats("healthPoints");
		
		//if extra damage then increase it
		if (critical) {
			damage = damage * 1.5;
			
		}
		
		//If successful attack then minus the enemy health
		if (landed) {
			if (damage < 1) {
				damage = 1;
			}
			double newHealth = enemyHealth - damage;
			enemy.setStats("healthPoints", (Math.round(newHealth)));
			
		}
	
		
		//Tell the player how much damage they dealt.
		System.out.print("You dealt ");
		System.out.print(Math.round(damage));
		System.out.println(" damage to the enemy!");
		
		//Only print to the console if the version is 'T' (text)
		if (this.animationType == 'T') {
		
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
		}
		
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
		
		if(alreadyTaunted) {
			System.out.println("You've already taunted the enemy!");
			Utilities.waitMilliseconds(2000);
			Utilities.clearConsole();
		}
		else {
		
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
		alreadyTaunted = true;
		Utilities.waitMilliseconds(2000);
		Utilities.clearConsole();
		}
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
			alreadyTaunted = false;
			super.resetStats();
			enemy.resetStats();
			//resets both player's and enemy's stats
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			
			return false;
		}
		
		else if (enemyHealth <= 0) {
			//Enemy's health reaches zero
			
			System.out.println("You have beaten the enemy!");
			
			//Add experience and money to the player	
			int gainedXP = enemy.getXP();
			int gainedMoney = enemy.getMoney();
			experience += gainedXP;
			money += gainedMoney;
					
			//If the animation is text based
			if (this.animationType == 'T') {
				Utilities.clearConsole();
				getSprite("fight");
				enemy.getSprite("dead");
				getSprite("quack");
				System.out.println("You have beaten the enemy!");
				
				System.out.print("You have gained ");
				System.out.print(gainedXP);
				System.out.println(" experience points!");
				Utilities.waitMilliseconds(800);
				
				
				System.out.print("You have gained ");
				System.out.print(gainedMoney);
				System.out.println(" moneys!");
				Utilities.waitMilliseconds(800);

				
				System.out.println("The battle has ended.");
				Utilities.waitMilliseconds(1200);
			}
			
			levelUp(enemy);
			alreadyTaunted = false;
			resetStats();
			enemy.resetStats();
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
	private void levelUp(EnemyObject enemy) {
		
		if (experience >= neededXP) {
			
			level += 1;
			experience -= neededXP;
			setStats("healthPointsStatic", (getStats("healthPointsStatic") + 3));
			setStats("manaPointsStatic", (getStats("manaPointsStatic") + 2));
			setStats("attackPointsStatic", (getStats("attackPointsStatic") + 2));
			setStats("defencePointsStatic", (getStats("defencePointsStatic") + 1));
			setStats("speedPointsStatic", (getStats("speedPointsStatic") + 1));
			setStats("accuracyPointsStatic", (getStats("accuracyPointsStatic") + 1));
			setStats("criticalHitPointsStatic", (getStats("criticalHitPointsStatic") + 1));

			increaseXPNeeded();
			scaleEnemy(enemy);
			
			//If the animation is text based
			if (this.animationType == 'T') {
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

	}
	
	private void increaseXPNeeded() {
		double increases = neededXP * 0.13;
		neededXP = Math.round(increases);
		
	}
	
	private void scaleEnemy(EnemyObject enemy) {
		enemy.setStats("healthPointsStatic", (getStats("healthPointsStatic") + 2));
		enemy.setStats("manaPointsStatic", (getStats("manaPointsStatic") + 1));
		enemy.setStats("attackPointsStatic", (getStats("attackPointsStatic") + 2));
		enemy.setStats("defencePointsStatic", (getStats("defencePointsStatic") + 1));
		enemy.setStats("speedPointsStatic", (getStats("speedPointsStatic") + 2));
		enemy.setStats("accuracyPointsStatic", (getStats("accuracyPointsStatic") + 1));
		enemy.setStats("criticalHitPointsStatic", (getStats("criticalHitPointsStatic") + 2));
	}
	
	
	public static void cleanup() {
		scanner.close();
	}
	
	public int getMoney() {
		return money;
	}
	
	public double getXP() {
		return experience;
	}
	
	
}