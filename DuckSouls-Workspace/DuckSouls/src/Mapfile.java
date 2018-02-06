import java.io.*;
import java.util.*;

public class Mapfile {

	private int size;
	private ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>(0);

	Scanner inputStream = null;

	public Mapfile(String fileName) {
		int lineCount = 0;
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + "fileName");
			System.exit(0);
		}
		while (inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			if (lineCount == 0) {
				this.size = Integer.parseInt(line);
			} else {
				map.add(new ArrayList<String>(size));
				line = line.trim();
				String[] parts = line.split(":");
				for (int i = 0; i < parts.length; i++) {
					map.get(lineCount - 1).add(parts[i]);
				} // end of for loop
			} // end of else block
			lineCount += 1;
		} // end of while block
		inputStream.close();
	}// end of constructor

	public void print() {
		for (int i = 0; i < this.map.size(); i++) {
			for (int i2 = 0; i2 < this.map.get(i).size(); i2++) {
				System.out.print(this.map.get(i).get(i2) + " ");
			}
			System.out.println(" ");
		}
	}// end of print()

	public void unlockDoor(int x, int y) {
		if (this.map.get(x).get(y).charAt(0) == 'D')
			if (this.map.get(x).get(y).charAt(1) == 'L') {
				String replace = this.map.get(x).get(y).replace('L', 'U');
				this.map.get(x).set(y, replace);
			}
	}

	public ArrayList<ArrayList<String>> getMap() {
		return map;
	}

}// end of class
