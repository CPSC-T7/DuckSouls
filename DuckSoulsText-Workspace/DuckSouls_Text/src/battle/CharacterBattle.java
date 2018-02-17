package battle;


/**
 * 
 * @author Rahmanta Satriana
 *
 */

public class CharacterBattle {
	
	// Player Stats (All fixed values for DEMO 1)
	// Stats for resetting (Will probably be set through a constructor later)
	protected double			healthPointsStatic;
	protected double			manaPointsStatic;
	protected double			attackPointsStatic;
	protected double			defencePointsStatic;
	protected double			speedPointsStatic;
	protected double			accuracyPointsStatic;
	protected double			criticalHitPointsStatic;
		
	// Stats that will change during the battle
	protected double			healthPoints;
	protected double			manaPoints;
	protected double			attackPoints;
	protected double			defencePoints;
	protected double			speedPoints;
	protected double			accuracyPoints;
	protected double			criticalHitPoints;

	
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
	
	
	
	public void setStats(String stat, double newVal) {
		
		switch (stat) {
		
		case "healthPoints":
			healthPoints = newVal;
			
		case "manaPoints":
			manaPoints = newVal;
			
		case "attackPoints":
			attackPoints = newVal;
			
		case "defencePoints":
			defencePoints = newVal;
			
		case "speedPoints":
			speedPoints = newVal;
			
		case "accuracyPoints":
			accuracyPoints = newVal;
			
		case "criticalHitPoints":
			criticalHitPoints = newVal;
			
		//Static time	
		
		case "healthPointsStatic":
			healthPointsStatic = newVal;
			
		case "manaPointsStatic":
			manaPointsStatic = newVal;
			
		case "attackPointsStatic":
			attackPointsStatic = newVal;
			
		case "defencePointsStatic":
			defencePointsStatic = newVal;
			
		case "speedPointsStatic":
			speedPointsStatic = newVal;
			
		case "accuracyPointsStatic":
			accuracyPointsStatic = newVal;
			
		case "criticalHitPointsStatic":
			criticalHitPointsStatic = newVal;
		
			
			
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
		return 0.0;
		
	}
}
