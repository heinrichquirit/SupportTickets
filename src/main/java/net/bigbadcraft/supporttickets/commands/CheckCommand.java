package main.java.net.bigbadcraft.supporttickets.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class CheckCommand extends BaseCommand {

	private final ChatColor W = ChatColor.WHITE;
	private final ChatColor B = ChatColor.DARK_AQUA;
	
	private SupportTickets p;
	
	public CheckCommand(SupportTickets plugin) {
		p = plugin;
	}
	
	public void execute(Player player, String[] args) {
		if (args.length == 1) {
			if (!Util.checkPermission(player, Permission.PLAYER_CHECK)) return;
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

}
