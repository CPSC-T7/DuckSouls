//IOException for use with CMD in Windows
import java.io.IOException;

public class Duck_Sprite_Test {
	
	//Duck position on X and Y planes.
	private static int xPosition = 60;
	private static int yPosition = 40;
	private static String xPadding = multiString("  ", xPosition);
	private static String yPadding = multiString("\n", yPosition);
	

	

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		System.out.print(getSprite(xPosition, yPosition, "duckOne"));
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
		
		//Standing Duck
		String duckOne = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "       _0 0|\r\n" + 
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		//Duck Dance (Frame 1)
		String duckTwo = yPadding +
				xPadding +  "          __\r\n" + 
				xPadding +  "        _0 0|\r\n" + 
				xPadding +  "        --  /\r\n" + 
				xPadding +  "        /  /------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		//Duck Dance (Frame 2)
		String duckThree = yPadding +
				xPadding +  "         __\r\n" + 
				xPadding +  "Quak*  _0 0|\r\n" +
				xPadding +  "       --  |\r\n" + 
				xPadding +  "        |  |------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             ||\r\n" + 
				xPadding +  "            --";
		
		//Duck Run (Frame 1)
		String duckFour = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             /|\r\n" + 
				xPadding +  "            `-";
		
		//Duck Run (Frame 2)
		String duckFive = yPadding +
				xPadding +  "       __\r\n" + 
				xPadding +  "     _0 0\\\r\n" + 
				xPadding +  "     --   \\\r\n" + 
				xPadding +  "       \\   \\-------/\r\n" + 
				xPadding +  "        |        //\r\n" + 
				xPadding +  "         \\______//\r\n" + 
				xPadding +  "             |/\r\n" + 
				xPadding +  "             `";	
		
		//Select the sprite frame to return based on method argument "duckSprite"
		switch(duckSprite) 
		{
			case("duckOne"):
				return duckOne;
		
			case("duckTwo"):
				return duckTwo;
			
			case("duckThree"):
				return duckThree;
			
			case("duckFour"):
				return duckFour;
			
			case("duckFive"):
				return duckFive;
			
			default:
				return("Error: no sprite found.");
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
			
			duckStand(2);
			duckQuack();
			duckStand(1);
			duckRun(20, -1, -1);
			duckQuack();
			duckDance(5);
			duckStand(2);
			duckRun(5, -1, 0);
			duckRun(5, +1, 0);
			duckRun(5, -1, 0);
			duckRun(5, +1, 0);
			duckStand(2);
			duckRun(20, +1, +1);
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
		clearConsole();
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
		clearConsole();
		System.out.print(getSprite(xPosition, yPosition, "duckThree"));
		Thread.sleep(600);
		clearConsole();
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
		
		for (int i = 0; i < numTimes; i++) {
			clearConsole();
			System.out.print(getSprite(xPosition, yPosition, "duckFour"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			clearConsole();
			System.out.print(getSprite(xPosition, yPosition, "duckFive"));
			Thread.sleep(50);
			xPosition += xDirection;
			yPosition += yDirection;
			clearConsole();
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
				clearConsole();
				System.out.print(getSprite(xPosition, yPosition, "duckOne"));
				Thread.sleep(100);
				clearConsole();
				System.out.print(getSprite(xPosition, yPosition, "duckTwo"));
				Thread.sleep(100);
				clearConsole();
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
	
	/**
	 * Clear the console screen of text.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void clearConsole() throws IOException, InterruptedException
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	        	 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (Exception e) {
			//If console doesn't clear print this error
	    	System.out.println("Console failed to clear.");
	    	Thread.sleep(3000);
	    }
	}
}
