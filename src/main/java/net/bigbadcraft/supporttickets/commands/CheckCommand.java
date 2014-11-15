package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class CheckCommand extends BaseCommand {

	private final String W = ChatColor.WHITE;
	private final String B = ChatColor.DARK_AQUA;
	
	private MyPlugin p;
	
	public CheckCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
		if (!p.queue.hasTicket(player)) {
			Util.msg(player, ChatColor.RED + "You currently have no tickets to check.");
			return;
		}
		for (Ticket t : p.queue.getPlayerTickets(player)) {
			Util.msg(player, "Checking current status of your tickets.");
			Util.msg(player, "Ticket ID: " + B + t.getId() + W +  " Status: " + B + t.getStatus());
		}
	}

}
