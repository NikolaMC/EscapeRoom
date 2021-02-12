package escapeRoomPackage;

import com.sun.source.tree.SynchronizedTree;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person extends Npc implements Runnable, Serializable {
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
    
    // Gets the first (and only) item the NPC is holding, if any
    public GameObject getFirstItem() {
    	return this.inventory.firstObject();
    }
    
    // Returns the inventory of the room the NPC is currently located in
    private Inventory roomInventory(int position) {
    	List<Inventory> roomInv = IntStream.range(0, rooms.length).mapToObj(x -> rooms[position].getInventory()).collect(Collectors.toList());
    	return roomInv.get(0);
    }
    
    // Makes the NPC pick up the 1st object from the room they're currently in, if the object is movable
    private void pickUp(Inventory i2) {
    	if(this.inventory.firstObject() == null && i2.firstObject() != null && i2.firstObject().getMoveable()) {
    		GameObject roomItem = i2.firstObject();
    		
			i2.removeObject(roomItem);
			this.inventory.addObject(roomItem);
		}
    }
    
    // Makes the NPC drop the item they are holding in the room they're currently in
    private void drop(Inventory i2) {
    	if(this.inventory.firstObject() != null && i2.isNotFull()) {
    		GameObject npcItem = this.inventory.firstObject();
    		
			this.inventory.removeObject(npcItem);
			i2.addObject(npcItem);
		}
    }

	@Override
	public  void run() {

		int rand = (int)(Math.random() * 6); // Generate a number between 0 and 5
		
		switch (rand) {
			case 0: // If the generated number is 0 and the NPC is currently in room 2, move the NPC to room 1
				if(this.position == 1) {
					this.position = 0;
				}
				break;
				
			case 1: // If the generated number is 1 and the NPC is currently in room 1 or room 3, move the NPC to room 2
				if(this.position == 2 || this.position == 0) {
					this.position = 1;
				}
				break;
				
			case 2: // If the generated number is 2 and the NPC is currently in room 2 or room 4, move the NPC to room 3
				if(this.position == 3 || this.position == 1) {
					this.position = 2;
				}
				break;
				
			case 3: // If the generated number is 3 and the NPC is currently in room 3, move the NPC to room 4
				if(this.position == 2) {
					this.position = 3;
				}
				break;
				
			case 4: // If the generated number is 4, make the NPC pick up the first item from the room they're in
				pickUp(roomInventory(this.position));
				break;
				
			case 5: // If the generated number is 5, make the NPC drop the item they're holding into the room they're in
				drop(roomInventory(this.position));
				break;
	
			default:
				break;
		}
	}
   
}