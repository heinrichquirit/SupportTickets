package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class SelectCommand extends BaseCommand {

	private MyPlugin p;
	
	public SelectCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
		int id = Util.parseInt(args[1]);
		if (!p.queue.contains(id)) {
			Util.msg(player, ChatColor.RED + "Ticket with that ID does not exist.");
			return;
		}
		p.queue.showDetails(player, id);
	}

}
