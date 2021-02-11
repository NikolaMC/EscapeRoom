package escapeRoomPackage;

public class LockedDoor extends GameObject {
	private boolean locked;

	public LockedDoor(String name, boolean moveable, boolean locked) {
		super(name, moveable);
		this.locked = locked;
	}
	
	public boolean isLocked() {
        return this.locked;
    }

}
