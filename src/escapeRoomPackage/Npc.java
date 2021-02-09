package escapeRoomPackage;

public abstract class Npc {
	String name;
    Inventory inventory;

    public Npc(String name) {
        this.name = name;
        this.inventory = new Inventory(1);
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

    public Inventory getInventory() {
        return this.inventory;
    }
    
    public String toString() {
        return this.name + " is carrying " + this.inventory.getNames();
    }
}
