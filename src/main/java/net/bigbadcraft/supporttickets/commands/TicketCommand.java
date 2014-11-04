package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.ArrayList;
import java.util.List;

import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

public class TicketCommand implements MC_Command {

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
	public void handleCommand(MC_Player arg0, String[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasPermissionToUse(MC_Player player) {
		// We will handle this using sub permissions within handleCommand
		return true;
	}

}
