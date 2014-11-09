package main.java.net.bigbadcraft.supporttickets;

import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;

public class Ticket {

	private int ticketId;
	private String requester;
	private String message;
	private String handler;
	private TicketStatus status;
	
	public Ticket(int id, String r, String m, String h, TicketStatus s) {
		ticketId = id;
		requester = r;
		message = m;
		setHandler(h);
		setStatus(s);
	}
	
	public int getId() {
		return ticketId;
	}
	
	public String getRequester() {
		return requester;
	}
	
	public String getMessage() { 
		return message;
	}
	
	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getHandler() {
		return handler;
	}
	
	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	
	public TicketStatus getStatus() {
		return status;
	}
	
}
