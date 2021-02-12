package escapeRoomPackage;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	
	private Room[] rooms;
	private Player player;
	private Person npc1;
	private Key boxKey, doorKey;
	private Container blueBox;
	private LockedDoor door;
	private GameObject[] items;
	private Lists lists = new Lists();
	
	private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextField input;
    private JTextArea output;
    private JTextArea inventory;
    private String command;
    private JButton takeButton;
    private JButton dropButton;
    private JButton tradeButton;
    private JButton goForwardButton;
    private JButton goBackwardButton;
	private JButton unlockButton;
	private JButton saveButton;
	private JButton loadButton;
    
    public Gui(Room[] rooms, Player player, Person npc1, Key boxKey, Key doorKey, Container blueBox, LockedDoor door, GameObject[] items) {
    	
    	this.rooms = rooms;
    	this.player = player;
    	this.npc1 = npc1;
    	this.boxKey = boxKey;
    	this.doorKey = doorKey;
    	this.blueBox = blueBox;
    	this.door = door;
    	this.items = items;
    	
    	// Set up the GUI
        this.command = "";
        this.setTitle("Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanels();
        this.add(mainPanel);
        this.setVisible(true);
        this.setResizable(false);
        
    }
    
    // Methods to update text fields in the GUI
    public void setShowRoom(String roomDescription) {
        this.showRoom.setText(roomDescription);
    }
    
    public void setShowPersons(Person person, String npcPosition) {
        this.showPersons.setText(person.toString() + "\nLocation - " + npcPosition);
    }
    
    public void removeShowPersons() {
    	this.showPersons.setText("NPCs");
    }
    
    public void setShowInventory(Inventory i) {
        this.inventory.setText(i.getNames());
    }
    
    public void setOutput(String output) {
    	this.output.setText(output);
    }

    // Add person to room
    public void setPerson(Person p) {
        this.showPersons.setText(p.toString());
    }

// Nedantåenda spaghetti är inte vacker...

    // Set up all the JPanels
    private void setUpPanels() {
        this.textPanel.add(showPersons);
        this.textPanel.add(showRoom);
        this.textPanel.add(inventory);
        this.textPanel.add(output);
        this.textPanel.add(input);
        this.buttonPanel.add(takeButton);
        this.buttonPanel.add(dropButton);
        this.buttonPanel.add(tradeButton);
        this.buttonPanel.add(unlockButton);
        this.buttonPanel.add(goForwardButton);
        this.buttonPanel.add(goBackwardButton);
        this.buttonPanel.add(saveButton);
        this.buttonPanel.add(loadButton);
        this.mainPanel.add(textPanel);
        this.mainPanel.add(buttonPanel);
    }
    
    // Set up all the elements, buttons and action listeners for the buttons
    private void setUpElements() {
    	this.mainPanel = new JPanel();
    	this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.textPanel = new JPanel();
        this.textPanel.setLayout(new BoxLayout(this.textPanel, BoxLayout.Y_AXIS));
        this.showRoom = new JTextArea("Room: ");
        this.showPersons = new JTextArea("NPCs");
        this.inventory = new JTextArea("Inventory");
        this.output = new JTextArea("Output");
        this.input = new JTextField("Give command");
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);
        this.output.setEditable(false);
        
        this.buttonPanel = new JPanel();
        
        setGui();
        Inventory playerInventory = player.getInventory();
        
        // If the player clicks "Take" and has entered a valid item name, move that item from the room to the player's inventory
        ActionListener takeListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");
        	
        	if (playerInventory.isNotFull() 
        			&& rooms[player.getCurrentLocation()].getInventory().getItem(this.command).getMoveable() 
        			&& rooms[player.getCurrentLocation()].getInventory().isObjectHere(rooms[player.getCurrentLocation()].getInventory().getItem(this.command))) {
        		
        		rooms[player.getCurrentLocation()].moveObject(playerInventory, rooms[player.getCurrentLocation()].getInventory().getItem(this.command));
        		setOutput("You picked up " + this.command + "!");
			}
        	
        	setGui();
        };
        
        // If the players clicks "Drop" and has entered a valid item name, move that item from the player's inventory to the room
        ActionListener dropListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");

        	if (rooms[player.getCurrentLocation()].getInventory().isNotFull() && playerInventory.isObjectHere(playerInventory.getItem(this.command))) {
        		playerInventory.moveObject(rooms[player.getCurrentLocation()].getInventory(), playerInventory.getItem(this.command));
        		setOutput("You dropped " + this.command + "!");
			}
        	
        	setGui();
        };
        
        // If the player clicks "Trade" and is in the same room as the NPC, trade the entered item from the player's inventory and the item the NPC is holding
        ActionListener tradeListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");
        	
        	if (player.getCurrentLocation() == npc1.getPosition() && playerInventory.isObjectHere(playerInventory.getItem(this.command)) && npc1.getFirstItem() != null) {
				npc1.getInventory().tradeObject(playerInventory, npc1.getFirstItem(), playerInventory.getItem(this.command));
				setOutput("Successfully traded!");
			}
        	
        	setGui();
        };
        
        // If the user clicks "Unlock" and is holding a key and a container for the key, or is in the same room as a locked door, unlock the container/door
        ActionListener unlockListener = e -> {        	
        	if (playerInventory.isObjectHere(boxKey) && playerInventory.isObjectHere(blueBox) && boxKey.fit(blueBox)) {
        		playerInventory.removeObject(boxKey);
        		blueBox.moveObject(playerInventory, doorKey);
        		playerInventory.removeObject(blueBox);
        		setOutput("You unlocked the blue box. Inside of it, you find another key!");
        	} else if (player.getCurrentLocation() == 3 && playerInventory.isObjectHere(doorKey) && doorKey.fit(door)) {
				playerInventory.removeObject(doorKey);
				rooms[3].removeObject(door);
				setOutput("You successfully escaped!");
			}
        	
        	setGui();
        };
        
        // If the user clicks "Go forward" and is not located in the final room, moves the user 1 room forward
        ActionListener forwardListener = e -> {
        	if (player.getCurrentLocation() < 3) {
				player.setCurrentLocation(player.getCurrentLocation() + 1);
				setOutput("You moved to the next room!");
			}
        	
        	setGui();
        };
        
        // If the user clicks "Go backward" and is not located in the first room, moves the user 1 room backwards
        ActionListener backwardListener = e -> {        	
        	if (player.getCurrentLocation() > 0) {
				player.setCurrentLocation(player.getCurrentLocation() - 1);
				setOutput("You moved to the previous room!");
			}
        	
        	setGui();
        };
        
        // If the user clicks "Save", saves the current progress of the game to a file on the user's computer (WIP)
        ActionListener saveListener = e -> {
        	lists.add(this.rooms[0]);
        	lists.add(this.rooms[1]);
        	lists.add(this.rooms[2]);
        	lists.add(this.rooms[3]);
        	
        	lists.add(this.npc1);
        	
        	lists.add(this.player);
        	
        	lists.add(items[0]);
        	lists.add(items[1]);
        	lists.add(items[2]);
        	lists.add(items[3]);
        	lists.add(items[4]);
        	lists.add(items[5]);
        	lists.add(items[6]);
        	lists.add(items[7]);
        	
        	lists.save();
        	
        	setOutput("Successfully saved!");
        };
        
        // If the user clicks "Load", loads a saved file from the user's computer (WIP)
        ActionListener loadListener = e -> {
        	System.out.println("Hi");
        };
        
        // Create all of the buttons and add the appropriate action listeners to them
        this.takeButton = new JButton("Take item");
        this.takeButton.addActionListener(takeListener);
        
        this.dropButton = new JButton("Drop item");
        this.dropButton.addActionListener(dropListener);
        
        this.tradeButton = new JButton("Trade");
        this.tradeButton.addActionListener(tradeListener);
        
        this.unlockButton = new JButton("Unlock");
        this.unlockButton.addActionListener(unlockListener);
        
        this.goForwardButton = new JButton("Go forward");
        this.goForwardButton.addActionListener(forwardListener);
        
        this.goBackwardButton = new JButton("Go backward");
        this.goBackwardButton.addActionListener(backwardListener);
        
        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(saveListener);
        
        this.loadButton = new JButton("Load");
        this.loadButton.addActionListener(loadListener);
    }
    
    // Update the GUI
    public void setGui() {
    	boxChecker();
    	
    	setShowRoom(this.rooms[this.player.getCurrentLocation()].getRoom());
    	
		setShowInventory(this.player.getInventory());
		
		if (this.player.getCurrentLocation() == this.npc1.getPosition()) {

			setShowPersons(this.npc1, this.rooms[this.npc1.getPosition()].getName());
		} else {
			removeShowPersons();
		}
    }
    
    // Checks if the blue box and the player are located in the 3rd room. If they are, and the room isn't full, spawns a key to the box
    private void boxChecker() {
    	if (player.getCurrentLocation() == 2 
    			&& rooms[2].getInventory().isObjectHere(blueBox) 
    			&& rooms[2].getInventory().isNotFull() 
    			&& !rooms[2].getInventory().isObjectHere(boxKey) 
    			&& !player.getInventory().isObjectHere(boxKey)) {
			rooms[2].addObject(boxKey);
		}
    }
}