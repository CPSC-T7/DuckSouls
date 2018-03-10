package animations;

import javafx.scene.image.Image;

public class Animation {

	//Background is protected, so that it may be used by subclasses
	protected Image BACKGROUND = new Image("Sprites/Backgrounds/Sewer-Battle.png");
	
	/**
	 * Method to play an animation.
	 * Can control multiple sprites at the same time.
	 */
	public void playAnimation() {
		System.out.println("No animation yet!");
	}
}
