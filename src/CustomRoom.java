
public class CustomRoom extends Room {
	private String description = "";
	private String location = "";
	
	public void addRoom(String l, Room r) {
		getAttachedRooms().put(l, r);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getLocation() {
		return location;
	}

}
