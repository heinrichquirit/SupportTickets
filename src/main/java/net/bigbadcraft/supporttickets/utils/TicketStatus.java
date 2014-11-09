package main.java.net.bigbadcraft.supporttickets.utils;

public enum TicketStatus {
	
	OPEN("Open"), PENDING("Pending"), REOPENED("Re-Opened"), CLOSED("Closed");
	
	private final String status;
	
	TicketStatus(final String status) {
		this.status = status;		
	}
	
	public String toString() {
		return status;
	}
	
}
