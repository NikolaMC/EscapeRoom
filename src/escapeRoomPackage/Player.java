package escapeRoomPackage;

import java.io.Serializable;

public class Player implements Serializable {

	private int currentLocation;
	Inventory inventory;
	  
	public Player() {
		this.currentLocation = 0;
		this.inventory = new Inventory(2);
	}
	
	// Add an object to the player's inventory
	public void addObject(GameObject go) {
		this.inventory.addObject(go);
	}
	
	// Remove an object from the player's inventory
	public void removeObject(GameObject go) {
		this.inventory.removeObject(go);
	}
	
	// Move an object from the player's inventory to the entered inventory
	public void moveObject(Inventory i2, GameObject go) {
		this.inventory.moveObject(i2, go);
	}

	// Return the player's inventory
    public Inventory getInventory() {
        return this.inventory;
    }
    
    // Get the first item from the player's inventory
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
	
    // Get the player's current location
	public int getCurrentLocation() {
		return currentLocation;
	}
	
	// Set the player's current location
	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}
	
}
