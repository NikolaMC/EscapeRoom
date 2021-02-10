package escapeRoomPackage;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

	Gui gui;
	Room room1, room2, room3, room4;
	Room[] rooms = new Room[4];
	Player player;
	Container cabinet;
	GameObject book, rock;
	Person npc1;
	
	public Game() {
		
		this.gui = new Gui();
		this.player = new Player();
		
		this.room1 = new Room("Starting room", "There's a small cabinet in one of the corners and a door next to it.\n");
		this.room2 = new Room("Hall", "This hall seems empty. There are 2 doors leading out of it.\n");
		this.room3 = new Room("Room with blue square", "There are 2 doors leading out of this room. You see a red square on the ground in a corner.\n");
		this.room4 = new Room("Exit room", "There are 2 doors leading out of this room. One of which is the exit, but it appears to be locked. \nYou see another blue square on the ground next to a wall.\n");
		
		rooms[0] = room1;
		rooms[1] = room2;
		rooms[2] = room3;
		rooms[3] = room4;
		
		this.cabinet = new Container("Cabinet", false, false);
		this.book = new GameObject("Book", true);
		this.rock = new GameObject("Rock", true);
		this.npc1 = new Person("Jack", 1);
		
		player.addObject(book);
		npc1.addObject(rock);
		
		room1.addObject(cabinet);
		room2.addNpc(npc1);
		
		ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(npc1, 2, 10, TimeUnit.SECONDS);
		
		while (true) {
			
			String command = gui.getCommand();
			
			if (!command.equals("-1")) {
				
				switch (command) {
					case "1":
						if (player.getCurrentLocation() == 1) {
							player.setCurrentLocation(0);
						}
						break;
						
					case "2":
						if (player.getCurrentLocation() == 0 || player.getCurrentLocation() == 2) {
							player.setCurrentLocation(1);
						}
						break;
						
					case "3":
						if (player.getCurrentLocation() == 1 || player.getCurrentLocation() == 3) {
							player.setCurrentLocation(2);
						}
						break;
						
					case "4":
						if (player.getCurrentLocation() == 2) {
							player.setCurrentLocation(3);
						}
						break;
						
					case "trade":
						if (player.getCurrentLocation() == npc1.getPosition()) {
							npc1.getInventory().tradeObject(player.getInventory(), npc1.getFirstItem());
						}
						break;
	
					default:
						break;
				}
				
			}
			
			gui.setShowRoom(rooms[player.getCurrentLocation()].getRoom());
			gui.setShowInventory(player.getInventory());
			if (player.getCurrentLocation() == npc1.getPosition()) {
				gui.setShowPersons(npc1, rooms[npc1.getPosition()].getName());
			} else {
				gui.removeShowPersons();
			}
			
		}
		
	}
	
}
