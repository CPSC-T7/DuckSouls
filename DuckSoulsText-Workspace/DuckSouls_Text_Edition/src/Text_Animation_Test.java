
public class Text_Animation_Test {

	public static void main(String[] args) throws InterruptedException {
		loading();
	}
	
	public static void loading() throws InterruptedException {
		String duckOne = "  ^\r\n" + 
				"_0 0\r\n" + 
				"-- |\r\n" + 
				" |  ------/\r\n" + 
				" |       //\r\n" + 
				"  \\_____//\r\n" + 
				"     ||\r\n" + 
				"    --\r\n" + 
				"";
		String duckTwo = "  ^\r\n" + 
				"_0 0\r\n" + 
				"-- |\r\n" + 
				" |  ------/\r\n" + 
				" |        /\r\n" + 
				"  \\______/\r\n" + 
				"      |\r\n" + 
				"     --\r\n" + 
				"";
		
		for (int i=0; i < 100; i++) {
			System.out.print(duckOne);
			Thread.sleep(100);
			clearConsole();
			System.out.print(duckTwo);
			Thread.sleep(100);
			clearConsole();
		}
	}
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
}
