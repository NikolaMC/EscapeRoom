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
    
    public void tradeObject(Inventory i2, GameObject go) {
    	if (isObjectHere(go) && i2.getFirstEmptyIndex() > 0) {
    		GameObject firstItem = i2.list[0];
    		
			this.removeObject(go);
			i2.removeObject(firstItem);
			i2.addObject(go);
			this.addObject(firstItem);
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
    
    public boolean isObjectHere(GameObject go) {
    	
    	boolean objectHere = false;
    	
    	for (int i = 0; i < this.list.length; i++) {
    		if (this.list[i] != null && this.list[i].equals(go)) {
				objectHere = true;
			}
		}
    	
    	return objectHere;
    	
    }
}
