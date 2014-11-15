package main.java.net.bigbadcraft.supporttickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class QueueManager {

	private final String B = ChatColor.DARK_AQUA;
	private final String W = ChatColor.WHITE;
	
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	public void addTicket(Ticket t) {
		if (!tickets.contains(t)) {
			tickets.add(t);
		}
	}
	
	public void removeTicket(int id) {
		if (contains(id)) { 
			tickets.remove(id);
		}
	}
	
	public boolean contains(int ticketId) {
		return tickets.contains(tickets.get(ticketId));
	}
	
	public void handleTicket(int ticketId, MC_Player staff) {
		if (contains(ticketId)) {
			Ticket t = getTicket(ticketId);
			t.setHandler(staff.getName());
			t.setStatus(TicketStatus.PENDING);
			Util.msg(staff, W + "You have selected " + B + t.getRequester() + W + " ticket.");
		}
	}
	
	public void displayTickets(MC_Player player) {
		Util.msg(player, W + "---------- " + B + "SupportTickets " + W + " (" + B + tickets.size() + W + ") ----------");
		for (Ticket t : tickets) {
			player.sendMessage(W + "(" + B + t.getId() + W +") " + t.getRequester() + ": " + t.getMessage());
		}
	}
	
	public Ticket getTicket(int ticketId) {
		Ticket ticket = null;
		if (!contains(ticketId)) return ticket; // Return null;
		for (Ticket t : tickets) {
			if (t.getId() == ticketId) {
				ticket = new Ticket(t.getId(), t.getRequester(), t.getMessage(), t.getHandler(), t.getStatus());
			}
		}
		return ticket;
	}
	
	public List<Ticket> getPlayerTickets(MC_Player player) {
		List<Ticket> temp = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getRequester().equals(player.getName())) {
				temp.add(t);
			}
		}
		if (temp.size() == 0) {
			return null;
		}
		return temp;
	}
	
	public boolean hasTicket(MC_Player player) {
		return getPlayerTickets(player).size() > 0;
	}
	
	public int getTotalTickets() {
		return tickets.size();
	}
	
	public int getTotalTickets(MC_Player player) {
		return getPlayerTickets(player).size();
	}
	
	/*
	 * Gets the player's earliest ticket id
	 */
	public int getEarly(MC_Player player) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : getPlayerTickets(player)) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.min(ids)));
	}
	
	/*
	 * Gets the player's latest ticket id
	 */
	public int getLate(MC_Player player) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : getPlayerTickets(player)) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.max(ids)));
	}
	
	public int getTicketCount() {
		return tickets.size();
	}
	
	public void showDetails(MC_Player player, int ticketId) {
		player.sendMessage(W + "---------- " + B + "SupportTickets" + W + "[" + B + "ID: " + ticketId + W + "] ----------");
		Ticket t = getTicket(ticketId);
		player.sendMessage(B + "Requester" + W + ": " + t.getRequester());
		player.sendMessage(B + "Message" + W + ": " + t.getMessage());
		player.sendMessage(B + "Handler" + W + ": " + t.getHandler());
		player.sendMessage(B + "Status: " + W + ": " + t.getStatus());
	}
	
	public void showTickets(MC_Player player, TicketStatus status) {
		List<Ticket> filter = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getStatus() == status) {
				filter.add(t);
			}
		}
		player.sendMessage(W + "---------- " + B + "SupportTickets " + W + " [" + B + "Status: Claimed" + W + "] ----------");
		for (Ticket t : filter) {
			player.sendMessage(W + "(" + B + t.getId() + W + ") " + t.getRequester() + ": " + t.getMessage());
		}
	}
	
}
