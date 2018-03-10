package animations;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Run extends Animation {

	private int 	frame 		= 0;		//The current animation frame
	private int		loop		= 0;		//The current animation loop
	private int 	numLoops	= 5;		//The number of run-loops to play
	private int 	position 	= 0;		//The moving sprite's position
	private int		translation	= 0;
	private boolean isPlayer;				//Whether the sprite is the player or not
	private int 	direction;				//The direction of the run movement
	private GraphicsContext gc;				//GraphcisContext
	
	private Image RUN_ONE = new Image("Sprites/Entities/Duck/Battle/Duck-Run-1.png");
	private Image RUN_TWO = new Image("Sprites/Entities/Duck/Battle/Duck-Run-2.png");
	private Image RUN_THREE = new Image("Sprites/Entities/Duck/Battle/Duck-Run-3.png");	
	
	
	public Run(boolean isPlayer, int direction, GraphicsContext gc) {
		this.isPlayer = isPlayer;
		this.direction = direction;
		this.gc = gc;
		
		if (direction == -1) {
			this.translation = 128;
		}
	}//End of Run constructor
	
	/**
	 * Play the Attack animation.
	 */
	public void playAnimation() {
		new AnimationTimer() {

			@Override
			public void handle(long args) {

				if (frame <= 5) {
					
					frame += 1;
					position += 4;
					gc.drawImage(BACKGROUND, 0, 0);
					gc.drawImage(RUN_ONE, position + translation, 64*3,
							RUN_ONE.getWidth()*direction, RUN_ONE.getHeight());
					
				}else if(frame <= 10) {

					frame += 1;
					position += 4;
					gc.drawImage(BACKGROUND, 0, 0);
					gc.drawImage(RUN_TWO, position + translation, 64*3,
							RUN_TWO.getWidth()*direction, RUN_TWO.getHeight());
				}else if(frame == 11) {
					
					//Check the number of loops done
					if(loop == numLoops) {
						stop(); //End the animation
					}else {
						frame = 0;
						loop += 1;
					}
				}
			}
		}.start();
	}//End of playAnimation
}
