import story_map.Map;
import utils.Utilities;

public class MoveLoop_Text_Story {
	
	public static void play() {

		Map map = new Map();
		map.initalization(0, 2);
		Utilities.clearConsole();
		map.mainloop();
		
	}
	
}
