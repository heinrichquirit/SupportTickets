package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class OverrideCommand extends BaseCommand {

	private final String R = ChatColor.RED;
	
	private MyPlugin p;
	
	public OverrideCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
		int id = 0;
		TicketStatus status = null;
		if (!Util.isNumber(args[2])) {
			Util.msg(player, R + "You must enter a valid ticket id.");
			return;
		}
		if (!Util.isValidStatus(args[3])) {
			Util.msg(player, R + "Status must be one of the following - Open|Pending|Re-Opened|Closed");
			return;
		}
		id = Util.parseInt(args[2]);
		status = TicketStatus.valueOf(args[3]);
		Ticket t = p.queue.getPendingTicket(id);
		p.queue.changeStatus(player, t, status);
	}

}
