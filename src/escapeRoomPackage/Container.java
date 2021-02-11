package escapeRoomPackage;

public class Container extends GameObject {
	private Inventory inventory;
    private boolean locked;

    public Container(String name, boolean moveable, boolean locked) {
        super(name, moveable);
        this.inventory = new Inventory(1);
        this.locked = locked;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    
    public boolean isLocked() {
        return this.locked;
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
