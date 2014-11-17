package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.ArrayList;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.utils.Level;
import main.java.net.bigbadcraft.supporttickets.utils.Permission;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class TicketCommand implements MC_Command {

	private final String R = ChatColor.RED;
	
	private MyPlugin p;
	private QueueManager q;
	
	public TicketCommand(MyPlugin plugin) {
		p = plugin;
		q = p.queue;
	}
	
	public List<String> getAliases() {
		List<String> list = new ArrayList<String>();
		list.add("t");
		return list;
	}

	public String getCommandName() {
		return "ticket";
	}

	@Override
	public String getHelpLine(MC_Player arg0) {
		return R + "Incorrect syntax, usage: /ticket";
	}

	@Override
	public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleCommand(MC_Player player, String[] args) {
		if (player == null) {
			Util.log(Level.INFO, "Please use this command in game.");
			return;
		}
		if (args.length <= 0) {
			player.sendMessage(getHelpLine(player));
			return;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("show")) {
				// for debugging purposes
				q.displayTickets(player);
			}
			if (args[0].equalsIgnoreCase("request")) {
				if (Util.checkPermission(player, Permission.PLAYER_REQUEST)) {
					Util.msg(player, R + "Incorrect syntax, usage: /ticket request <message>");
				}
			}
			if (args[0].equalsIgnoreCase("check")) {
				if (Util.checkPermission(player, Permission.PLAYER_CHECK)) {
					new CheckCommand(p).execute(player, args);
				}
			}
			if (args[0].equalsIgnoreCase("select")) {
				if (Util.checkPermission(player, Permission.MODERATOR_SELECT)) {
					Util.msg(player, R + "Incorrect syntax, usage: /ticket select <id>");
				}
			}
			if (args[0].equalsIgnoreCase("list")) {
				if (Util.checkPermission(player, Permission.MODERATOR_LIST)) {
					Util.msg(player, R + "Incorrect syntax, usage: /ticket list <Open|Pending|Re-Opened|Closed>");
				}
			}
			if (args[0].equalsIgnoreCase("change")) {
				if (Util.checkPermission(player, Permission.MODERATOR_CHANGE)) {
					Util.msg(player, R + "Incorrect syntax, usage: /ticket change <id> <status>");
				}
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("select")) {
				if (Util.checkPermission(player, Permission.MODERATOR_SELECT)) {
					new SelectCommand(p).execute(player, args);
				}
			}
			if (args[0].equalsIgnoreCase("list")) {
				if (Util.checkPermission(player, Permission.MODERATOR_LIST)) {
					new ListCommand(p).execute(player, args);
				}
			}
			if (args[0].equalsIgnoreCase("change")) {
				if (Util.checkPermission(player, Permission.MODERATOR_CHANGE)) {
					Util.msg(player, R + "Incorrect syntax, usage: /ticket change <id> <status>");
				}
			}
		}
		if (args.length == 3) {
			if (args[0].equalsIgnoreCase("change")) {
				if (Util.checkPermission(player, Permission.MODERATOR_CHANGE)) {
					new ChangeCommand(p).execute(player, args);
				}
			}
		}
		if (args.length > 1) {
			if (args[0].equalsIgnoreCase("request")) {
				if (Util.checkPermission(player, Permission.PLAYER_REQUEST)) {
					new RequestCommand(p).execute(player, args);
				}
			}
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player player) {
		// We will handle this using sub permissions within handleCommand
		return true;
	}

}
