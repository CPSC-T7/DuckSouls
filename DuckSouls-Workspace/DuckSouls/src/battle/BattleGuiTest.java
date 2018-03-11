package battle;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import animations.*; //sprite animations


/**
 * 
 * @author Wylee McAndrews
 * 
 * Basic test of the GUI Battle screen, and player animations.
 *
 */
public class BattleGuiTest extends Application {
	
	public void start(Stage mainStage) throws Exception {
		final int windowSize = 64 * 9;
		
		Canvas canvas = new Canvas(windowSize, windowSize);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		mainStage.setTitle("DuckSouls");
		
		final Image battleBackgroundImage = new Image("Sprites/Backgrounds/Sewer-Battle.png");
	    final ImageView battleBackground = new ImageView(battleBackgroundImage);
		
		
		final Image playerIdleImage = new Image("Sprites/Entities/Duck/Battle/Duck.png");
	    final ImageView playerIdle = new ImageView(playerIdleImage);
	    
	    final Group root = new Group();
	    final Pane backgroundLayer = new Pane();
	    final Pane playerLayer = new Pane();
	    final Pane groupPane = new Pane(backgroundLayer, playerLayer);
	    
	    
	    root.getChildren().add(groupPane);
	    
	    backgroundLayer.getChildren().add(battleBackground);
	    
	    BattlePlayer player = new BattlePlayer(playerIdle);
		playerLayer.getChildren().add(player);
		
		
		
		Scene scene = new Scene(root, windowSize, windowSize);
		
		//Animation runLeft = new Run(true, -1, gc);
		//Animation runRight = new Run(true, 1, gc);
		//final Animation animation = new SpriteAnimation(imageView);
		//animation.setCycleCount(Animation.INDEFINITE);


		
		player.animation.play();
		
		
		mainStage.setScene(scene);
		mainStage.show();
	}
    public static void main(String[] args) 
    {
        launch(args);
    }
}
