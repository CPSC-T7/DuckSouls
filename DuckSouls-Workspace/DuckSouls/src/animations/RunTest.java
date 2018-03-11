package animations;

import javafx.animation.Transition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * The animation for running up to, or away from the enemy.
 * 
 * @author Wylee
 *
 */
public class RunTest extends Transition {

	private final Image RUN_ONE = new Image("Sprites/Entities/Duck/Battle/Duck-Run-1.png");
	private final Image RUN_TWO = new Image("Sprites/Entities/Duck/Battle/Duck-Run-2.png");
	private Image currentImage = RUN_ONE;
	private GraphicsContext gc;				//GraphcisContext
	
	public RunTest() {
		setCycleDuration(Duration.millis(500));
	}
		
	@Override
	protected void interpolate(double arg0) {
		if (currentImage == RUN_ONE) {
			gc.drawImage(RUN_ONE, 0, 0);
			currentImage = RUN_TWO;
		}else {
			gc.drawImage(RUN_TWO, 0, 0);
			currentImage = RUN_ONE;
		}
	}
}
