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
public class EnemyObject extends CharacterBattle {
	
	/** Public Variables */
	
	// x/y position: Where the enemy is drawn on the screen (0,0 = topmost left)
	public int				xPosition			= 35;
	public int				yPosition			= 0;
	
	/** Private Variables */
	
	//The type of animation (text or GUI)
	private char animationType;

	
	//Amount of XP and Money to give the player upon death
	private int				giveXP				= 25;
	private int				giveMoney			= 100;
	private boolean			alreadyTaunted		= false;
	
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
	public EnemyObject(String enemy, double health, double mana, double attack, double defence, double speed, double accuracy,	double crit, char animationType) {
		super(health, mana, attack, defence, speed, accuracy, crit);
		enemyType = enemy;
		this.animationType = animationType;
		
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
	public boolean enemyMove(DuckObject player, int move) {
		
		//Random random = new Random();
		//int move = random.nextInt(4);
		
		if (alreadyTaunted) {
			move = 0;
		}
		
		//A random chance for the enemy to choose a move
		if (move == 0 || move == 2 || move == 3) {
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
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean enemyMove(DuckObject player) {
		
		Random random = new Random();
		int move = random.nextInt(4);
		
		if (alreadyTaunted) {
			move = 0;
		}
		
		//A random chance for the enemy to choose a move
		if (move == 0 || move == 2 || move == 3) {
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
		
		if (accuracyChance <= getStats("accuracyPoints")) {
			landed = true;
		} else {
			landed = false;
		}
		
		
		if (criticalHitChance <= getStats("criticalHitPoints")) {
			critical = true;
		} else {
			critical = false;
		}
		
		double damage;
		damage = (getStats("attackPoints") * 2.5) - player.getStats("defencePoints");
		double playerHealth = player.getStats("healthPoints");
		if (critical) {
			damage = damage * 1.5;
		}
		
		damage = attackBonus(damage);
		
		if (landed) {
			if (damage < 1) {
				damage = 1;
			}
			double newHealth = playerHealth - damage;
			player.setStats("healthPoints", (Math.round(newHealth)));
		}
		
		//If the animation is text based, print to console
		if (this.animationType == 'T') {
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
		}
	}// End of attack
	
	/**
	 * 
	 * @param player
	 */
	public void taunt(DuckObject player) {
		
		//If the enemy has already taunted
		if(alreadyTaunted) {
			System.out.println("The enemy taunted, but it didn't do anything!");
			Utilities.waitMilliseconds(2000);
			Utilities.clearConsole();
		}
		else {
			//Increases and decreases the player's stats
			double playerAttack = player.getStats("attackPoints");
			double playerDefence = player.getStats("defencePoints");
			player.setStats("attackPoints", (playerAttack + 5));
			player.setStats("defencePoints", (playerDefence - 5));
			alreadyTaunted = true;
			
			if (this.animationType == 'T') {
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
		}
	}//End of taunt
	
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
	 * 
	 * @param player
	 * @param move
	 * @return
	 */
	private boolean finishBattle(DuckObject player, int move) {
		
		double playerHealth = player.getStats("healthPoints");
		
		if (playerHealth <= 0) {
			if(this.animationType == 'T') {
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
				
			}
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	public int getMoney() {
		return giveMoney;
	}
	
	public int getXP() {
		return giveXP;
	}

	
}