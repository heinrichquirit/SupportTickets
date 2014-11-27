package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.net.bigbadcraft.supporttickets.utils.Level;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class TicketCommand implements MC_Command {

	private final String R = ChatColor.RED;
	private HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
	
	/* Sub command instances */
	private ChangeCommand change;
	private CheckCommand check;
	private ListCommand list;
	private OverrideCommand override;
	private RequestCommand request;
	private SelectCommand select;
	private StaffCommand staff;
	
	private MyPlugin p;
	
	public TicketCommand(MyPlugin plugin) {
		p = plugin;
		change = new ChangeCommand(p);
		check = new CheckCommand(p);
		list = new ListCommand(p);
		override = new OverrideCommand(p);
		request = new RequestCommand(p);
		select = new SelectCommand(p);
		staff = new StaffCommand(p);
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
		return null;
	}

	@Override
	public void handleCommand(MC_Player player, String[] args) {
		if (player == null) {
			Util.log(Level.INFO, "Please use this command in game.");
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
		commands.put("change", change);
		commands.put("check", check);
		commands.put("list", list);
		commands.put("override", override);
		commands.put("request", request);
		commands.put("select", select);
		commands.put("staff", staff);
	}
	
}
