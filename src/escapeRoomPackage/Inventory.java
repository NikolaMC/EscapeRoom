package escapeRoomPackage;

import java.util.Arrays;

public class Inventory {
	private GameObject[] list;
    private int size;

    public Inventory(int size) {
        this.size = size;
        list = new GameObject[size];
    }
    
    public void addObject(GameObject go) {
        int index = getFirstEmptyIndex();

        if (index == -1){
            System.out.println("Inventory is full");
            return;
        }
        
        this.list[index] = go;
    }
    
    public void removeObject(GameObject go) {
    	
    	for (int i = 0; i < this.list.length; i++) {
    		if (this.list[i] == null || this.list[i].equals(go)) {
				this.list[i] = null;
			}
		}
    	
    }
    
    public void moveObject(Inventory i2, GameObject go) {
    	if (isObjectHere(go)) {
			this.removeObject(go);
			i2.addObject(go);
		}
    }
    
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
    
    public GameObject firstObject() {
    	return this.list[0];
    }
    
    public String getNames() {
    	String listNames = "";
    	
    	for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				listNames += " ";
			} else {
				listNames += list[i].getName() + ", ";
			}
		}
    	
    	return listNames;
    }

    private int getFirstEmptyIndex() {

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == null) {
                return i;
            }
        }
        
        return -1;
        
    }
    
    public GameObject getItem(String itemName) {
    	GameObject item = new GameObject("", false);
    	
    	for (int i = 0; i < this.list.length; i++) {
    		if (this.list[i] != null && this.list[i].getName().equals(itemName)) {
				item = this.list[i];
			}
		}
    	
    	return item;
    }
    
    public boolean isObjectHere(GameObject go) {
    	
    	boolean objectHere = false;
    	
    	for (int i = 0; i < this.list.length; i++) {
    		if (this.list[i] != null && this.list[i].equals(go)) {
				objectHere = true;
			}
		}
    	
    	return objectHere;
    	
    }
    
    public boolean isNotFull() {
    	
    	boolean notFull = true;
    	int index = getFirstEmptyIndex();
    	
    	if (index == -1) {
			notFull = false;
		}
    	
    	return notFull;
    	
    }
}
