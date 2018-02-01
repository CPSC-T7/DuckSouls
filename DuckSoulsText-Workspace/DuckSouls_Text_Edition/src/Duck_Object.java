//IOException for use with CMD in Windows
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Wylee McAndrews
 * @author add name if modified
 */
public class Duck_Object {
	
	//Public Variables

	//Current Sprite
	public String sprite = "duck";
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
	public void playerMove(Enemy_Object enemy) throws IOException, InterruptedException {
		
		String move = scanner.nextLine();
		move = move.toLowerCase();
		
		if (move.contains("quack")) {
			quack(enemy);
		}else if (move.contains("attack")) {
			attack(enemy);
		}else if (move.contains("taunt")) {
			taunt(enemy);
		}
	}
	
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void quack(Enemy_Object enemy) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		
		System.out.print(getSprite("fightBanner"));
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		System.out.print(getSprite("quack"));
		Utilities.waitMilliseconds(1000);
		Utilities.clearConsole();
		
		System.out.print(getSprite("fightBanner"));
		System.out.print(enemy.getSprite(enemy.enemySprite()));
		System.out.print(getSprite("duck"));
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
			Utilities.waitMilliseconds(10);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("run_2"));
			Utilities.waitMilliseconds(10);
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
		run(13, +1, 0, enemy);
		peck(enemy);
		run(13, -1, 0, enemy);
		run(0, +1, 0, enemy);
		
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
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
			System.out.print(getSprite("fightBanner"));
			System.out.print(enemy.getSprite(enemy.enemySprite()));
			System.out.print(getSprite("attack2"));
			Utilities.waitMilliseconds(100);
			Utilities.clearConsole();
			
		}
	}
	
	/**
	 * 
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Enemy_Object enemy) throws IOException, InterruptedException 
	{
		
		for (int i = 0; i <= 2; i++) 
		{
			
				Utilities.clearConsole();
				
				System.out.print(getSprite("fightBanner"));
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				System.out.print(getSprite("duck"));
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
				System.out.print(getSprite("fightBanner"));
				System.out.print(enemy.getSprite(enemy.enemySprite()));
				System.out.print(getSprite("taunt"));
				Utilities.waitMilliseconds(100);
				Utilities.clearConsole();
				
		}
	}
}
