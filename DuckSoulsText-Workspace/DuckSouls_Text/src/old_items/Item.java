package old_items;

/**
 * Defines in game items and allows user to use those items in DuckSouls.
 * 
 * @author Cassondra Platel
 * @version 3.0.0
 *
 *TO DO:	complete a working useItem() method
 *			add private updateStat() method that gets/sets stats --> switch statement? should reduce code duplication found in useItem()
 *			add public hasWeapon and hasArmor to Duck_Object.java --> to check if player has item equipped
 *			add getStat() and setStat to Duck_Object.java --> switch statements
 *			create resetWeaponStats() and resetArmorStats() in Duck_Object --> reset players base stats for each respective category (aka unequipping the weapon or armor)
 *			Create Inventory parent with Store and Player child classes --> add updateInventory() to reflect using/buying a consumable item
 *			test useItem() at runtime
 *			
 */

//GRANDPARENT CLASS

public class Item {

// ITEM ATTRIBUTES

	private String itemName;

	private boolean canConsume = false;
	private boolean isWeapon = false;
	private boolean isArmor = false;

	private int healthDamage = 0;
	private int manaDamage = 0;
	private int attack = 0;
	private int defense = 0;
	private int speed = 0;
	private int accuracy = 0;
	private int critChance = 0;
	private int price = 0;

// ITEM METHODS

	/**
	 * Sets the name of an item.
	 * 
	 * @param identifier
	 *            Single word used for item identification.
	 */
	protected void setName(String identifier){

		switch (identifier){

			//consumable names
			case "bugs": this.itemName = "De Bug"; break;
			case "food": this.itemName = "Half-Eaten Food"; break;
			case "fish": this.itemName = "Smelly Fish"; break;
			case "cruton": this.itemName = "Soggy Cruton"; break;
			case "roids": this.itemName = "Suspicious Liquid"; break;		
			case "wax": this.itemName = "Dr.Quakenstein's Wonderous Wing Wax!"; break;
			
			//weapon names
			case "dagger": this.itemName = "Pointy Sticks"; break;
			case "knife": this.itemName = "Razor Blades"; break;
			case "bow": this.itemName = "Pew Pew"; break;
			case "shield": this.itemName = "Meat Shield"; break;
			case "sword": this.itemName = "Toy Sword"; break;
			case "dual": this.itemName = "Broken Toothpick"; break;
			case "staff": this.itemName = "Magic Stick"; break;
			
			//armor names
			case "mail": this.itemName = "Full Metal Jacket"; break;
			case "leather": this.itemName = "Mooskin"; break;
			case "robe": this.itemName = "Ugly Christmas Sweater"; break;
		}
	}//End of setName method


	/**
	 * Sets boolean attribute values.
	 * 
	 * @param identifier
	 * 			Single word used for item type identification.
	 * @param newValue
	 *			New value for the attribute.
	 */
	protected void setAttribute(String identifier, boolean newValue){

		switch(identifier){

			case "consume": this.canConsume = newValue; break;
			case "weapon": this.isWeapon = newValue; break;
			case "armor": this.isArmor = newValue; break;
		}
	}//End of setAttribute method
	

	/**
	 * Sets integer attribute values.
	 * 
	 * @param identifier
	 * 			Single word used for item type identification.
	 * @param newValue
	 *			New value for the attribute.
	 */	
	protected void setAttribute(String identifier, int newValue){

		switch(identifier){

			case "health": this.healthDamage = newValue; break;
			case "mana": this.manaDamage = newValue; break;
			case "attack": this.attack = newValue; break;
			case "defense": this.defense = newValue; break;
			case "speed": this.speed = newValue; break;
			case "accuracy": this.accuracy = newValue; break;
			case "crit": this.critChance = newValue; break;
			case "price": this.price = newValue; break;
		}
	}//End of setAttribute method


	/**
	 * Returns the full name of the item.
	 * 
	 * @return itemName
	 * 			Return the name of the item
	 */
	public String getName(){

		return this.itemName;
	}//End of getName method


	/**
	 * Returns boolean attribute values
	 * 
	 * @param identifier
	 * 			Single word used for item type identification
	 * @return value
	 * 			Return item value for identified attribute
	 */
	public boolean getBoolAttribute(String identifier){

		boolean value = false;

		switch(identifier){

			case "consume": value = this.canConsume; break;
			case "weapon": value = this.isWeapon; break;
			case "armor": value = this.isArmor; break;
		}
		return value;
	}//End of getBoolAttribute method


	/**
	 * Return integer attribute value
	 * 
	 * @param identifier
	 * 			Single word used for attribute identification.
	 * @return num
	 *			Return numerical value of identified attribute
	 */
	public int getAttribute(String identifier){

		int num = 0;
		switch(identifier){

			case "health": num = this.healthDamage; break;
			case "mana": num = this.manaDamage; break;
			case "attack": num = this.attack; break;
			case "defense": num = this.defense; break;
			case "speed": num = this.speed; break;
			case "accuracy": num = this.accuracy; break;
			case "crit": num = this.critChance; break;
			case "price": num =this.price; break;

		}
		return num;
	}//End of getAttribute method

//DID NOT FINISH A WORKING USEITEM METHOD WITH INHERITANCE
/*	public void useItem(Duck_Object player,String identifier){
		if (this.canConsume == true){
			int currentValue = player.getStats(identifier);
			int newValue = (currentValue += getAttribute(identifier));
			player.setStats(identifier,newValue);
		}
		else if (player.hasWeapon == true || player.hasArmor == true){
			this.removeEquipped(player);
			}//check if player already has weapon/armor equipped and remove/reduce stats as needed
		}
	}//end of useItem method
	protected removeEquipped(Duck_Object player){
		boolean weapon = this.getAttribute("weapon");
		boolean armor = this.getAttribute("armor");
		if (player.hasWeapon == true && weapon == true){
			player.resetWeaponStats();
			player.hasweapon = false;
		}
		else if (player.hasArmor == true && armor == true){
			player.resetArmorStats();
			player.hasArmor = false;
		}
	}//End of removeEquipped method
	protected void updateWeapon(Duck_Object player){
		player.hasWeapon = true;
			
		int currentValue = player.getStats("attack");
		int newValue = (currentValue += this.getAttribute("attack"));
		player.setStats("attack",newValue);
			
		int currentValue = player.getStats("defense");
		int newValue = (currentValue += this.getAttribute("defense"));
		player.setStats("defense",newValue);
			
		int currentValue = player.getStats("crit");
		int newValue = (currentValue += this.getAttribute("crit"));
		player.setStats("crit",newValue);
	}//End of updateWeapon method
*/

}//End of Item class


//CONSUMABLE PARENT AND CHILDREN

	class Consumable extends Item{

		Consumable(){super.setAttribute("consume",true);}
	}//End of Consumable class


	class Bugs extends Consumable{

		Bugs(){
			super.setName("bugs");
			super.setAttribute("health", 10);
			super.setAttribute("price", 10);
		}
	}//End of Bugs class


	class Food extends Consumable{

		Food(){
			super.setName("food");
			super.setAttribute("health", 15);
			super.setAttribute("mana", 5);
			super.setAttribute("price", 20);
		}
	}//End of Food class


	class Cruton extends Consumable{

		Cruton(){
			super.setName("cruton");
			super.setAttribute("health", 1);
			super.setAttribute("price", 100);
		}
	}//End of Cruton class


	class Roids extends Consumable{

		Roids(){
			super.setName("roids");
			super.setAttribute("health", 5);
			super.setAttribute("attack", 25);
			super.setAttribute("speed", 20);
			super.setAttribute("defense", -5);
			super.setAttribute("price", 50);
		}
	}//End of Roids class


	class Wax extends Consumable{

		Wax(){
			super.setName("wax");
			super.setAttribute("speed", 50);
			super.setAttribute("price", 20);
		}
	}//End of Wax class


//WEAPON PARENT AND CHILDREN

	class Weapon extends Item{

		Weapon(){super.setAttribute("weapon",true);}
	}//End of Weapon class


	class Daggers extends Weapon{
		
		Daggers(){
			super.setName("dagger");
			super.setAttribute("attack", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End of Daggers class


	class Knives extends Weapon{

		Knives(){
			super.setName("knife");
			super.setAttribute("attack", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End of Knives class
	

	class Bow extends Weapon{
		
		Bow(){
			super.setName("bow");
			super.setAttribute("attack", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End of Bow class
	
	//sword and shield
	class Shield extends Weapon{
		
		Shield(){
			super.setName("shield");
			super.setAttribute("attack", 15);
			super.setAttribute("defense", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End of Shield class
	
	//two-handed sword
	class Sword extends Weapon{
		
		Sword(){
			super.setName("sword");
			super.setAttribute("attack", 25);
			super.setAttribute("speed", 10);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 15);
			super.setAttribute("price", 20);
		}
	}//End of Sword class
	

	class Dual extends Weapon{
		
		Dual(){
			super.setName("dagger");
			super.setAttribute("attack", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End of Dual class
	

	class Staff extends Weapon{
		
		Staff(){
			super.setName("staff");
			super.setAttribute("attack", 15);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("crit", 10);
			super.setAttribute("price", 20);
		}
	}//End fo Staff class


//ARMOR PARENT AND CHILDREN

	class Armor extends Item{

		Armor(){super.setAttribute("armor",true);}
	}//End of Armor class


	class Mail extends Armor{
		
		Mail(){
			super.setName("mail");
			super.setAttribute("defense", 25);
			super.setAttribute("speed", 5);
			super.setAttribute("accuracy", 10);
			super.setAttribute("price", 20);
		}
	}//End of Mail class

	
	class Leather extends Armor{
		
		Leather(){
			super.setName("leather");
			super.setAttribute("defense", 15);
			super.setAttribute("speed", 10);
			super.setAttribute("accuracy", 15);
			super.setAttribute("price", 20);
		}
	}//End of Leather class

	
	class Robe extends Weapon{
		
		Robe(){
			super.setName("robe");
			super.setAttribute("defense", 5);
			super.setAttribute("speed", 25);
			super.setAttribute("accuracy", 20);
			super.setAttribute("price", 20);
		}
	}//End of Robe class
