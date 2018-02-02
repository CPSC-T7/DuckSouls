import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Test_Text {

	public static void main(String[] args) throws FileNotFoundException
	{
		File duck = new File("TextSprites/Stand/duckStand_Left.txt");
		Scanner readFile = new Scanner(duck);
		while(readFile.hasNext()) {
			String line = readFile.nextLine();
			System.out.println(line);
		}
	}

}
