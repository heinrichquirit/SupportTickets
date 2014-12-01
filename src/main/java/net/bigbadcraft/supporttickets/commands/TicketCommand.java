package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.utils.DebugLevel;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class TicketCommand implements MC_Command {

	private final String R = ChatColor.RED;
	private HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
	
	private MyPlugin p;
	
	public TicketCommand(MyPlugin plugin) {
		p = plugin;
		loadCommands();
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
	public String getHelpLine(MC_Player player) {
		return null;
	}

	@Override
	public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
		// TODO Auto-generated method stub
		// dafuq is this
		return null;
	}

	@Override
	public void handleCommand(MC_Player player, String[] args) {
		if (player == null) {
			Util.log(DebugLevel.LOW, "Please use this command in game.");
			return;
		}
		if (args.length <= 0) {
			Util.sendHelpMenu(player);
		}
		String sub = args[1];
		if (!commands.containsKey(sub)) {
			Util.msg(player, R + sub + " command does not exist!");
			return;
		}
		commands.get(sub).execute(player, args);
	}

	@Override
	public boolean hasPermissionToUse(MC_Player player) {
		// We will handle this using sub permissions within handleCommand
		return true;
	}

	private void loadCommands() {
		commands.put("change", new ChangeCommand(p));
		commands.put("check", new CheckCommand(p));
		commands.put("list", new ListCommand(p));
		commands.put("override", new OverrideCommand(p));
		commands.put("request", new RequestCommand(p));
		commands.put("select", new SelectCommand(p));
		commands.put("staff", new StaffCommand(p));
	}
	
}
