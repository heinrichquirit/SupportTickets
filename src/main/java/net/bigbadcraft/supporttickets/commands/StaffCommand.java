package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class StaffCommand extends BaseCommand {
	
	private final ChatColor W = ChatColor.WHITE;
	private final ChatColor B = ChatColor.DARK_AQUA;
	
	private SupportTickets p;
	
	public StaffCommand(SupportTickets plugin) {
		p = plugin;
	}
	
	public void execute(Player player, String[] args) {
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
