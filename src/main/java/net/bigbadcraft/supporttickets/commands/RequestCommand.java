package main.java.net.bigbadcraft.supporttickets.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class RequestCommand extends BaseCommand {
	
	private SupportTickets p;
	private QueueManager q;
	
	public RequestCommand(SupportTickets plugin) {
		p = plugin;
		q = p.queue;
	}
	
	public void execute(Player player, String[] args) {
		if (args.length == 1) {
			if (Util.checkPermission(player, Permission.PLAYER_REQUEST)) {
				Util.msg(player, ChatColor.RED + "Incorrect syntax, usage: /ticket request <message>");
				return;
			}
		}
		if (args.length > 1) {
			if (!Util.checkPermission(player, Permission.PLAYER_REQUEST)) return;
			String name = player.getName();
			String message = Util.join(1, args);
			Ticket t = new Ticket(q.getTicketCount() == 0 ? 1 : q.getTicketCount() + 1, name, message, "none", TicketStatus.OPEN);
			q.queue(t);
			if (q.getTotalTickets(player) == p.ticketLimiter) {
				Util.msg(player, ChatColor.RED + "You have reached the total of tickets you can create.");
			}
			Util.msg(player, ChatColor.YELLOW + "You have submitted your ticket. ID Reference: " + ChatColor.GREEN + t.getId());
		}
	}

}
