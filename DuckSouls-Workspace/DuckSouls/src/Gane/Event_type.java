package Gane;

public enum Event_type {
	/**
	 * This enumerator is used to define the four cardinal directions. This prevents
	 * having to pass characters or stings to determine direction.
	 * 
	 * @author Matthew Allwright
	 * @version 1.2.1
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
