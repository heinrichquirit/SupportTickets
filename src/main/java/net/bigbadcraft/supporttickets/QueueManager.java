package main.java.net.bigbadcraft.supporttickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class QueueManager {

	private final String G = ChatColor.GREEN;
	private final String Y = ChatColor.YELLOW;
	
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	public void addTicket(Ticket t) {
		tickets.add(t);
	}
	
	public void removeTicket(int id) {
		tickets.remove(id);
	}
	
	public void displayTickets(MC_Player player) {
		player.sendMessage(Y + "---------- " + G + "SupportTickets " + Y + " (" + G + tickets.size() + Y + ") ----------");
		for (Ticket t : tickets) {
			player.sendMessage(Y + "(" + G + t.getId() + Y + ") " + t.getRequester() + ": " + t.getMessage());
		}
	}
	
	/*  Fix this */
	public int getTotalTickets(String name) {
		List<Ticket> playerTickets = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getRequester().equals(name)) {
				playerTickets.add(t);
			}
		}
		for (Ticket t : playerTickets) {
			
		}
		return Collections.frequency(playerTickets, name);
	}
	
	/*
	 * Gets the player's earliest ticket id
	 */
	public int getEarly(String name) {
		List<Ticket> playerTickets = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getRequester().equals(name)) {
				playerTickets.add(t);
			}
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : playerTickets) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.min(ids)));
	}
	
	/*
	 * Gets the player's latest ticket id
	 */
	public int getLate(String name) {
		List<Ticket> playerTickets = new ArrayList<Ticket>();
		for (Ticket t : tickets) {
			if (t.getRequester().equals(name)) {
				playerTickets.add(t);
			}
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (Ticket t : playerTickets) {
			ids.add(t.getId());
		}
		return ids.get(ids.indexOf(Collections.max(ids)));
	}
	
	public int getTicketCount() {
		return tickets.size();
	}
	
	public void showDetails(int ticketId) {
		
	}
	
	public void showTickets(TicketStatus status) {
		
	}
	
}
