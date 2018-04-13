package ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Non-animated sprite.
 * Great for messages!
 * 
 * @author Wylee McAndrews
 *
 */
public class StaticSprite extends Pane{

    /**
     * StaticSprite constructor.
     * Gets the imageView and position x-y.
     * 
     * @param imageView
     * 						The imageView
     * @param positionX
     * 						The X position
     * @param positionY
     * 						The Y position
     */
    public StaticSprite(ImageView imageView, 
    					int width,
    					int height,
    					int positionX,
    					int positionY)
    {   
        //Set the viewport to the first frame
        imageView.setViewport(new Rectangle2D(0,0,width,height));
       
        getChildren().add(imageView); //Add the imageView to the layer
        
        setTranslateX(positionX); //Translate the sprite to it's position
        setTranslateY(positionY);
    }
}
