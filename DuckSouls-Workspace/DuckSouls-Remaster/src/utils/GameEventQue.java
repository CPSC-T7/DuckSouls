package utils;

import java.util.ArrayList;

public class GameEventQue {
	
	private static ArrayList<GameEvent> eventQue = new ArrayList<GameEvent>();
	
	public static boolean hasEvent() {
		return eventQue.size() > 0;
	}
	
	/**
	 * Once called, returns and removes the first item in the event que.<br>
	 * <b> THE EVENT MUST BE HANDLED ONCE THIS IS CALLED OR IT WILL BE IGNORED!</b>
	 * 
	 * @return
	 */
	public static GameEvent handleNextEvent() {
		GameEvent nextEvent = eventQue.get(0);
		eventQue.remove(nextEvent);
		return nextEvent;
	}
	
	public static GameEvent nextEventType() {
		return eventQue.get(0);
	}
	
	public static void addEvent(GameEvent event) {
		eventQue.add(event);
	}
	
}
