package main.java.net.bigbadcraft.supporttickets;

import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;

public class Ticket {

	private int id;
	private String requester;
	private String message;
	private String handler;
	private TicketStatus status;
	
	public Ticket() {
		id = 0;
		requester = null;
		message = null;
		handler = null;
		status = null;
	}
	
	public Ticket(int ticketId, String r, String m, String h, TicketStatus s) {
		id = ticketId;
		requester = r;
		message = m;
		setHandler(h);
		setStatus(s);
	}
	
	public int getId() {
		return id;
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
