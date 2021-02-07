package escapeRoomPackage;

import java.util.Arrays;

public class Inventory {
	private GameObject[] list;
    private int size;

    public Inventory(int size){
        this.size = size;
        list = new GameObject[size];
    }
    
    public void addObject(GameObject go){
        int index = getFirstEmptyIndex();

        if (index ==-1){
            System.out.println("Inventory är fullt");
            return;
        }
        
        this.list[index] = go;
    }
    
    public void moveObject(Inventory i2, GameObject go) {
        //if (!isObjectHere(go)){ //Error message }
        i2.addObject(go);
        //this.removeObject(go);
    }
    
    public String toString(){
        return Arrays.toString(this.list);
    }

    private int getFirstEmptyIndex(){

        for (int i = 0; i<this.list.length; i++){
            if (this.list[i] == null){
                return i;
            }
        }
        
        return -1;
        
    }
}
