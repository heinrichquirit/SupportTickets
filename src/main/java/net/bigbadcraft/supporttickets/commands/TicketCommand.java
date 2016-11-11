package main.java.net.bigbadcraft.supporttickets.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.java.net.bigbadcraft.supporttickets.SupportTickets;
import main.java.net.bigbadcraft.supporttickets.utils.Util;

public class TicketCommand implements CommandExecutor {

	private final ChatColor R = ChatColor.RED;
	private HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
	
	private SupportTickets p;
	
	public TicketCommand(SupportTickets plugin) {
		p = plugin;
		loadCommands();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must use this command in-game!");
			return true;
		}
		Player player = (Player) sender;
		if (args.length <= 0) {
			Util.sendHelpMenu(player);
		}
		String sub = args[1];
		if (!commands.containsKey(sub)) {
			Util.msg(player, R + sub + " command does not exist!");
			return true;
		}
		commands.get(sub).execute(player, args);
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
