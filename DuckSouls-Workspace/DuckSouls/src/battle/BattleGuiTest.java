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
		
		runAnimation(gc, battleBackground, 2, -1, 0);
		runAnimation(gc, battleBackground, 2, 1, 64);
		
		mainStage.show();
		
	}
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public void runAnimation(GraphicsContext gc, Image battleBackground, int numLoops, int direction, int translation) {
    	new AnimationTimer() {
    		int loops = 0;
			int frame = 0;
			int position = 0;

			Image duckRun1 = new Image("Sprites/Entities/Duck/Battle/Duck-Run-1.png");
			Image duckRun2 = new Image("Sprites/Entities/Duck/Battle/Duck-Run-2.png");
			Image duckRun3 = new Image("Sprites/Entities/Duck/Battle/Duck-Run-3.png");
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
					if(frame < 0){
						
					}else if (frame <= 5) {
					frame += 1;
					position += 4;
					gc.drawImage(battleBackground, 0, 0);
					gc.drawImage(duckRun1, 0 + position + translation, 64*3,
							duckRun1.getWidth()*direction, duckRun1.getHeight());
				}else if(frame <= 10) {
					frame += 1;
					position += 4;
					gc.drawImage(battleBackground, 0, 0);
					gc.drawImage(duckRun2, 0 + position + translation, 64*3,
							duckRun2.getWidth()*direction, duckRun2.getHeight());
				}else if(frame <= 15) {
					frame += 1;
					position += 4;
					gc.drawImage(battleBackground, 0, 0);
					gc.drawImage(duckRun3, 0 + position + translation, 64*3,
							duckRun3.getWidth()*direction, duckRun3.getHeight());
				}else if(frame > 15) {
					loops += 1;
					frame = 0;
					position += 4;
					gc.drawImage(battleBackground, 0, 0);
					gc.drawImage(duckRun1, 0 + position + translation, 64*3,
							duckRun1.getWidth()*direction, duckRun1.getHeight());
					if(loops == numLoops) {
						frame = -1;
					}
				}
			}
		}.start();
		
    }
}
