package escapeRoomPackage;

public abstract class Npc {
	String name;
    Inventory inventory;

    public Npc(String name) {
        this.name = name;
        this.inventory = new Inventory(1);
    }
    
    // Add an object to the NPC's inventory
    public void addObject(GameObject go) {
		this.inventory.addObject(go);
	}
    
    // Remove an object from the NPC's inventory
    public void removeObject(GameObject go) {
		this.inventory.removeObject(go);
	}
	
    // Move an object from the NPC's inventory to the entered inventory
	public void moveObject(Inventory i2, GameObject go) {
		this.inventory.moveObject(i2, go);
	}

	// Returns this NPC's inventory
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public String toString() {
        return this.name + " is carrying " + this.inventory.getNames();
    }
}
