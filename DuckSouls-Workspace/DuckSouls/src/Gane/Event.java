package Gane;

import items.Clothes;
import items.Goo;
import items.Item;

public class Event {

	private Event_type type = Event_type.NOEVENT;
	private String nextworld = null;
	private Item item = new Goo();
	private Item Weapon = new Clothes();
	private Item Armour = new Clothes();
	
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
	
	public Event(Event_type type, Item weapon, Item armour) {
		this.type = type;
		this.Weapon = weapon;
		this.Armour = armour;
	}
	
	public void setEvent(Event event) {
		this.type = event.getType();
		this.Weapon = event.getWeapon();
		this.nextworld = event.getNextworld();
	}

	public String getNextworld() {
		return nextworld;
	}

	public Event_type getType() {
		return type;
	}
	
	public Item getWeapon() {
		return new Item(Weapon);
	}
	
	public Item getArmour() {
		return new Item(Armour);
	}



}
