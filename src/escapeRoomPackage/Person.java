package escapeRoomPackage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person extends Npc implements Runnable {
	private int position;
	private Room[] rooms;
	private Gui gui;

    public Person(String name, int startRoom, Room[] rooms, Gui gui) {
        super(name);
        this.position = startRoom;
        this.rooms = rooms;
        this.gui = gui;
    }
    
    public int getPosition() {
		return position;
	}
    
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
    
    private Inventory roomInventory(int position) {
    	List<Inventory> roomInv = IntStream.range(0, rooms.length).mapToObj(x -> rooms[position].getInventory()).collect(Collectors.toList());
    	return roomInv.get(0);
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
		synchronize { gui.setGui(); }
		
//		int rand = (int)(Math.random() * 6);
		int rand = 5;
		switch (rand) {
			case 0:
				if(this.position == 1) {
					System.out.println("0");
					this.position = 0;
				}
				break;
				
			case 1:
				if(this.position == 2 || this.position == 0) {
					System.out.println("1");
					this.position = 1;
				}
				break;
				
			case 2:
				if(this.position == 3 || this.position == 1) {
					System.out.println("2");
					this.position = 2;
				}
				break;
				
			case 3:
				if(this.position == 2) {
					System.out.println("3");
					this.position = 3;
				}
				break;
				
			case 4:
				pickUp(roomInventory(this.position));
				System.out.println("4");
				break;
				
			case 5:
				drop(roomInventory(this.position));
				System.out.println("5");
				break;
	
			default:
				break;
		}
	}
   
}