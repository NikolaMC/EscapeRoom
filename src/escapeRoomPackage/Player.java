package escapeRoomPackage;

public class Player {

	private int currentLocation;
	Inventory inventory;
	  
	public Player() {
		this.currentLocation = 0;
		this.inventory = new Inventory(2);
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
    
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
	
	public int getCurrentLocation() {
		return currentLocation;
	}
	
	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}
	
}
