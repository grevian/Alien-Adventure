
public abstract class Enemy {
	
	private int health;
	private String name;
	private String description;
	
	private int damage;
	
	public int getHealth() {
		return health;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDamage() {
		return damage;
	}

	public void setHealth(int i) {
		this.health = i;	
	}

}
