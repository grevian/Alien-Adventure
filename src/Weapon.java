
public abstract class Weapon implements HoldableObject {
	private String name;
	private String description;
	
	public abstract int getDamage();
	
	public boolean canDrop() {
		return true;
	}
	
	public boolean showInBag() {
		return true;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public int getDamage(Enemy e) {
		return getDamage();
	}

}
