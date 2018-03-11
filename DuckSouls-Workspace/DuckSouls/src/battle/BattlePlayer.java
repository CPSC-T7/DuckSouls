package battle;

import animations.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
 * The player sprite during the battle screen.
 */
public class BattlePlayer extends Pane{

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
    public void moveX(int x){
        boolean right = x>0?true:false;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
    }
    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
    }
}