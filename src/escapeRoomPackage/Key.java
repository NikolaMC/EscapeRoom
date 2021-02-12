package escapeRoomPackage;

import java.io.Serializable;

public class Key extends GameObject implements Serializable {
	Container container;
	LockedDoor door;
	
    public Key(String name, boolean moveable, Container c) {
        super(name, moveable);
        this.container = c;
    }
    
    public Key(String name, boolean moveable, LockedDoor door) {
        super(name, moveable);
        this.door = door;
    }
    
    // Check if the key fits the container or door
    public boolean fit(Container c) {
    	
        if (this.container.getName().equals(c.getName())){
            return true;
        } else{
            return false;
        }

    }
    
    public boolean fit(LockedDoor door) {
    	
        if (this.door.getName().equals(door.getName())){
            return true;
        } else{
            return false;
        }

    }
}
