package escapeRoomPackage;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

	Gui gui;
	Room room1, room2, room3, room4;
	Room[] rooms = new Room[4];
	Player player;
	Container cabinet;
	GameObject book, rock, blueBox, stick, statue;
	Person npc1;
	
	public Game() {
		
		this.player = new Player();
		
		this.room1 = new Room("Starting room", "There's a small statue in one of the corners and a door next to it.\n");
		this.room2 = new Room("Hall", "This hall seems empty. There are 2 doors leading out of it.\n");
		this.room3 = new Room("Room with blue square", "There are 2 doors leading out of this room. You see a red square on the ground in a corner.\n");
		this.room4 = new Room("Exit room", "There are 2 doors leading out of this room. One of which is the exit, but it appears to be locked. \nYou see another blue square on the ground next to a wall.\n");
		
		rooms[0] = room1;
		rooms[1] = room2;
		rooms[2] = room3;
		rooms[3] = room4;
		
		this.blueBox = new GameObject("blue box", true);
		this.book = new GameObject("book", true);
		this.rock = new GameObject("rock", true);
		this.stick = new GameObject("stick", true);
		this.statue = new GameObject("statue", false);
				
		room1.addObject(statue);
		room1.addObject(book);
		room2.addObject(stick);
		
		this.npc1 = new Person("Jack", 1, rooms);
		
		room2.addNpc(npc1);
		
		player.addObject(rock);
		npc1.addObject(blueBox);
		
		this.gui = new Gui(rooms, player, npc1);
		
		ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(npc1, 2, 10, TimeUnit.SECONDS);
		
	}
	
}
