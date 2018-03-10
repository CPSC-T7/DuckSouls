package battle;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import animations.*; //sprite animations


/**
 * 
 * @author Wylee McAndrews
 * 
 * Basic test of the GUI Battle scree, and player animations.
 *
 */
public class BattleGuiTest extends Application {

	public void start(Stage mainStage) throws Exception {
		final int windowSize = 64 * 9;
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(windowSize, windowSize);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image battleBackground = new Image("Sprites/Backgrounds/Sewer-Battle.png");
		mainStage.setTitle("DuckSouls");
		mainStage.setScene(scene);
		
		Animation runLeft = new Run(true, -1, gc);
		Animation runRight = new Run(true, 1, gc);
		
		new AnimationTimer() {
			
			@Override
			public void handle(long args) {
				
				runRight.playAnimation();
				runLeft.playAnimation();
			}
			
		}.start();
		
		System.out.println("Animation Done");
		
		mainStage.show();
		
	}
    public static void main(String[] args) 
    {
        launch(args);
    }
}
