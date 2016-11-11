package main.java.net.bigbadcraft.supporttickets;

import org.bukkit.plugin.java.JavaPlugin;

import main.java.net.bigbadcraft.supporttickets.utils.ConfigPath;

public class SupportTickets extends JavaPlugin {

	/* Managers */
	public QueueManager queue = new QueueManager();
	
	/* Default Configuration settings */
	public int ticketLimiter;
	
	public void onEnable() {
		saveDefaultConfig();
		
		getConfig().getInt(ConfigPath.TICKET_LIMITER);
	}
	
}
