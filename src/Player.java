import java.util.Hashtable;


public class Player {

	private int health = 100;
	private String name = "Unknown";
	private Weapon equippedWeapon = new Fists();
	
	private Room currentRoom;
	
	private Hashtable<String, HoldableObject> backpack = new Hashtable<String, HoldableObject>();
	
	public void pickupItem(HoldableObject h) {
		backpack.put(h.getName(), h);
	}
	
	public void listBag() {
		for ( HoldableObject h: backpack.values() ) {
			if ( h.showInBag() )
				System.out.println(h.getName() + " -- " + h.getDescription());
		}
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public Weapon getCurretWeapon() {
		return equippedWeapon;
	}
	
	
	
}
