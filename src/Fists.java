
public class Fists extends Weapon {

	protected String name = "Fists";
	protected String description = "Right & Lefty";
	
	public boolean canDrop() {
		return false;
	}
	
	public boolean showInBag() {
		return false;
	}
	
	@Override
	public int getDamage() {
		return 5;
	}

}
