package escapeRoomPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	
	private Room[] rooms;
	private Player player;
	private Person npc1;
	private Key boxKey, doorKey;
	private Container blueBox;
	private LockedDoor door;
	
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
    
    public Gui(Room[] rooms, Player player, Person npc1, Key boxKey, Key doorKey, Container blueBox, LockedDoor door) {
    	
    	this.rooms = rooms;
    	this.player = player;
    	this.npc1 = npc1;
    	this.boxKey = boxKey;
    	this.doorKey = doorKey;
    	this.blueBox = blueBox;
    	this.door = door;
    	
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
    
    // Här kan man updatera respektive fält:
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

    //Add person to room
    public void setPerson(Person p) {
        this.showPersons.setText(p.toString());
    }

// Nedantåenda spaghetti är inte vacker...

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
        this.mainPanel.add(textPanel);
        this.mainPanel.add(buttonPanel);
    }
    
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
        
        ActionListener takeListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");
        	
        	if (playerInventory.isNotFull() 
        			&& rooms[player.getCurrentLocation()].getInventory().getItem(this.command).getMoveable() 
        			&& rooms[player.getCurrentLocation()].getInventory().isObjectHere(rooms[player.getCurrentLocation()].getInventory().getItem(this.command))) {
        		
        		rooms[player.getCurrentLocation()].moveObject(playerInventory, rooms[player.getCurrentLocation()].getInventory().getItem(this.command));
        		setOutput("You picked up " + this.command);
			}
        	
        	setGui();
        };
        
        ActionListener dropListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");

        	if (rooms[player.getCurrentLocation()].getInventory().isNotFull() && playerInventory.isObjectHere(playerInventory.getItem(this.command))) {
        		playerInventory.moveObject(rooms[player.getCurrentLocation()].getInventory(), playerInventory.getItem(this.command));
        		setOutput("You dropped " + this.command);
			}
        	
        	setGui();
        };
        
        ActionListener tradeListener = e -> {
        	this.command = input.getText();
        	
        	if (player.getCurrentLocation() == npc1.getPosition()) {
				npc1.getInventory().tradeObject(playerInventory, npc1.getFirstItem(), playerInventory.getItem(this.command));
				setOutput("Successfully traded" + this.command + " and " + npc1.getFirstItem().getName());
			}
        	
        	setGui();
        };
        
        ActionListener unlockListener = e -> {        	
        	if (playerInventory.isObjectHere(boxKey) && playerInventory.isObjectHere(blueBox) && boxKey.fit(blueBox)) {
        		playerInventory.removeObject(boxKey);
        		blueBox.moveObject(playerInventory, doorKey);
        		playerInventory.removeObject(blueBox);
        		setOutput("You unlocked the blue box. Inside of it, you find another key");
        	}
        	
        	if (player.getCurrentLocation() == 3 && playerInventory.isObjectHere(doorKey) && doorKey.fit(door)) {
				playerInventory.removeObject(doorKey);
				rooms[3].removeObject(door);
				setOutput("You successfully escaped!");
			}
        	
        	setGui();
        };
        
        ActionListener forwardListener = e -> {
        	if (player.getCurrentLocation() < 3) {
				player.setCurrentLocation(player.getCurrentLocation() + 1);
				setOutput("You moved to the next room");
			}
        	
        	setGui();
        };
        
        ActionListener backwardListener = e -> {        	
        	if (player.getCurrentLocation() > 0) {
				player.setCurrentLocation(player.getCurrentLocation() - 1);
				setOutput("You moved to the previous room");
			}
        	
        	setGui();
        };
        
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
        
    }
    
    private void setGui() {
    	boxChecker();
    	
    	setShowRoom(rooms[player.getCurrentLocation()].getRoom());
    	
		setShowInventory(player.getInventory());
		
		if (player.getCurrentLocation() == npc1.getPosition()) {
			setShowPersons(npc1, rooms[npc1.getPosition()].getName());
		} else {
			removeShowPersons();
		}
    }
    
    private void boxChecker() {
    	if (player.getCurrentLocation() == 2 && rooms[2].getInventory().isObjectHere(blueBox) && rooms[2].getInventory().isNotFull()) {
			rooms[2].addObject(boxKey);
		}
    }

}