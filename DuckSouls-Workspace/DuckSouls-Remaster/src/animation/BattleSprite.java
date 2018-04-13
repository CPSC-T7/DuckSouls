package animation;

import animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * The player and enemy sprite during the battle screen.
 * 
 * @author Wylee McAndrews
 *
 */
public class BattleSprite extends Pane{
	
	//Constants   
	
	private final int width = 128;		//Width of each frame
    private final int height = 128;		//Height of each frame
    private final int count = 2;			//Number of total images per animation
    private final int columns = 2;		//Number of columns in the spritesheet
    
	public final int IDLE_POSITION = 0;
	public final int RUN_RIGHT_POSITION = 1 * width;
	public final int RUN_LEFT_POSITION = 2 * width;
	public final int ATTACK_POSITION = 3 * width;
	public final int HURT_POSITION = 4 * width;
	public final int DEAD_POSITION = 5 * width;

	// Sprite animation attributes
    private ImageView imageView; 	//The sprite sheet object for the animation
    private int offsetX = 0;		//ImageView x offset
    private int offsetY = 0;		//ImageView y offset

    
    //Set the Y translation to 3 tiles down from the top of the screen for all battle sprites.
    private final int spriteYTranslation = 64*3;
    
    //Access SpriteAnimation properties from anywhere.
    public SpriteAnimation animation;
    
    /**
     * BattleSprite constructor
     * 
     * @param imageView
     * 					The imageview SpriteSheet for the sprite
     * @param translateX
     * 					The default X translation on the screen
     */
    public BattleSprite(ImageView imageView, int translateX){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, 128, 128);
        getChildren().addAll(imageView);
        
        this.setTranslateY(spriteYTranslation);
        this.setTranslateX(translateX);
    }

    /**
     * Translates the sprite left and right on the screen.
     * 
     * @param x
     * 				The amount to move the player/enemy sprite
     */
    public void moveX(int x){
        this.setTranslateX(this.getTranslateX() + x);
    }
    
}