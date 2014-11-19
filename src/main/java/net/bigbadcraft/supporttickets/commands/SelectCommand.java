package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class SelectCommand extends BaseCommand {

	private MyPlugin p;
	private QueueManager q;
	
	public SelectCommand(MyPlugin plugin) {
		p = plugin;
		q = p.queue;
	}
	
	public void execute(MC_Player player, String[] args) {
		int id = Util.parseInt(args[0]);
		if (!p.queue.contains(id)) {
			Util.msg(player, ChatColor.RED + "Ticket with that ID does not exist.");
			return;
		}
		q.pendTicket(player, id);
		q.showDetails(player, id);
	}

}
