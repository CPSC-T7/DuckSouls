import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Tile {

	private Image tileImage;
	
	public Tile(Image image)
	{
		tileImage = image;
	}
	
	public void drawTile(GraphicsContext gc, int[] position)
	{
		gc.drawImage(tileImage, position[0], position[1]);
	}

}
