package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.List;

import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class StaffCommand extends BaseCommand {
	
	private final String W = ChatColor.WHITE;
	private final String B = ChatColor.DARK_AQUA;
	
	private MyPlugin p;
	
	public StaffCommand(MyPlugin plugin) {
		p = plugin;
	}
	
	public void execute(MC_Player player, String[] args) {
		if (args.length == 1) {
			if (!Util.checkPermission(player, Permission.ADMIN_PROGRESS_CHECK)) return;
			List<Ticket> list = p.queue.getPendingTickets();
			Util.msg(player, "---------- " + B + "SupportTickets" + W + " ----------");
			for (Ticket t : list) {
				Util.msg(player, W + "(" + B + t.getId() + W + ") " + B + t.getRequester() + W + ": " + t.getMessage() + "| Handler: " + B + t.getHandler());
			}
		}
	}

}
