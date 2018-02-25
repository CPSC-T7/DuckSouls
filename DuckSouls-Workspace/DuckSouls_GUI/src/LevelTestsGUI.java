import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import mattTiles.*;

public class LevelTestsGUI extends Application {

	@Override
	public void start(Stage testStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(800, 800);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		testStage.setTitle("Test Window");
		testStage.setScene(scene);
		testStage.show();

		// Add tiles to the 2D room array
		Tile[][] roomTiles = new Tile[9][9];
		// Tile rows
		for (int i = 0; i <= 8; i++) {
			// Tile columns
			for (int j = 0; j <= 8; j++) {
				roomTiles[i][j] = new Tile(new Image("Tiles/Sewer/Floor.png"));
			}
		}

		drawRoom(gc, roomTiles);
	}// End of start

	private void drawRoom(GraphicsContext gc, Tile[][] roomTiles) {
		// X and Y position of the tiles
		int[] position = new int[] { 0, 0 };
		// Draw each tile for the room
		// Tile rows
		for (int i = 0; i <= 8; i++) {
			// Tile columns
			for (int j = 0; j <= 8; j++) {
				roomTiles[i][j].drawTile(gc, position);
				position[0] += 64;
			}
			position[0] = 0;
			position[1] += 64;
		}
	}// End of drawRoom

}
