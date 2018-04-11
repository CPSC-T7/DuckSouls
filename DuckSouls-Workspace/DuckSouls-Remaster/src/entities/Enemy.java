package entities;

import java.awt.Point;
import java.util.Random;
import ai.NotAvaliablePathExecption;
import ai.Path;
import javafx.scene.image.Image;
import utils.Orientation;
import world.Room;

public class Enemy extends Entity {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static final double	BASE_HEALTH			= 15;
	private static final double	BASE_ATTACK			= 3;
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
	private int					level;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Enemy(Point position, int level) {
		
		super(STRING_REPR, position, (BASE_HEALTH + (HEALTH_PER_LEVEL * (level - 1))),
				(BASE_ATTACK + (ATTACK_PER_LEVEL * (level - 1))), (BASE_DEFENCE + (DEFENCE_PER_LEVEL * (level - 1))),
				(BASE_SPEED + (SPEED_PER_LEVEL * (level - 1))), (BASE_ACCURACY + (ACCURACY_PER_LEVEL * (level - 1))),
				(BASE_CRIT + (CRIT_PER_LEVEL * (level - 1))));
		
		this.level = level;
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
	public String choice(int a) {
		Random random = new Random();
		int move = random.nextInt(4);
		
		String command = super.choice(move);
		return command;
	}
	
	public int getExperienceGiven() {
		return experienceGiven;
	}
	
	public int getScore() {
		return SCORE;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}
	
	public void move(Room room, Point playerPoint) {
		try {
			Path path = new Path(room, this.getPosition(), playerPoint);
			Point thisPoint = path.getNext(); 
			if(thisPoint.x > this.getPosition().x) {
				this.setOrientation(Orientation.EAST);
			}
			else {
				if(thisPoint.x < this.getPosition().x) {
					this.setOrientation(Orientation.WEST);
				}
				else {
					if(thisPoint.y > this.getPosition().y) {
						this.setOrientation(Orientation.SOUTH);
					}
					else {
						this.setOrientation(Orientation.NORTH);
					}
				}
			}
			this.setPosition(path.getNext());
		} catch (NotAvaliablePathExecption e) {
			Random rand = new Random();
			int R = rand.nextInt(4);
			switch(R) {
				case 0: this.setOrientation(Orientation.NORTH); break;
				case 1: this.setOrientation(Orientation.SOUTH); break;
				case 2: this.setOrientation(Orientation.EAST); break;
				case 4: this.setOrientation(Orientation.WEST); break;
			}
		}
		
	}
	
}
