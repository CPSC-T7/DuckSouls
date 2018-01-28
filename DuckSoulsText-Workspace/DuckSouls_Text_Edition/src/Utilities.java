import java.io.IOException;

public class Utilities {

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
