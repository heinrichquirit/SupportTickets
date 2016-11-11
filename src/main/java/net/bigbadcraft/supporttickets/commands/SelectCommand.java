package main.java.net.bigbadcraft.supporttickets.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class SelectCommand extends BaseCommand {

	private SupportTickets p;
	private QueueManager q;
	
	public SelectCommand(SupportTickets plugin) {
		p = plugin;
		q = p.queue;
	}
	
	public void execute(Player player, String[] args) {
		if (args.length == 1) {
			if (Util.checkPermission(player, Permission.MODERATOR_SELECT)) {
				Util.msg(player, ChatColor.RED + "Incorrect syntax, usage: /ticket select <id>");
				return;
			}
		}
		if (args.length == 2) {
			if (!Util.checkPermission(player, Permission.MODERATOR_SELECT)) return;
			int id = Util.parseInt(args[0]);
			if (!p.queue.contains(id)) {
				Util.msg(player, ChatColor.RED + "Ticket with that ID does not exist.");
				return;
			}
			q.pendTicket(player, id);
			q.showDetails(player, id);
		}
	}

}
