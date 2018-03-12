package ui;

import animations.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MenuButton extends Pane{
	
    ImageView imageView;
    private final String buttonType;
    int count = 1;
    int columns = 1;
    int offsetX = 0;
    int offsetY = 0;
    int width = 120;
    int height = 40;

    public SpriteAnimation animation;
    
    public MenuButton(ImageView imageView, 
    					String buttonType,
    					int positionX,
    					int positionY)
    {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
        this.buttonType = buttonType;
        
        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
    }
    
    public String getButtonType() {
    	
    	return(this.buttonType);
    }
    
}
