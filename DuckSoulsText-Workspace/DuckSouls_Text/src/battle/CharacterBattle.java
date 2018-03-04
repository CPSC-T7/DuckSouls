package battle;

import java.util.Random;

/**
 * 
 * @author Rahmanta Satriana
 *
 */

public class CharacterBattle {
	
	// Player Stats (All fixed values for DEMO 1)
	// Stats for resetting (Will probably be set through a constructor later)
	private double			healthPointsStatic;
	private double			manaPointsStatic;
	private double			attackPointsStatic;
	private double			defencePointsStatic;
	private double			speedPointsStatic;
	private double			accuracyPointsStatic;
	private double			criticalHitPointsStatic;
		
	// Stats that will change during the battle
	private double			healthPoints;
	private double			manaPoints;
	private double			attackPoints;
	private double			defencePoints;
	private double			speedPoints;
	private double			accuracyPoints;
	private double			criticalHitPoints;
	
	/**
	 * Main class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	
	public CharacterBattle (double health, double mana, double attack, double defence, double speed, double accuracy, double crit) {
		healthPoints = health;
		manaPoints = mana;
		attackPoints = attack;
		defencePoints = defence;
		speedPoints = speed;
		accuracyPoints = accuracy;
		criticalHitPoints = crit;


		healthPointsStatic = health;
		manaPointsStatic = mana;
		attackPointsStatic = attack;
		defencePointsStatic = defence;
		speedPointsStatic = speed;
		accuracyPointsStatic = accuracy;
		criticalHitPointsStatic = crit;
	}
	
	public double attackBonus(double damage) {
		
		Random rand = new Random();
		//Create a random object
		int bonus = rand.nextInt(11) - 5;
		damage += bonus;
		return damage;
	}
	
	
	
	public void setStats(String stat, double newVal) {
		
		switch (stat) {
		
		case "healthPoints":
			healthPoints = newVal;
			break;
			
		case "manaPoints":
			manaPoints = newVal;
			break;
			
		case "attackPoints":
			attackPoints = newVal;
			break;
			
		case "defencePoints":
			defencePoints = newVal;
			break;
			
		case "speedPoints":
			speedPoints = newVal;
			break;
			
		case "accuracyPoints":
			accuracyPoints = newVal;
			break;
			
		case "criticalHitPoints":
			criticalHitPoints = newVal;
			break;
			
		//Static time	
		
		case "healthPointsStatic":
			healthPointsStatic = newVal;
			break;
			
		case "manaPointsStatic":
			manaPointsStatic = newVal;
			break;
			
		case "attackPointsStatic":
			attackPointsStatic = newVal;
			break;
			
		case "defencePointsStatic":
			defencePointsStatic = newVal;
			break;
			
		case "speedPointsStatic":
			speedPointsStatic = newVal;
			break;
			
		case "accuracyPointsStatic":
			accuracyPointsStatic = newVal;
			break;
			
		case "criticalHitPointsStatic":
			criticalHitPointsStatic = newVal;
			break;
		
			
			
		}
		
	}
	
	public double getStats(String stat) {
		
		
		switch (stat) {
		
		case "healthPoints":
			return healthPoints;
			
		case "manaPoints":
			return manaPoints;
			
		case "attackPoints":
			return attackPoints;
			
		case "defencePoints":
			return defencePoints;
			
		case "speedPoints":
			return speedPoints;
			
		case "accuracyPoints":
			return accuracyPoints;
			
		case "criticalHitPoints":
			return criticalHitPoints;
			
		//Static time	
		
		case "healthPointsStatic":
			return healthPointsStatic;
			
		case "manaPointsStatic":
			return manaPointsStatic;
			
		case "attackPointsStatic":
			return attackPointsStatic;
			
		case "defencePointsStatic":
			return defencePointsStatic;
			
		case "speedPointsStatic":
			return speedPointsStatic;
			
		case "accuracyPointsStatic":
			return accuracyPointsStatic;
			
		case "criticalHitPointsStatic":
			return criticalHitPointsStatic;
				
		}
		return 0;
		
		
	}
	
	public void resetStats() {
		healthPoints = healthPointsStatic + 0;
		manaPoints = manaPointsStatic + 0;
		attackPoints = attackPointsStatic + 0;
		defencePoints = defencePointsStatic + 0;
		speedPoints = speedPointsStatic + 0;
		accuracyPoints = accuracyPointsStatic + 0;
		criticalHitPoints = criticalHitPointsStatic + 0;
	}
}
