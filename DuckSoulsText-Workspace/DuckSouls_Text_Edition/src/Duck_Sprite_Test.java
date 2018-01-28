//IOException for use with CMD in Windows
import java.io.IOException;

public class Duck_Sprite_Test {
	
	//Duck position on X and Y planes.
	private static int xPosition = 60;
	private static int yPosition = 30;
	private static String xPadding = multiString("  ", xPosition);
	private static String yPadding = multiString("\n", yPosition);
	private static String direction = "Left";
	

	

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		//System.out.print(getSprite(xPosition, yPosition, "duckOne"));
		duckAnimation(1, xPosition, yPosition);
	}
	
	/**
	 * 
	 * @param xPad
	 * @param yPad
	 * @param duckSprite
	 * @return
	 */
	public static String getSprite(int xPos, int yPos, String duckSprite) 
	{
		//update x position on screen
		xPadding = multiString("  ", xPos);
		yPadding = multiString("\n", yPos);
		
		//Better way to store/move sprites?
		
		String duckSouls = 
				"\t\t   ███▄█  █   ██  ▄████▄   ██ ▄█▀     ██████  ▒█████   █    ██  ██▓      ██████ \r\n" + 
				"\t\t ██▀  ██ ██  ▓██▒▒██▀ ▀█   ██▄█▒    ░██    ▒ ░██▒  ██▒ ██  ▓██▒▓██▒    ▒██    ▒ \r\n" + 
				"\t\t██    ██░██  ▒██░▒▓█    ▄ ░███▄░    ░ ▓██▄   ░██░  ██▒▓██  ▒██░▒██░    ░ ▓██▄   \r\n" + 
				"\t\t ██   ██░░█  ░██░▒▓▓▄ ▄██░░██ █▄      ▒   ██░░██   ██░▓▓█  ░██░▒██░      ▒   ██▒\r\n" + 
				"\t\t░▒██████░░█████▓ ▒ ▓███▀ ░▒██▒ █▄   ▒██████▒░░ ████▓▒░▒▒█████▓ ░██████▒▒██████▒▒\r\n" + 
				"\t\t ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒   ▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒ ▒▓▒ ▒ ░\r\n" + 
				"\t\t ░ ▒  ▒ ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░   ░ ░▒  ░ ░  ░ ▒ ▒░ ░░▒░ ░ ░ ░ ░ ▒  ░░ ░▒  ░ ░\r\n" + 
				"\t\t ░ ░  ░  ░░░ ░ ░ ░        ░ ░░ ░    ░  ░  ░  ░ ░ ░ ▒   ░░░ ░ ░   ░ ░   ░  ░  ░  \r\n" + 
				"\t\t   ░       ░     ░ ░      ░  ░            ░      ░ ░     ░         ░  ░      ░  \r\n" + 
				"\t\t ░               ░                                                              \r\n" +
				"\t\t\t\t\t\t Maximize Window";
		
		//Standing Duck
		String duckOne_Left = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "       _0 0|\r\n" + 
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		String duckOne_Right = yPadding +
				xPadding +  "                __\r\n" + 
				xPadding +  "               |0 0_\r\n" + 
				xPadding +  "               |  --\r\n" + 
				xPadding +  "        \\------|  |\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --";
		
		//Duck Dance (Mix with standing duck)
		String duckTwo_Left = yPadding +
				xPadding +  "          __\r\n" + 
				xPadding +  "        _0 0|\r\n" + 
				xPadding +  "        --  /\r\n" + 
				xPadding +  "        /  /------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		String duckTwo_Right = yPadding +
				xPadding +  "               __\r\n" + 
				xPadding +  "              |0 0_\r\n" + 
				xPadding +  "              \\  --\r\n" + 
				xPadding +  "        \\------\\  \\\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --";
		
		//Duck Quack
		String duckThree_Left = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "Quak*  _0 0|\r\n" +
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		String duckThree_Right = yPadding +
				xPadding +  "                __\r\n" + 
				xPadding +  "               |0 0_  *Quak\r\n" + 
				xPadding +  "               |  --\r\n" + 
				xPadding +  "        \\------|  |\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "              --";	
		
		//Duck Run (Frame 1)
		String duckFour_Left = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             /|\r\n" + 
				xPadding +  "            `-";
		
		String duckFour_Right = yPadding +
				xPadding +  "                  __\r\n" + 
				xPadding +  "                 /0 0_\r\n" + 
				xPadding +  "                /   --\r\n" + 
				xPadding +  "       \\-------/   /\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             |\\\r\n" + 
				xPadding +  "              -`";
		
		//Duck Run (Frame 2)
		String duckFive_Left = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             |/\r\n" + 
				xPadding +  "             `";
		
		String duckFive_Right = yPadding +
				xPadding +  "                  __\r\n" + 
				xPadding +  "                 /0 0_\r\n" + 
				xPadding +  "                /   --\r\n" + 
				xPadding +  "       \\-------/   /\r\n" + 
				xPadding +  "        \\\\        |\r\n" + 
				xPadding +  "         \\\\______/\r\n" + 
				xPadding +  "             \\|\r\n" + 
				xPadding +  "              `-";
		
		//Select title sprite (Test)
		if (duckSprite == "duckSouls") {
			return duckSouls;
		}
		
		//Select the sprite frame to return based on method argument "duckSprite"
		if (direction == "Left") {
			switch(duckSprite) 
			{
				case("duckOne"):
					return duckOne_Left;
			
				case("duckTwo"):
					return duckTwo_Left;
				
				case("duckThree"):
					return duckThree_Left;
				
				case("duckFour"):
					return duckFour_Left;
				
				case("duckFive"):
					return duckFive_Left;
				
				default:
					return("Error: no sprite found.");
			}
		}else {
			switch(duckSprite) 
			{
				case("duckOne"):
					return duckOne_Right;
			
				case("duckTwo"):
					return duckTwo_Right;
				
				case("duckThree"):
					return duckThree_Right;
				
				case("duckFour"):
					return duckFour_Right;
				
				case("duckFive"):
					return duckFive_Right;
				
				default:
					return("Error: no sprite found.");
			}
		}
	}
	
	/**
	 * 
	 * @param numTimes
	 * @param xPosition
	 * @param yPosition
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void duckAnimation(int numTimes, int xPosition, int yPosition) throws IOException, InterruptedException 
	{
		for (int i=0; i < numTimes; i++) {
			
			System.out.print(getSprite(xPosition, yPosition, "duckSouls"));
			Thread.sleep(3000);
			Utilities.clearConsole();
			duckStand(2);
			duckQuack();
			duckStand(1);
			duckRun(12, -1, -1);
			duckQuack();
			duckDance(5);
			duckStand(2);
			duckRun(5, -1, 0);
			duckRun(5, +1, 0);
			duckRun(5, -1, 0);
			duckRun(5, +1, 0);
			duckStand(2);
			duckRun(12, +1, +1);
			duckDance(1);
			duckQuack();
		}
	}
	
	/**
	 * 
	 * @param seconds
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void duckStand(int seconds) throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		System.out.print(getSprite(xPosition, yPosition, "duckOne"));
		Thread.sleep(seconds*1000);
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void duckQuack() throws IOException, InterruptedException 
	{
		Utilities.clearConsole();
		System.out.print(getSprite(xPosition, yPosition, "duckThree"));
		Thread.sleep(600);
		Utilities.clearConsole();
		System.out.print(getSprite(xPosition, yPosition, "duckOne"));
	}
	
	/**
	 * 
	 * @param numTimes
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void duckRun(int numTimes, int xDirection, int yDirection) throws IOException, InterruptedException 
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		for (int i = 0; i < numTimes; i++) {
			Utilities.clearConsole();
			System.out.print(getSprite(xPosition, yPosition, "duckFour"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			System.out.print(getSprite(xPosition, yPosition, "duckFive"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}		
		System.out.print(getSprite(xPosition, yPosition, "duckOne"));
	}
	
	/**
	 * 
	 * @param numTimes
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void duckDance(int numTimes) throws IOException, InterruptedException 
	{
		
		for (int i = 0; i <= numTimes; i++) {
				Utilities.clearConsole();
				System.out.print(getSprite(xPosition, yPosition, "duckOne"));
				Thread.sleep(100);
				Utilities.clearConsole();
				System.out.print(getSprite(xPosition, yPosition, "duckTwo"));
				Thread.sleep(100);
				Utilities.clearConsole();
			}
		System.out.print(getSprite(xPosition, yPosition, "duckOne"));
	}
	

	/**
	 * Multiply a string by a specified amount.
	 * 
	 * i.e:  "f"*3 = "fff"
	 * 
	 * @param string
	 * 				The string to multiply.
	 * @param multiple
	 * 				The number of times to multiply the string.
	 * @return newString
	 * 				The new string.
	 */
	public static String multiString(String string, int multiple){
		
	    StringBuilder stringArray = new StringBuilder();
	    for(int i = 0; i < multiple; i++){
	        stringArray.append(string);
	    }
	    String newString = stringArray.toString();
	    return(newString);
	}
}
