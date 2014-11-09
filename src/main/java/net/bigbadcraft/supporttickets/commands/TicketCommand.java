package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.ArrayList;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.utils.Level;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class TicketCommand implements MC_Command {

	private MyPlugin p;
	
	public TicketCommand(MyPlugin plugin) {
		p = plugin;
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
		return ChatColor.RED + "Incorrect syntax, usage: /ticket";
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
				p.queue.displayTickets(player);
			}
			if (args[0].equalsIgnoreCase("request")) {
				Util.makeMessage(player, ChatColor.RED + "Incorrect syntax, usage: /ticket request <message>");
			}
		}
		if (args.length > 1) {
			if (args[0].equalsIgnoreCase("request")) {
				new RequestCommand(p).execute(player, args);
			}
		}
	}

	@Override
	public boolean hasPermissionToUse(MC_Player player) {
		// We will handle this using sub permissions within handleCommand
		return true;
	}

}
