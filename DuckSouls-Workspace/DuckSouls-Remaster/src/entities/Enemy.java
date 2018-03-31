package entities;

import java.awt.Point;
import java.util.Random;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static final double	BASE_HEALTH			= 20;
	private static final double	BASE_ATTACK			= 6;
	private static final double	BASE_DEFENCE		= 5;
	private static final double	BASE_SPEED			= 5;
	private static final double	BASE_ACCURACY		= 71;
	private static final double	BASE_CRIT			= 16;
	private static final int	BASE_GIVEN_XP		= 10;
	private static final int	SCORE				= 10;
	
	private static final int	HEALTH_PER_LEVEL	= 2;
	private static final int	ATTACK_PER_LEVEL	= 1;
	private static final int	DEFENCE_PER_LEVEL	= 2;
	private static final int	SPEED_PER_LEVEL		= 1;
	private static final int	ACCURACY_PER_LEVEL	= 2;
	private static final int	CRIT_PER_LEVEL		= 2;
	private static final int	GIVEN_XP_PER_LEVEL	= 2;
	
	private static final String	STRING_REPR			= " E ";
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private int					experienceGiven;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Enemy(Point position, int level) {
		
		super(STRING_REPR, (BASE_HEALTH + (HEALTH_PER_LEVEL * (level - 1))),
				(BASE_ATTACK + (ATTACK_PER_LEVEL * (level - 1))), (BASE_DEFENCE + (DEFENCE_PER_LEVEL * (level - 1))),
				(BASE_SPEED + (SPEED_PER_LEVEL * (level - 1))), (BASE_ACCURACY + (ACCURACY_PER_LEVEL * (level - 1))),
				(BASE_CRIT + (CRIT_PER_LEVEL * (level - 1))));
		
		this.experienceGiven = BASE_GIVEN_XP + (GIVEN_XP_PER_LEVEL + (level - 1));
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return new Image(ENTITY_SPRITE_FOLDER_PATH + "Rat/Rat-" + this.orientation.STR + ".png");
	}
	
	@Override
	public int choice(int a) {
		Random random = new Random();
		int move = random.nextInt(4);
		
		int command = super.choice(move);
		return command;
	}
	
	public int getExperienceGiven() {
		return experienceGiven;
	}
	
	public int getScore() {
		return SCORE;
	}
	
}
