package SupportTickets;

import main.java.net.bigbadcraft.supporttickets.QueueManager;
import main.java.net.bigbadcraft.supporttickets.commands.TicketCommand;
import main.java.net.bigbadcraft.supporttickets.utils.Config;
import main.java.net.bigbadcraft.supporttickets.utils.Level;
import main.java.net.bigbadcraft.supporttickets.utils.Util;
import PluginReference.MC_Server;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;

public class MyPlugin extends PluginBase {

	/* Managers */
	public QueueManager queue = new QueueManager();
	
	public Config conf;
	
	/* Configuration paths */
	public static final String TICKET_LIMITER = "ticket-limiter";
	
	private static MC_Server server = null;
	
	public void onStartup(MC_Server s) {
		Util.log(Level.INFO, "Plugin enabled.");
		server = s;
		conf = new Config("config.ini");
		server.registerCommand(new TicketCommand(this));
	}
	
	public void onShutdown() {
		Util.log(Level.INFO, "Plugin disabled.");
	}
	
	public PluginInfo getPluginInfo() {
		PluginInfo info = new PluginInfo();
		info.name = "SupportTickets";
		info.description = "A support ticket system.";
		return info;
	}
	
	public static MC_Server getServer()	{
		return server;
	}
	
}
