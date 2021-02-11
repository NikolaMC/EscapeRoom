package escapeRoomPackage;

public class Person extends Npc implements Runnable {
	private int position;

    public Person(String name, int startRoom) {
        super(name);
        this.position = startRoom;
    }
    
    public int getPosition() {
		return position;
	}
    
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
    
    private void pickUp(Inventory i2) {
    	if(this.inventory.firstObject() == null && i2.firstObject() != null) {
			i2.removeObject(i2.firstObject());
			this.inventory.addObject(i2.firstObject());
		}
    }

	@Override
	public void run() {
		int rand = (int)(Math.random() * 4);
		
		switch (rand) {
			case 0:
				if(this.position == 1) {
					this.position = 0;
				}
				break;
				
			case 1:
				if(this.position == 2 || this.position == 0) {
					this.position = 1;
				}
				break;
				
			case 2:
				if(this.position == 3 || this.position == 1) {
					this.position = 2;
				}
				break;
				
			case 3:
				if(this.position == 2) {
					this.position = 3;
				}
				break;
	
			default:
				break;
		}
		
	}
   
}