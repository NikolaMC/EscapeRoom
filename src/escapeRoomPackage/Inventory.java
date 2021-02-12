package escapeRoomPackage;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Inventory {
	private GameObject[] list;
	
    public Inventory(int size) {
        list = new GameObject[size];
    }
    
    // Add the entered object to this inventory if there is an empty space
    public void addObject(GameObject go) {
        int index = getFirstEmptyIndex();

        if (index == -1){
            System.out.println("Inventory is full");
            return;
        }
        
        this.list[index] = go;
    }
    
    // Remove the entered object from this inventory
    public void removeObject(GameObject go) {
    	this.list = IntStream.range(0, this.list.length)
    			.peek(x -> {
    				if (x == indexOfItem(go)) {
    					this.list[x] = null;
    				}
    			})
    			.mapToObj(x -> this.list[x]).toArray(GameObject[]::new);
    	
    }
    
    // Move an object from this inventory to the i2 inventory
    public void moveObject(Inventory i2, GameObject go) {
    	if (isObjectHere(go)) {
			this.removeObject(go);
			i2.addObject(go);
		}
    }
    
    // Swap the entered object from this inventory and the entered object from the i2 inventory
    public void tradeObject(Inventory i2, GameObject go, GameObject go1) {
    	if (isObjectHere(go) && i2.isObjectHere(go1)) {
    		GameObject item = go1;
			this.removeObject(go);
			i2.removeObject(item);
			i2.addObject(go);
			this.addObject(item);
		}
    }
    
    public String toString() {
        return Arrays.toString(this.list);
    }
    
    // Get the first object in an inventory
    public GameObject firstObject() {
    	return this.list[0];
    }
    
    // Get the names of all items in an inventory as a comma separated string
    public String getNames() {
    	return Arrays.stream(this.list)
    			.filter(Objects::nonNull)
    			.map(x -> x.getName())
    			.collect(Collectors.joining(", "));
    }

    // Get the first empty spot in an inventory
    private int getFirstEmptyIndex() {

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == null) {
                return i;
            }
        }
        
        return -1;
        
    }
    
    // Return an item with a specific name
    public GameObject getItem(String itemName) {    	
    	Optional<GameObject> item = Arrays.stream(this.list)
    			.filter(Objects::nonNull)
    			.filter(x -> x.getName().equals(itemName))
    			.findFirst();
    	
    	if (!item.isEmpty()) {
			return item.get();
		}
    	
    	return new GameObject("", false);
    }
    
    // Check if the entered item is present in the inventory
    public boolean isObjectHere(GameObject go) {
    	return Arrays.stream(this.list)
    			.filter(Objects::nonNull)
    			.anyMatch(x -> x.equals(go));
    }
    
    // Check if the inventory has empty space left. If yes, return true. If it's full, return false
    public boolean isNotFull() {
    	
    	boolean notFull = true;
    	int index = getFirstEmptyIndex();
    	
    	if (index == -1) {
			notFull = false;
		}
    	
    	return notFull;
    	
    }
    
    // Get the index of the entered object
    public int indexOfItem(GameObject go) {
    	
    	int index = Arrays.stream(this.list).collect(Collectors.toList()).indexOf(go);
    	
    	return index;
    	
    }
}
