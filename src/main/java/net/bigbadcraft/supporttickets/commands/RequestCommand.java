package main.java.net.bigbadcraft.supporttickets.commands;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.Ticket;
import main.java.net.bigbadcraft.supporttickets.utils.TicketStatus;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.ChatColor;
import PluginReference.MC_Player;
import SupportTickets.MyPlugin;

public class RequestCommand extends BaseCommand {
	
	private MyPlugin p;
	private QueueManager q;
	
	public RequestCommand(MyPlugin plugin) {
		p = plugin;
		q = p.queue;
	}
	
	public void execute(MC_Player player, String[] args) {
		String name = player.getName();
		String message = Util.join(1, args);
		player.sendMessage(""+q.getTotalTickets(name));
		player.sendMessage(p.conf.getValue(MyPlugin.TICKET_LIMITER));
		if (q.getTotalTickets(name) == Integer.parseInt(p.conf.getValue(MyPlugin.TICKET_LIMITER))) {
			Util.makeMessage(player, ChatColor.RED + "You have reached the total of tickets you can create.");
		}
		Ticket t = new Ticket(q.getTicketCount() == 0 ? 1 : q.getTicketCount() + 1, name, message, "none", TicketStatus.OPEN);
		q.addTicket(t);
		Util.makeMessage(player, ChatColor.YELLOW + "You have submitted your ticket. ID Reference: " + ChatColor.GREEN + t.getId());
	}

}
