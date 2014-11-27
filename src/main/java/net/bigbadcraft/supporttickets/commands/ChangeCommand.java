package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class ChangeCommand extends BaseCommand {

	private final String R = ChatColor.RED;
	
	private MyPlugin p;
	
	public ChangeCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
		if (args.length == 1) {
			if (Util.checkPermission(player, Permission.MODERATOR_CHANGE)) {
				Util.msg(player, R + "Incorrect syntax, usage: /ticket change <id> <status>");
				return;
			}
		}
		if (args.length == 2) {
			if (Util.checkPermission(player, Permission.MODERATOR_CHANGE)) {
				Util.msg(player, R + "Incorrect syntax, usage: /ticket change <id> <status>");
				return;
			}
		}
		if (args.length == 3) {
			if (!Util.checkPermission(player, Permission.MODERATOR_CHANGE)) return;
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
			if (!t.getHandler().equals(player.getName())) {
				Util.msg(player, R + "You cannot modify the status of this ticket.");
				return;
			}
			p.queue.changeStatus(player, t, status);
		}
	}

}
