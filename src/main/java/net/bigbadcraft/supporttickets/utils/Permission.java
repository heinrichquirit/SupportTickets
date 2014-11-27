package main.java.net.bigbadcraft.supporttickets.utils;

public enum Permission {
	
	PLAYER_USE("supporttickets.use", "", ""), 
	PLAYER_REQUEST("supporttickets.request", "/ticket request <message>", "Request for staff assistance."), 
	PLAYER_CHECK("supporttickets.check", "/ticket check", "Check the current status of your ticket."),
	MODERATOR_LIST("supporttickets.list", "/ticket list [status]", "List all/specified tickets."), 
	MODERATOR_SELECT("supporttickets.select", "/ticket select <id>", "Handle a specified ticket."), 
	MODERATOR_CHANGE("supporttickets.change", "/ticket change <id> <status>", "Change specified ticket's status."),
	ADMIN_PROGRESS_CHECK("supporttickets.staff", "/ticket staff", "List current ticket assignees."), 
	ADMIN_OVERRIDE("supporttickets.override", "/ticket override <id> <status>", "Override and change specified ticket's status.");
	
	private final String s;
	private final String d;
	private final String p;
	
	Permission(final String syntax, final String desc, final String perm) {
		s = syntax;
		d = desc;
		p = perm;
	}
	
	public String getSyntax() {
		return s;
	}
	
	public String getDescription() {
		return d;
	}
	
	public String toString() {
		return p;
	}
	
}
