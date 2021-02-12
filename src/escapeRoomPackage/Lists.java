package escapeRoomPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;

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
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void addNpc(Person npc) {
		this.npc.add(npc);
	}
	
	public void addPlayer(Player player) {
		this.player.add(player);
	}
	
	public void addItem(GameObject item) {
		this.items.add(item);
	}
	
	public ArrayList<GameObject> getItems() {
		return items;
	}
	
	public ArrayList<Person> getNpc() {
		return npc;
	}
	
	public ArrayList<Player> getPlayer() {
		return player;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void show() {
		System.out.println(rooms);
		System.out.println(npc);
		System.out.println(player);
		System.out.println(items);
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
	
	public Lists load() {
		JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
	    fc.showOpenDialog(null);
	    File file = fc.getSelectedFile();
	    this.fileName = file.getName();

	    Lists list = this;
	    FileInputStream fis = null;
	    
	    try {
	        fis = new FileInputStream(this.fileName);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        list = (Lists) ois.readObject();
	        ois.close();
	    } catch (IOException e) {

	    } catch (ClassNotFoundException e) {

	    }
	    
	    return list;
	}
}
