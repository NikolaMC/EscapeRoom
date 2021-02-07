package escapeRoomPackage;

public class Room {
	
	private int id;
	private String name;
	private String description;
	
	public Room(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRoom() {
		String room = String.format("%s - %s", name, description);
		return room;
	}
	
}
