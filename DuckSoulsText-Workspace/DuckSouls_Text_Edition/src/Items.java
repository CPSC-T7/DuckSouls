public class Items {

	private ItemType item;

	// CONSTRUCTORS

	Item greatsword = new Item(ItemType.sword);

	Item(ItemType item){

		this.item = item;

	}

	// ENUMERATORS

	public enum ItemType(){
		POTION,
		SWORD,
		ETC
	}
	
	public enum Consumable {

		BUGS (false,true,20, 0)
		,ET,FISH,FOOD,PRESCRIPTION,WAX;

		private boolean CAN_EQUIP;
		private boolean CAN_CONSUME;
		private int HEALTH_DAM;
		private int MANA_DAM;

		private Consumable(boolean CAN_EQUIP, boolean CAN_CONSUME, int HEALTH_DAM, int MANA_DAM){

			this.CAN_EQUIP = CAN_EQUIP;
			
		}
	}
	public enum Weapon{

		PLACEHOLDER
	}
	public enum Armor{

		PLACEHOLDER
	}

	public void useItem(Item item) {

		//check character inventory number?
		
		//update HP/MP
		//update item count in player inventory
	}