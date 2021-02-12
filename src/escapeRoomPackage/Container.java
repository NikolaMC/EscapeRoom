package escapeRoomPackage;

public class Container extends GameObject {
	private Inventory inventory;
    private boolean locked;

    public Container(String name, boolean moveable, boolean locked) {
        super(name, moveable);
        this.inventory = new Inventory(1);
        this.locked = locked;
    }

    // Return inventory of this object
    public Inventory getInventory() {
        return this.inventory;
    }
    
    // Check if this container is locked
    public boolean isLocked() {
        return this.locked;
    }
    
    // Add an object to the container
    public void addObject(GameObject go) {
		this.inventory.addObject(go);
	}
    
    // Remove an object from the container
    public void removeObject(GameObject go) {
		this.inventory.removeObject(go);
	}
	
    // Move an object from this container to the entered inventory
	public void moveObject(Inventory i2, GameObject go) {
		this.inventory.moveObject(i2, go);
	}
}
