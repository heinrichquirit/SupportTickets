package main.java.net.bigbadcraft.supporttickets.utils;

public enum Level {

	FINE("FINE"), INFO("INFO"), WARNING("WARNING"), SEVERE("SEVERE");
	
	private final String level;
	
	Level(final String level) {
		this.level = level;
	}
	
	public String toString() {
		return level;
	}
	
}
