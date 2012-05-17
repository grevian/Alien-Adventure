import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Room {

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<HoldableObject> items = new ArrayList<HoldableObject>();
	private Hashtable<String, Room> attachedRooms = new Hashtable<String, Room>();

	public abstract String getDescription();
	public abstract String getLocation();
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public Hashtable<String, Room> getAttachedRooms() {
		return attachedRooms;
	}
	
	public ArrayList<HoldableObject> getItems() {
		return items;
	}
	
}
