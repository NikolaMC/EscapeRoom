package escapeRoomPackage;

public class GameObject {
	private String name;
	private boolean moveable;
	
	public GameObject(String name, boolean moveable) {
		this.name = name;
		this.moveable = moveable;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getMoveable() {
		return moveable;
	}
}
