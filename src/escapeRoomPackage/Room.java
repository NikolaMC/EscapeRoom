package escapeRoomPackage;

import java.io.Serializable;

public class Room implements Serializable {
	
	private String name;
	private String description;
	private Inventory inventory;
	private Person[] person;
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.inventory = new Inventory(4);
		this.person = new Person[5];
	}
	
	public String getName() {
		return name;
	}
	
	// Return the room's name, description and inventory
	public String getRoom() {
		String room = String.format("%s - %s\nObjects in the room - %s", name, description, inventory.getNames());
		return room;
	}
	
	// Add an NPC to the room
	public void addNpc(Person person) {
		this.person[0] = person;
	}
	
	// Get the NPC's in the room
	public Person getPeople() {
		return this.person[0];
	}
	
	// Get the room's inventory
	public Inventory getInventory() {
        return this.inventory;
    }
	
	// Add an object to the room
	public void addObject(GameObject go) {
		this.inventory.addObject(go);
	}
	
	// Remove an object from the room
	public void removeObject(GameObject go) {
		this.inventory.removeObject(go);
	}
	
	// Move an object from this room to the entered inventory
	public void moveObject(Inventory i2, GameObject go) {
		this.inventory.moveObject(i2, go);
	}
	
}
