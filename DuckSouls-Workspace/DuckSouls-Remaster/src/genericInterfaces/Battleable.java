package genericInterfaces;

import java.util.Random;

public interface Battleable {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	// public static final String BATTLE_SPRITE_FOLDER_PATH = ??? ; TODO: Fill in.
	public static final double DAMAGE_RANGE_MOD = 0.1;
	public static final Random _RANDOM = new Random();
	
	/*
	 * 
	 * ABSTRACTS
	 * 
	 */
	
	/**
	 * Takes into account the entity's statistics and weapon to randomly decide an
	 * integer amount of damage to deal when told to attack in battle.
	 * 
	 * @return The amount of damage the entity should deal.
	 */
	public abstract double sendAttack();
	
	/**
	 * Takes into account the entity's statistics and armour to decide how much
	 * damage to take when attacked in battle.
	 * 
	 * @param damage
	 *            The amount of damage the other entity dealt to this enemy.
	 */
	public abstract void receiveAttack(double damage);
	
	
}
