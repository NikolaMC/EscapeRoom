package escapeRoomPackage;

public class Room {
	
	private String name;
	private String description;
	private Inventory inventory;
	private Person[] person;
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.inventory = new Inventory(3);
		this.person = new Person[5];
	}
	
	public String getName() {
		return name;
	}
	
	public String getRoom() {
		String room = String.format("%s - %s\nObjects in the room - %s", name, description, inventory.getNames());
		return room;
	}
	
	public void addNpc(Person person) {
		this.person[0] = person;
	}
	
	public Person getPeople() {
		return this.person[0];
	}
	
	public Inventory getInventory() {
        return this.inventory;
    }
	
	public void addObject(GameObject go) {
		this.inventory.addObject(go);
	}
	
	public void removeObject(GameObject go) {
		this.inventory.removeObject(go);
	}
	
	public void moveObject(Inventory i2, GameObject go) {
		this.inventory.moveObject(i2, go);
	}
	
}
