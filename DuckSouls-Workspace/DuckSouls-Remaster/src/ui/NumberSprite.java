package ui;

import animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


/**
 * Number sprite.
 * 
 * @author Wylee McAndrews
 *
 */
public class NumberSprite extends Pane{
	
	//Number imageView has one column and ten rows 0-9.
	//It only displays one image at a time (is not animated)
    ImageView imageView;
    int count = 1;
    int columns = 1;
    int offsetX = 0;
    int offsetY = 0;
    int width = 64;
    int height = 64;
    
    private static final int tileSize = 64;
    
    //Use SpriteAnimation to change the state of the button (selected/deselected)
    public SpriteAnimation animation;
    
    /**
     * MenuButton constructor.
     * Gets the button imageView, button type, and button position x-y.
     * 
     * @param imageView
     * 						The button imageView (2 frames)
     * @param buttonType
     * 						The type of button (What it does)
     * @param positionX
     * 						The X position of the button
     * @param positionY
     * 						The Y position of the button
     */
    public NumberSprite(ImageView imageView, 
    					int positionX,
    					int positionY)
    {
        this.imageView = imageView;
        
        //Set the viewport to the first frame
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        
        //The SpriteAnimation class will help with changing the viewed number
        animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, width, height);
        
       
        getChildren().add(imageView); //Add the imageView to the layer
        
        this.setTranslateX(positionX); //Translate the number to it's x-y position
        this.setTranslateY(positionY);
    }
    
    /**
     * Set which number is displayed on the imageView
     * 
     * @param number
     * 				The number to display
     */
   public void setNumber(int number) {
	   
	   animation.setOffsetY(number * tileSize);
	   
   }
    
}
