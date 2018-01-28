import java.io.IOException;

public class Text_Animation_Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		loading();
	}
	
	public static void loading() throws IOException, InterruptedException {
		String duckOne = "         __\r\n" + 
				"       _0 0|\r\n" + 
				"       --  |\r\n" + 
				"        |  |------/\r\n" + 
				"        |        //\r\n" + 
				"         \\______//\r\n" + 
				"             ||\r\n" + 
				"            --";
		String duckTwo = "          __\r\n" + 
				"        _0 0|\r\n" + 
				"        --  /\r\n" + 
				"        /  /------/\r\n" + 
				"        |        //\r\n" + 
				"         \\______//\r\n" + 
				"             ||\r\n" + 
				"            --";
		String duckThree = "         __\r\n" + 
				"Quak*  _0 0|\r\n" +
				"       --  |\r\n" + 
				"        |  |------/\r\n" + 
				"        |        //\r\n" + 
				"         \\______//\r\n" + 
				"             ||\r\n" + 
				"            --";
		String duckFour = "       __\r\n" + 
				"     _0 0\\\r\n" + 
				"     --   \\\r\n" + 
				"       \\   \\-------/\r\n" + 
				"        |        //\r\n" + 
				"         \\______//\r\n" + 
				"             /|\r\n" + 
				"            `-";
		String duckFive = "       __\r\n" + 
				"     _0 0\\\r\n" + 
				"     --   \\\r\n" + 
				"       \\   \\-------/\r\n" + 
				"        |        //\r\n" + 
				"         \\______//\r\n" + 
				"             |/\r\n" + 
				"             `";
		for (int i=0; i < 400; i++) {
			for (int b=0; b<4; b++) {
				System.out.print(duckOne);
				Thread.sleep(100);
				clearConsole();
				System.out.print(duckTwo);
				Thread.sleep(100);
				clearConsole();
			}
			System.out.print(duckThree);
			Thread.sleep(300);
			clearConsole();
			System.out.print(duckOne);
			Thread.sleep(500);
			clearConsole();
			for (int b=0; b<10; b++) {
				System.out.print(duckFour);
				Thread.sleep(50);
				clearConsole();
				System.out.print(duckFive);
				Thread.sleep(50);
				clearConsole();
			}
		}
	}
	
	public final static void clearConsole()
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
	    catch (final Exception e)
	    {
	    	System.out.println("Oof, ouch");
	        //  Handle any exceptions.
	    }
	}
}
