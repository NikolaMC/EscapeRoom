package escapeRoomPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	
	private Room[] rooms;
	private Player player;
	private Person npc1;
	
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
    
    public Gui(Room[] rooms, Player player, Person npc1) {
    	
    	this.rooms = rooms;
    	this.player = player;
    	this.npc1 = npc1;
    	
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
        //Room playerRoom = rooms[player.getCurrentLocation()];
        Inventory playerInventory = player.getInventory();
        
        ActionListener takeListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");
        	
        	if (playerInventory.isNotFull() 
        			&& rooms[player.getCurrentLocation()].getInventory().getItem(this.command).getMoveable() 
        			&& rooms[player.getCurrentLocation()].getInventory().isObjectHere(rooms[player.getCurrentLocation()].getInventory().getItem(this.command))) {
        		
        		rooms[player.getCurrentLocation()].moveObject(playerInventory, rooms[player.getCurrentLocation()].getInventory().getItem(this.command));
			}
        	
        	setGui();
        };
        
        ActionListener dropListener = e -> {
        	this.command = input.getText();
        	this.input.setText("");

        	if (rooms[player.getCurrentLocation()].getInventory().isNotFull() && playerInventory.isObjectHere(playerInventory.getItem(this.command))) {
        		playerInventory.moveObject(rooms[player.getCurrentLocation()].getInventory(), playerInventory.getItem(this.command));
			}
        	
        	setGui();
        };
        
        ActionListener tradeListener = e -> {
        	this.command = input.getText();
        	
        	if (player.getCurrentLocation() == npc1.getPosition()) {
				npc1.getInventory().tradeObject(playerInventory, npc1.getFirstItem(), playerInventory.getItem(this.command));
			}
        	
        	setGui();
        };
        
        ActionListener forwardListener = e -> {
        	if (player.getCurrentLocation() < 3) {
				player.setCurrentLocation(player.getCurrentLocation() + 1);
			}
        	
        	setGui();
        };
        
        ActionListener backwardListener = e -> {        	
        	if (player.getCurrentLocation() > 0) {
				player.setCurrentLocation(player.getCurrentLocation() - 1);
			}
        	
        	setGui();
        };
        
        this.takeButton = new JButton("Take item");
        this.takeButton.addActionListener(takeListener);
        
        this.dropButton = new JButton("Drop item");
        this.dropButton.addActionListener(dropListener);
        
        this.tradeButton = new JButton("Trade");
        this.tradeButton.addActionListener(tradeListener);
        
        this.goForwardButton = new JButton("Go forward");
        this.goForwardButton.addActionListener(forwardListener);
        
        this.goBackwardButton = new JButton("Go backward");
        this.goBackwardButton.addActionListener(backwardListener);
        
    }
    
    private void setGui() {
    	setShowRoom(rooms[player.getCurrentLocation()].getRoom());
    	
		setShowInventory(player.getInventory());
		
		if (player.getCurrentLocation() == npc1.getPosition()) {
			setShowPersons(npc1, rooms[npc1.getPosition()].getName());
		} else {
			removeShowPersons();
		}
    }

}