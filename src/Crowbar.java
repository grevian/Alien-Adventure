
public class Crowbar extends Weapon {

	protected String name = "Crowbar";
	protected String description = "The weapon of a true scientist";
	
	public String getDescription() {
		return description;
	}
	
	public String getName() { 
		return name;
	}
	
	@Override
	public int getDamage() {
		return 8;
	}
	

}
