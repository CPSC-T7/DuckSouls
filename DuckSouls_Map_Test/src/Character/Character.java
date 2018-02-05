package Character;
import java.awt.*;
import java.util.*;
import Tiles.*;

public class Character {
	private Point pos = new Point();
	private char orientation = 'E';
	private boolean isCharacter = false;
	private String rep = new String();
	private char[] possibleOrientation  = {'N', 'E', 'S', 'W'};
	
	
	
	public Character(int x, int y, boolean isCharacter) {
		this.pos.setLocation(x, y);
		this.isCharacter = isCharacter;
		if(this.isCharacter) this.rep = "@";
		else this.rep="E";
	}
	
	public String print() {
		return this.rep;
	}
	
	public void move(int y, int x, ArrayList<ArrayList<Tile>> mapdata) {
		if(mapdata.get(y).get(x).CanMove()) {
			pos.setLocation(x, y);
		}
	}
	
	public void turn(char direction) {
		for(char i: this.possibleOrientation) {
			if(direction == i)
				orientation = direction;
		}
	}
	
	public void setPos(int x, int y, ArrayList<ArrayList<Tile>> map) {
		if(map.get(y).get(x).CanMove()) {
			this.pos.setLocation(x, y);
		}
	}
	
	public int getX() {
		return this.pos.x;
	}
	
	public int getY() {
		return this.pos.y;
	}
	
	public boolean isPlayer() {
		return this.isCharacter;
	}

	public void move(ArrayList<ArrayList<Tile>> map) {
		
	}
	
}
