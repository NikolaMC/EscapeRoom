package escapeRoomPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	
	private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextArea output;
    private JTextField input;
    private JTextArea inventory;
    private String command;
    private boolean gotCommand;
    private JButton submitButton;
    public Gui() {
    	
        this.gotCommand = false;
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

    // Returnera det senaste commitade kommandot
    public String getCommand() {
    	
        if (this.gotCommand){
            System.out.println(this.command);
            return this.command;
        }
        
        return "-1";

    }
    
    // Här kan man updatera respektive fält:
    public void setShowRoom(String roomDescription) {
        this.showRoom.setText(roomDescription);
    }
    
    public void setShowPersons(Person person) {
        this.showPersons.setText(person.toString());
    }
    
    public void setShowInventory(Inventory i) {
        this.input.setText(i.toString());
    }
    
    public void setStatus(String output) {
    	this.output.setText(output);
    }

    //Add person to room
    public void setPerson(Person p) {
        this.showPersons.setText(p.toString());
    }

// Nedantåenda spaghetti är inte vacker...


    public void gotCommand() {
        this.gotCommand = false;
    }

    private void setUpPanels() {
        this.textPanel.add(showPersons);
        this.textPanel.add(showRoom);
        this.textPanel.add(inventory);
        this.textPanel.add(output);
        this.textPanel.add(input);
        this.buttonPanel.add(submitButton);
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

        ActionListener inputListener = e -> {
            this.command = input.getText();
            this.gotCommand = true;
            System.out.println(this.command);
        };

        input.addActionListener(inputListener);

        this.submitButton = new JButton("Submit");
        this.submitButton.addActionListener(inputListener);

    }

}