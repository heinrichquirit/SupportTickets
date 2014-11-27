package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class ListCommand extends BaseCommand {

	private MyPlugin p;
	
	public ListCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
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
