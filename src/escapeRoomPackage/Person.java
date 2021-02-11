package escapeRoomPackage;

public class Person extends Npc implements Runnable {
	private int position;
	private Room[] rooms;

    public Person(String name, int startRoom, Room[] rooms) {
        super(name);
        this.position = startRoom;
        this.rooms = rooms;
    }
    
    public int getPosition() {
		return position;
	}
    
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
    
    private Inventory roomInventory(int position) {
    	Inventory roomInv = new Inventory(1);
    	for (int i = 0; i < this.rooms.length; i++) {
			roomInv = rooms[position].getInventory();
		}
    	return roomInv;
    }
    
    private void pickUp(Inventory i2) {
    	if(this.inventory.firstObject() == null && i2.firstObject() != null && i2.firstObject().getMoveable()) {
    		GameObject roomItem = i2.firstObject();
    		
			i2.removeObject(roomItem);
			this.inventory.addObject(roomItem);
		}
    }
    
    private void drop(Inventory i2) {
    	if(this.inventory.firstObject() != null && i2.isNotFull()) {
    		GameObject npcItem = this.inventory.firstObject();
    		
			this.inventory.removeObject(npcItem);
			i2.addObject(npcItem);
		}
    }

	@Override
	public void run() {
		int rand = (int)(Math.random() * 6);
		
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
				
			case 4:
				pickUp(roomInventory(this.position));
				break;
				
			case 5:
				drop(roomInventory(this.position));
				break;
	
			default:
				break;
		}
		
	}
   
}