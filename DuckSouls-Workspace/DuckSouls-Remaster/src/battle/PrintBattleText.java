package battle;

import entities.Enemy;
import entities.Player;
import items.Item;

public class PrintBattleText {
	
	//TODO: ADD WAIT MILLISECONDS UTILITIES FOR TEXT VERSION and maybe gui
	
	public static void attackingText(boolean thisIsPlayer, boolean isGUI) {
		String text = "";
		if (thisIsPlayer) {
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
	
	public static void damageText(boolean thisIsPlayer, double damage, boolean isGUI) {
		String text = "";
		if (thisIsPlayer) {
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
	
	public static void critText(boolean thisIsPlayer, boolean isGUI) {
		String text = "";
		if (thisIsPlayer) {
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
	
	public static void missedText(boolean thisIsPlayer, boolean isGUI) {
		String text = "";
		if (thisIsPlayer) {
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
	
	public static void tauntedText(boolean thisIsPlayer, boolean isGUI) {
		String text = "";
		String text2 = "attack has been increased and defence decreased!";
		if (thisIsPlayer) {
			text = "You taunted the enemy! \nTheir" + text2;
		}
		else {
			text = "The enemy taunted you! \nYour" + text2;
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void slainEntity(boolean thisIsPlayer, boolean isGUI) {
		String text = "";
		if (!thisIsPlayer) {
			text = "You have slained the enemy!";
		}
		else {
			text = "The enemy slained you!";
		}
		
		if (isGUI) {
			//TODO: GUI BANANA
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void xpGain(Enemy enemy, boolean isGUI) {
		String text = "You have gained " + enemy.getExperienceGiven() + " experience!";
		
		if (isGUI) {
			//TODO WYLEEEEEE
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void gameOver(boolean isGUI) {
		String text = "You have died..."
				+ "\nThe light is slowly fading away..."
				+ "\nYou lost your conscienceness...";
		
		if (isGUI) {
			//TODO WYLEEEEEE
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void levelUp(Player player, boolean isGUI) {
		String text = "You have leveled up!" + "\nYou are now level " + player.getLevel() +"!";
		if (isGUI) {
			//TODO WYLEEEEEE
		}
		else {
			System.out.println(text);
		}
	}
	
	public static void itemText(Item item, boolean isGUI, double healed) {
		String text = "You used the " + item.getName() + "! \nYou healed " + healed + " health!";
		if (isGUI) {
			//TODO WYLEEEEEE
		}
		else {
			System.out.println(text);
		}
	}
}
