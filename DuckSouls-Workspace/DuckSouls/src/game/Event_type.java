package game;

public enum Event_type {
	/**
	 * This enumerator is used to define the type of events that exist This predefined the type
	 * of events that the game needs to hand
	 * 
	 * @author Colin Au Yeung
	 * @version 1.0
	 *
	 */
		
		BATTLE("battle"),
		NEXTWORLD("nextworld"),
		NOEVENT("null");
		
		public String str;
		
		private Event_type(String str) {
			this.str = str;
		}
		

}
