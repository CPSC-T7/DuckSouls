package Gane;

import items.Clothes;
import items.Item;

public class Event {

	private Event_type type = Event_type.NOEVENT;
	private String nextworld = null;
	private Item item = new Clothes();
	private Item item2 = new Clothes();
	
	public Event(Event_type type, String nextworld) {
		this.type = type;
		this.nextworld = nextworld;
		
	}
	
	public Event(Event_type type) {
		this.type = type;
	}
	
	public Event(Event_type type, Item item) {
		this.type = type;
		this.item = new Item(item);
	}
	
	public Event(Event_type type, Item item, Item item2) {
		this.type = type;
		this.item = item;
		this.item2 = item2;
	}
	
	public void setEvent(Event event) {
		this.type = event.getType();
		this.item = event.getItem();
		this.nextworld = event.getNextworld();
	}

	public String getNextworld() {
		return nextworld;
	}

	public Event_type getType() {
		return type;
	}
	
	public Item getItem() {
		return new Item(item);
	}
	
	public Item getItem2() {
		return new Item(item2);
	}



}
