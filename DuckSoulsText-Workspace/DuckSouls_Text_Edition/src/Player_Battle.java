import java.util.Scanner;

/**
 * Player Stuff
 * Battle Loop
 * Options
 * Stats
 * 
 * @author Rahmanta Satriana
 * @author add name if modified
 *
 */

public class Player_Battle {
	
	//Player Stats, it's fixed for DEMO 1
	final private int HEALTH_POINTS = 20;
	final private int MANA_POINTS = 15;
	final private int ATTACK = 5;
	final private int DEFENCE = 5;
	final private int SPEED = 5;
	final private int ACCURACY = 70;
	final private int CRITICAL_HIT = 16;
	
	
	private int healthPoints = 20;
	private int manaPoints = 15;
	private int attack = 5;
	private int defence = 5;
	private int speed = 5;
	private int accuracy = 70;
	private int criticalHit = 16;
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static Duck_Object playerGraphics = new Duck_Object();
	
	public static void main(String[] args) {
		
	}
	
	public void playerLoop() {
		
		boolean selection = true;
		
		while (selection) {
			
			String move = scanner.nextLine();
			move = move.toLowerCase();
			
			if (move.contains("quack")) {
				quack(enemy);
				selection = false;
			}else if (move.contains("attack")) {
				attack(enemy);
				selection = false;
			}else if (move.contains("taunt")) {
				taunt(enemy);
				selection = false;
			}else if (move.contains("fly")) {
				fly(enemy);
				selection = false;
			}else {
				System.out.println("That is not a valid command.");
			}
			
		}
		
	}
	
	private void resetStats() {
		
		healthPoints = HEALTH_POINTS + 0;
		manaPoints = MANA_POINTS + 0;
		attack = ATTACK + 0;
		defence = DEFENCE + 0;
		speed = SPEED + 0;
		accuracy = ACCURACY + 0;
		criticalHit = CRITICAL_HIT + 0;
		
	}

}
