package escapeRoomPackage;

public class Game {

	Gui gui;
	Room[] rooms = new Room[4];
	Player player;
	
	public Game() {
		
		this.gui = new Gui();
		player = new Player();
		
		Room room1 = new Room(0, "Room 1", "Very cramped and dusty. You see a small cabinet in one of the corners and a door next to it.");
		Room room2 = new Room(1, "Room 2", "A small hall, at the end of which you see another door. You also see what looks to be a human walking around.");
		Room room3 = new Room(2, "Room 3", "b");
		Room room4 = new Room(3, "Room 4", "c");
		
		rooms[0] = room1;
		rooms[1] = room2;
		rooms[2] = room3;
		rooms[3] = room4;
		
		while (true) {
			
			String command = gui.getCommand();
			
			if (!command.equals("-1")) {
				
				switch (command) {
					case "1":
						player.setCurrentLocation(0);
						break;
						
					case "2":
						player.setCurrentLocation(1);
						break;
						
					case "3":
						player.setCurrentLocation(2);
						break;
						
					case "4":
						player.setCurrentLocation(3);
						break;
	
					default:
						break;
				}
				
			}
			
			gui.setShowRoom(rooms[player.getCurrentLocation()].getRoom());
			
		}
		
	}
	
}
