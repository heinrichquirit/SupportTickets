package main.java.net.bigbadcraft.supporttickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class QueueManager {

	private final ChatColor B = ChatColor.DARK_AQUA;
	private final ChatColor W = ChatColor.WHITE;
	
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private List<Ticket> pendingTickets = new ArrayList<Ticket>();
	
	public void queue(Ticket t) {
		if (!tickets.contains(t)) {
			tickets.add(t);
		}
	}
	
	public void dequeue(int id) {
		if (contains(id)) { 
			tickets.remove(id);
		}
	}
	
	public void pend(Ticket t) {
		dequeue(t.getId());
		pendingTickets.add(t);
	}
	
	public void unpend(int id) {
		if (pendingTickets.contains(pendingTickets.get(id))) {
			pendingTickets.remove(id);
		}
	}
	
	public boolean contains(int ticketId) {
		return tickets.contains(tickets.get(ticketId));
	}
	
	public void handleTicket(int ticketId, Player staff) {
		if (contains(ticketId)) {
			Ticket t = getQueuedTicket(ticketId);
			t.setHandler(staff.getName());
			t.setStatus(TicketStatus.PENDING);
			Util.msg(staff, W + "You have selected " + B + t.getRequester() + W + " ticket.");
		}
	}
	
	public void displayTickets(Player player) {
		Util.msg(player, W + "---------- " + B + "SupportTickets " + W + " (" + B + tickets.size() + W + ") ----------");
		for (Ticket t : tickets) {
			player.sendMessage(W + "(" + B + t.getId() + W +") " + t.getRequester() + ": " + t.getMessage());
		}
	}
	
	/*
	 * Gets the specified ticket from tickets list
	 */
	public Ticket getQueuedTicket(int ticketId) {
		Ticket ticket = null;
		if (!contains(ticketId)) return ticket; // Return null;
		for (Ticket t : tickets) {
			if (t.getId() == ticketId) {
				ticket = new Ticket(t.getId(), t.getRequester(), t.getMessage(), t.getHandler(), t.getStatus());
			}
		}
		return ticket;
	}
	
	public Ticket getPendingTicket(int ticketId) {
		Ticket ticket = null;
		if (!pendingTickets.contains(pendingTickets.get(ticketId))) return null;
		for (Ticket t : pendingTickets) {
			if (t.getId() == ticketId) {
				ticket = new Ticket(t.getId(), t.getRequester(), t.getMessage(), t.getHandler(), t.getStatus());
			}
		}
		return ticket;
	}
	
	public List<Ticket> getPlayerTickets(Player player) {
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
	
	public boolean hasTicket(Player player) {
		return getPlayerTickets(player).size() > 0;
	}
	
	public int getTotalTickets() {
		return tickets.size();
	}
	
	public int getTotalTickets(Player player) {
		return getPlayerTickets(player).size();
	}
	
	/*
	 * Gets the player's earliest ticket id
	 */
	public int getEarly(Player player) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : getPlayerTickets(player)) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.min(ids)));
	}
	
	/*
	 * Gets the player's latest ticket id
	 */
	public int getLate(Player player) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : getPlayerTickets(player)) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.max(ids)));
	}
	
	public int getTicketCount() {
		return tickets.size();
	}
	
	public void showDetails(Player player, int ticketId) {
		player.sendMessage(W + "---------- " + B + "SupportTickets" + W + "[" + B + "ID: " + ticketId + W + "] ----------");
		Ticket t = getQueuedTicket(ticketId);
		player.sendMessage(B + "Requester" + W + ": " + t.getRequester());
		player.sendMessage(B + "Message" + W + ": " + t.getMessage());
		player.sendMessage(B + "Handler" + W + ": " + t.getHandler());
		player.sendMessage(B + "Status: " + W + ": " + t.getStatus());
	}
	
	public void showTickets(Player player, TicketStatus status) {
		List<Ticket> filter = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getStatus() == status) {
				filter.add(t);
			}
		}
		player.sendMessage(W + "---------- " + B + "SupportTickets " + W + " [" + B + "Status: " + status + W + "] ----------");
		for (Ticket t : filter) {
			player.sendMessage(W + "(" + B + t.getId() + W + ") " + B + t.getRequester() + W + ": " + t.getMessage());
		}
	}
	
	public void removeTicket(int ticketId) {
		pendingTickets.remove(ticketId);
	}
	
	public List<Ticket> getQueuedTickets() {
		return tickets;
	}
	
	public List<Ticket> getPendingTickets() {
		return pendingTickets;
	}
	
	/* Methods for pendingTickets list */
	
	public void pendTicket(Player player, int ticketId) {
		Ticket t = getQueuedTicket(ticketId);
		pend(t);
		t.setHandler(player.getName());
		t.setStatus(TicketStatus.PENDING);
		Util.msg(player, "You are now handling " + B + t.getRequester() + "'s " + W + "ticket."); 
	}
	
	public void changeStatus(Player staff, Ticket ticket, TicketStatus status) {
		switch(status) {
		case OPEN:
			ticket.setStatus(status);
			unpend(ticket.getId());
			queue(ticket);
			Util.msg(staff, "You have set this ticket to " + B + status);
			break;
		case PENDING:
			ticket.setStatus(status);
			dequeue(ticket.getId());
			pend(ticket);
			Util.msg(staff, "You have set this ticket to " + B + status);
			break;
		case REOPENED:
			ticket.setStatus(status);
			unpend(ticket.getId());
			queue(ticket);
			Util.msg(staff, "You have set this ticket to " + B + status);
			break;
		case CLOSED:
			ticket.setStatus(status);
			dequeue(ticket.getId());
			unpend(ticket.getId());
			Util.msg(staff, "You have set this ticket to " + B + status);
			break;
		}
	}
	
}
