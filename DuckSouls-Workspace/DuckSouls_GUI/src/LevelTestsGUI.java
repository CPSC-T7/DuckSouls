import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import map.GUILevel;

public class LevelTestsGUI extends Application {	

	private int windowSize = 800;
	
	/*
	 * Start of JavaFX
	 */
	@Override
	public void start(Stage testStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(this.windowSize, this.windowSize);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		testStage.setTitle("DuckSouls");
		testStage.setScene(scene);
		testStage.show();
		
		GUILevel tl = new GUILevel();
		tl.moveLoop(gc, scene);
	}
}
