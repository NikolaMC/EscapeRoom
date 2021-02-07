package escapeRoomPackage;

public class Key extends GameObject {
	Container container;
	
    public Key(String name, boolean moveable, Container c) {
        super(name, moveable);
        this.container = c;
    }
    
    public boolean fit (Container c){
    	
        if (this.container.getName().equals(c.getName())){
            return true;
        } else{
            return false;
        }

    }
}
