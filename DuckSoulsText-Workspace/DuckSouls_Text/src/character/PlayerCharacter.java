package character;
import java.util.*;

import tiles.*;

public class PlayerCharacter extends Character {
	Scanner keyboard = new Scanner(System.in);
	
	public PlayerCharacter() {
		super(0, 0, true);
	}
	
	public PlayerCharacter(int x, int y, ArrayList<ArrayList<Tile>> map) {
		super(x, y, true);
		this.setPos(x, y, map);
	}
	
	public void move(ArrayList<ArrayList<Tile>> map) {
		String[] possibleInput = {"w", "a", "s", "d"};
		boolean done = false;
		do {
			System.out.println("Where do you want to move? (input with wasd)");
			String line = keyboard.nextLine();
			line = line.trim();
			for(String input: possibleInput) {
				if(line.equals(input)) {
					switch(line) {
					case "w":
						if(this.getY() >= 1) {
							if(map.get(this.getY()-1).get(this.getX()).CanMove()) {
								super.move(this.getY()-1, this.getX(), map);
								done = true;
							}
						}
						break;
					case "s":
						if(this.getY() < map.size()-1) {
							if(map.get(this.getY()+1).get(this.getX()).CanMove()) {
								super.move(this.getY()+1, this.getX(), map);
								done = true;
							}
						}
						break;
					case "a":
						if(this.getX() > 0) {
							if(map.get(this.getY()).get(this.getX()-1).CanMove()) {
								super.move(this.getY(), this.getX()-1, map);
								done = true;
							}
						}
						break;
					case "d":
						if(this.getX() < map.get(this.getY()).size()-1) {
							if(map.get(this.getY()).get(this.getX()+1).CanMove()) {
								super.move(this.getY(), this.getX()+1, map);
								done = true;
							}
						}
						break;
					}//end of switch statement
				}//end of if statement
			}//end of for loop
		if(!done)
			System.out.println("Invalid move");
		} while(!done);
	}//end of move method
}
