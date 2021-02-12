package escapeRoomPackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Lists implements Serializable {

	private ArrayList<Room> rooms;
	private ArrayList<Person> npc;
	private ArrayList<Player> player;
	private ArrayList<GameObject> items;
	private String fileName = "myFile";
	
	public Lists() {
		this.rooms = new ArrayList<>();
		this.npc = new ArrayList<>();
		this.player = new ArrayList<>();
		this.items = new ArrayList<>();
	}
	
	public void add(Room room) {
		this.rooms.add(room);
	}
	
	public void add(Person npc) {
		this.npc.add(npc);
	}
	
	public void add(Player player) {
		this.player.add(player);
	}
	
	public void add(GameObject item) {
		this.items.add(item);
	}

	public void save() {
		try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
