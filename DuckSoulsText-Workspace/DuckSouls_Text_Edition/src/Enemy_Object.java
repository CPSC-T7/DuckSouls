//IOException for use with CMD in Windows
import java.io.IOException;
import java.util.Scanner;


/**
 * SUPER SUPER SUPER EARLY ENEMY OBJECT:
 * Pretty much a direct copy of the duck sprite.
 * Only used as a placeholder until further enemies and
 * enemy attacks are created.
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 *
 */
public class Enemy_Object {
	
	//Public Variables
	
	//Player HP
	//public int healthPoints;
	//Current Sprite
	public String sprite = "duck";
	// x/y position: Where the duck is drawn on the screen (0,0 = topmost left)
	public int xPosition = 35;
	public int yPosition = 0	;
	//Enemy Stats
	public int healthPoints = 20;
	public int manaPoints = 15;
	public int attack = 5;
	public int defend = 5;
	public int speed = 5;
	public int accuracy = 70;
	public int criticalHit = 16;
	
	
	//Private Variables

	// x/y padding: Converts x/y position into spaces/tabs
	private String xPadding = Utilities.multiplyString("  ", xPosition);
	private String yPadding = Utilities.multiplyString("\n", yPosition);
	//The direction that the sprite is facing
	private static String direction = "Left";
	//Get user input
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * @return sprite
	 */
	public String enemySprite() {
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
		if (direction == "Left") {
			switch(duckSprite) 
			{
				case("duck"):
					return duck_Left;
			
				case("taunt"):
					return taunt_Left;
				
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
	public void enemyMove(Enemy_Object enemy) throws IOException, InterruptedException {
		
		String move = scanner.nextLine();
		move = move.toLowerCase();
		
		if (move.contains("example attack")) {
			System.out.println("This would make an enemy attack");
		}else if (move.contains("example defend")) {
			System.out.println("This would make an enemy defend");
		}
	}
	
	
	/**
	 * 
	 * @param seconds
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void stand(int seconds) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		System.out.print(getSprite("duck"));
		Thread.sleep(seconds*1000);
	}

}
