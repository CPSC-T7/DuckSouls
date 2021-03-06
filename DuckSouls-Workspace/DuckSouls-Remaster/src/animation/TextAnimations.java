package animation;



import utils.Utilities;

public class TextAnimations {

	
	
	/**
	 * Gets the required sprite from a text file.
	 * 
	 * @param sprite
	 *            The sprite to print.
	 * @param entity
	 * 			whether it is duck or rat
	 * @param direction
	 * 			whether it is facing right or left
	 * @param x
	 * 			the xCoordinate of the entity
	 * @param y
	 * 			the yCoordinate of the entity 
	 */
	
	public static void getSprite(String sprite, String entity, String direction, int x, int y) {
		// update x position on screen
		String xPadding = Utilities.multiplyString("  ", x);
		String yPadding = Utilities.multiplyString("\n", y);
		
		// Select the sprite frame to return based on method argument "duckSprite"
		switch (sprite) {
		
			case ("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");
				break;
			
			case ("stand"):
				Utilities.printSprite(entity + "/Stand/stand_" + direction, xPadding, yPadding);
				break;
			
			case ("taunt"):
				Utilities.printSprite(entity + "/Taunt/taunt_One_" + direction, xPadding, yPadding);
				break;
			
			case ("quack"):
				Utilities.printSprite(entity + "/Quack/duckQuack_" + direction, xPadding, yPadding);
				break;
			
			case ("attack1"):
				Utilities.printSprite(entity + "/Attack/attack_One_" + direction, xPadding, yPadding);
				break;
			
			case ("attack2"):
				Utilities.printSprite(entity + "/Attack/attack_Two_" + direction, xPadding, yPadding);
				break;	
				
			case ("attack3"):
				Utilities.printSprite(entity + "/Attack/attack_Three_" + direction, xPadding, yPadding);
				break;
			
			case ("hurt"):
				Utilities.printSprite(entity + "/Hurt/hurt_" + direction, xPadding, yPadding);
				break;
			
			case ("dead"):
				Utilities.printSprite(entity + "/Dead/dead_" + direction, xPadding, yPadding);
				break;
			
			case ("run_1"):
				Utilities.printSprite(entity + "/Run/run_One_" + direction, xPadding, yPadding);
				break;
			
			case ("run_2"):
				Utilities.printSprite(entity + "/Run/run_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
		
	}// End of getSprite
	
	/**
	 * Draw the move options ("Fight" banner for now), Enemy, and player in default
	 * positions
	 */
	public static void initialise() {
		getSprite("fight", "Rat", "Left", 35, 0);
		getSprite("stand", "Rat", "Left", 35, 0);
		getSprite("stand", "Duck", "Right", 0, 0);
	}
	
	
	/**
	 * Make the duck run
	 * 
	 * @param numTimes
	 * 				The number of times to run
	 * @param xDirection
	 * 				The direction to run on the X axis
	 * @param yDirection
	 * 				The direction to run on the Y axis
	 * @param xPosition
	 * 				the x position of the duck
	 */
	public static int playerRun(int numTimes, int xDirection, int yDirection, int xPosition) {
		String direction;
		String otherDirection;
		int yPosition = 0;
		if (xDirection == 1) {
			direction = "Right";
			otherDirection = "Left";
		} else {
			direction = "Left";
			otherDirection = "Right";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight", "Duck", direction, xPosition, yPosition);
			getSprite("stand", "Rat", otherDirection, 35, 0);
			getSprite("run_1", "Duck", direction, xPosition, yPosition);
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight", "Duck", direction, xPosition, yPosition);
			getSprite("stand", "Rat", otherDirection, 35, 0);
			getSprite("run_2", "Duck", direction, xPosition, yPosition);
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
		}
		
		return xPosition;
		
	}// End of run
	
	
	/**
	 * method for the attack animation, involves the duck running
	 * and pecking at the enemy and running back
	 */
	public static void playerAttackAnimation() {
		//Run to the enemy, attack, then run back and turn around
		int xPosition = 0;
		xPosition = playerRun(13, +1, 0, xPosition);
		Utilities.clearConsole();
		getSprite("fight", "Duck", "Right", xPosition, 0);
		getSprite("stand", "Rat", "Left", 35, 0);
		getSprite("attack1", "Duck", "Right", xPosition, 0);
		Utilities.waitMilliseconds(200);
		Utilities.clearConsole();
		
		getSprite("fight", "Duck", "Right", xPosition, 0);
		getSprite("hurt", "Rat", "Left", 35, 0);
		getSprite("attack2", "Duck", "Right", xPosition, 0);
		Utilities.waitMilliseconds(400);
		Utilities.clearConsole();
		xPosition = playerRun(13, -1, 0, xPosition);
		xPosition = playerRun(0, +1, 0, xPosition);	
		
		//Enemy flinches
		Utilities.clearConsole();
		
		getSprite("fight", "Rat", "Left", 35, 0);
		getSprite("stand", "Rat", "Left", 35, 0);
		getSprite("stand", "Duck", "Right", xPosition, 0);
		Utilities.waitMilliseconds(400);
		
		for (int i = 0; i <= 3; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", "Left", 35, 0);
			getSprite("taunt", "Rat", "Left", 35, 0);
			getSprite("stand", "Duck", "Right", xPosition, 0);
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", "Left", 35, 0);
			getSprite("taunt", "Rat", "Left", 35, 0);
			getSprite("stand", "Duck", "Right", xPosition, 0);
			Utilities.waitMilliseconds(50);
		}
	}
	
	/**
	 * method for the taunt animation
	 */
	public static void playerTauntAnimation() {
		for (int i = 0; i <= 2; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight", "Duck", "Right", 0, 0);
			getSprite("stand", "Rat", "Left", 35, 0);
			getSprite("stand", "Duck", "Right", 0, 0);
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
			getSprite("fight", "Duck", "Right", 0, 0);
			getSprite("stand", "Rat", "Left", 35, 0);
			getSprite("taunt", "Duck", "Right", 0, 0);
			Utilities.waitMilliseconds(100);	
		}
	}
	

	/**
	 * method for the animation when the player loses and dies
	 */
	public static void playerDeadAnimation() {
		Utilities.clearConsole();
		getSprite("stand", "Rat", "Left", 35, 0);
		getSprite("dead", "Duck", "Right", 0, 0);
	}
	
	
	
	/**
	 * 
	 * Make the rat run
	 * 
	 * @param numTimes
	 * 				The number of times to run
	 * @param xDirection
	 * 				The direction to run on the X axis
	 * @param yDirection
	 * 				The direction to run on the Y axis
	 * @param xPosition
	 * 				the x position of the rat
	 */
	public static int enemyRun(int numTimes, int xDirection, int yDirection, int xPosition) {
		String direction;
		String otherDirection;
		int yPosition = 0;
		if (xDirection == 1) {
			direction = "Right";
			otherDirection = "Left";
		} else {
			direction = "Left";
			otherDirection = "Right";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", direction, xPosition, yPosition);
			getSprite("run_1", "Rat", direction, xPosition, yPosition);
			getSprite("stand", "Duck", otherDirection, 0, 0);
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", direction, xPosition, yPosition);
			getSprite("run_2", "Rat", direction, xPosition, yPosition);
			getSprite("stand", "Duck", otherDirection, 0, 0);
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
		}
		
		return xPosition;
		
	}// End of run
	
	
	/**
	 * method for the attack animation, involves the rat running
	 * and swiping at the player and running back
	 */
	public static void enemyAttackAnimation() {
		int xPosition = 35;
		xPosition = enemyRun(13, -1, 0,xPosition);
		
		Utilities.clearConsole();
		
		getSprite("fight", "Rat", "Left", xPosition, 0);
		getSprite("attack1", "Rat", "Left", xPosition, 0);
		getSprite("stand", "Duck", "Right", 0, 0);
		Utilities.waitMilliseconds(300);
		Utilities.clearConsole();
		
		getSprite("fight", "Rat", "Left", xPosition, 0);
		getSprite("attack2", "Rat", "Left", xPosition, 0);
		getSprite("hurt", "Duck", "Right", 0, 0);
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
		getSprite("fight", "Rat", "Left", xPosition, 0);
		getSprite("attack3", "Rat", "Left", xPosition, 0);
		getSprite("hurt", "Duck", "Right", 0, 0);
		Utilities.waitMilliseconds(100);
		xPosition = enemyRun(13, +1, 0, xPosition);
		xPosition = enemyRun(0, -1, 0, xPosition);
	}
	
	/**
	 * method for the taunt animation
	 */
	public static void enemyTauntAnimation() {
		for (int i = 0; i <= 3; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", "Left", 35, 0);
			getSprite("taunt", "Rat", "Left", 35, 0);
			getSprite("stand", "Duck", "Right", 0, 0);
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight", "Rat", "Left", 35, 0);
			getSprite("taunt", "Rat", "Left", 35, 0);
			getSprite("stand", "Duck", "Right", 0, 0);
			Utilities.waitMilliseconds(50);
		}
	}
	
	/**
	 * method for the animation when the enemy loses and dies
	 */
	public static void enemyDeadAnimation() {
		Utilities.clearConsole();
		getSprite("dead", "Rat", "Left", 35, 0);
		getSprite("stand", "Duck", "Right", 0, 0);
	}
	
	
}
