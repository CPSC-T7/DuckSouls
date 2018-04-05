package battle;

import entities.Entity;
import entities.Player;

public class PrintBattleText {
	
	//TODO: ADD WAIT MILLISECONDS UTILITIES FOR TEXT VERSION and maybe gui
	
	public static void attackingText(Entity attacker, boolean isGUI) {
		String text = "";
		if (attacker instanceof Player) {
			text = "You attacked the enemy!";
		}
		else {
			text = "The enemy attacked you!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
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
	
	public static void slainEntity(Entity attacker, boolean isGUI) {
		String text = "";
		if (attacker instanceof Player) {
			text = "The enemy slained you!";
		}
		else {
			text = "You have slained the enemy!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void gameOver(boolean isGUI) {
		String text = "You have died..."
				+ "\n The light is slowly fading away..."
				+ "\n You lost your conscienceness...";
		
		if (isGUI) {
			//TODO WYLEEEEEE
		}
		else {
			System.out.println(text);
		}
	}
}
