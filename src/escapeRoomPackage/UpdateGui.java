package escapeRoomPackage;


public class UpdateGui implements Runnable {
    private Gui gui;

    public UpdateGui(Gui g){

        this.gui = g;
    }

    @Override
    public void run() {
        gui.setGui();
        System.out.println("Updated gui");
    }

}
