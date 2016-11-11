package main.java.net.bigbadcraft.supporttickets.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class ListCommand extends BaseCommand {

	private SupportTickets p;
	
	public ListCommand(SupportTickets plugin) {
		p = plugin;
	}
	
	public void execute(Player player, String[] args) {
		if (args.length == 1) {
			if (Util.checkPermission(player, Permission.MODERATOR_LIST)) {
				Util.msg(player, ChatColor.RED + "Incorrect syntax, usage: /ticket list <Open|Pending|Re-Opened|Closed>");
				return;
			}
		}
		if (args.length == 2) {
			if (!Util.checkPermission(player, Permission.MODERATOR_LIST)) return;
			String status = args[1];
			if (!Util.isValidStatus(status)) {
				Util.msg(player, ChatColor.RED + "Status must be one of the following - Open|Pending|Re-Opened|Closed");
				return;
			}
			p.queue.showTickets(player, TicketStatus.valueOf(status));
		}
	}

}
