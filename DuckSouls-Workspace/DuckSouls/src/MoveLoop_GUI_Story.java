import Gane.Controller_GUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class MoveLoop_GUI_Story extends Application {	

	private int windowSize = 64*7;
	
	/*
	 * Start of JavaFX
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(this.windowSize, this.windowSize);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainStage.setTitle("DuckSouls");
		mainStage.setScene(scene);
		mainStage.show();
		
		Controller_GUI tl = new Controller_GUI();
		tl.mainloop(gc, scene);
	}
}