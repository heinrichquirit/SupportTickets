package main.java.net.bigbadcraft.supporttickets.utils;

public enum DebugLevel {

	LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH");
	
	private final String level;
	
	DebugLevel(final String level) {
		this.level = level;
	}
	
	public String toString() {
		return level;
	}
	
}
