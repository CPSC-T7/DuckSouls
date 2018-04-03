package battle;

import entities.Entity;
import entities.Player;

public class PrintBattleText {
	
	//TODO: TURN INTO ABSTRACT MAYBE LATER
	// TEXTTEXT AND GUITEXT EXTENDS
	
	public static void attackingText() {
		
	}
	
	public static void damageText(Entity attacker, double damage, boolean isGUI) {
		String text = "";
		if (attacker instanceof Player) {
			text = "You dealt " + Math.round(damage) + " damage to the enemy!";
		}
		else {
			text = "The enemy dealt " + Math.round(damage) + " damage to you!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void critText(Entity attacker, boolean isGUI) {
		String text = "";
		if (attacker instanceof Player) {
			text = "You dealt a critical hit!";
		}
		else {
			text = "The enemy dealt a critical hit!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void missedText(Entity attacker, boolean isGUI) {
		String text = "";
		if (attacker instanceof Player) {
			text = "You missed the enemy!";
		}
		else {
			text = "The enemy missed you!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void tauntedText(Entity attacker, boolean isGUI) {
		String text = "";
		String text2 = "attack has been increased and defence decreased!";
		if (attacker instanceof Player) {
			text = "You taunted the enemy! \n Their" + text2;
		}
		else {
			text = "The enemy taunted you! \n Your" + text2;
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}	
}
