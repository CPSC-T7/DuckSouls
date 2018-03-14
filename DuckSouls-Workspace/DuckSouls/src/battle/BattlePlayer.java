package battle;

import animations.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The player sprite during the battle screen.
 * 
 * @author Wylee McAndrews
 *
 */
public class BattlePlayer extends Pane{

	//BattlePlayer imageView has frames of animation (1 per column)
    ImageView imageView;
    int count = 2;
    int columns = 2;
    int offsetX = 0;
    int offsetY = 0;
    int width = 128;
    int height = 128;
    int score = 0;

    SpriteAnimation animation;
    public BattlePlayer(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, 128, 128);
        getChildren().addAll(imageView);
        
        this.setTranslateY(64*3);
    }

    /**
     * Translates the player's sprite left and right on the screen.
     * 
     * @param x
     * 				The amount to move the player's sprite
     */
    public void moveX(int x){
        this.setTranslateX(this.getTranslateX() + x);
    }
    
}