package main.java.net.bigbadcraft.supporttickets.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class OverrideCommand extends BaseCommand {

	private final ChatColor R = ChatColor.RED;
	
	private SupportTickets p;
	
	public OverrideCommand(SupportTickets plugin) {
		p = plugin;
	}
	
	public void execute(Player player, String[] args) {
		if (args.length <= 2) {
			if (Util.checkPermission(player, Permission.ADMIN_OVERRIDE)) {
				Util.msg(player, R + "Incorrect syntax, usage: /ticket override <id> <status>");
				return;
			}
		}
		if (args.length == 3) {
			if (!Util.checkPermission(player, Permission.ADMIN_OVERRIDE)) return;
			int id = 0;
			TicketStatus status = null;
			if (!Util.isNumber(args[1])) {
				Util.msg(player, R + "You must enter a valid ticket id.");
				return;
			}
			if (!Util.isValidStatus(args[2])) {
				Util.msg(player, R + "Status must be one of the following - Open|Pending|Re-Opened|Closed");
				return;
			}
			id = Util.parseInt(args[1]);
			status = TicketStatus.valueOf(args[2]);
			Ticket t = p.queue.getPendingTicket(id);
			p.queue.changeStatus(player, t, status);
		}
	}

}
