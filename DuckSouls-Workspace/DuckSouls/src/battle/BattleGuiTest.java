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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import map.GUILevel;
import tiles.Stairs;
import utils.Orientation;
import utils.Utilities;

import java.awt.Point;

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
		
		player.animation.play();	
		
		mainStage.setScene(scene);
		mainStage.show();
		
		
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				update(scene, player);
				
			}
			
		};
		timer.start();
		
	}
	public void update(Scene scene, BattlePlayer player) {
		
		// Do the action inputed by the user
		scene.setOnKeyPressed(key -> {
			
			if (key.getCode() == KeyCode.W) { // NORTH
				
				player.animation.play();
				player.animation.setOffsetY(0);
				
			} else if (key.getCode() == KeyCode.A) { // WEST
				
				player.animation.play();
				player.animation.setOffsetY(256);
				
			} else if (key.getCode() == KeyCode.S) { // SOUTH

				
			} else if (key.getCode() == KeyCode.D) { // EAST
				
				player.animation.play();
				player.animation.setOffsetY(128);
				player.moveX(8);
			}
		});
		
	}
    public static void main(String[] args) 
    {
        launch(args);
    }
}
