package main.java.net.bigbadcraft.supporttickets;

import PluginReference.MC_Server;
import PluginReference.PluginBase;

public class MyPlugin extends PluginBase {

	private static MC_Server server = null;
	
	public void onStartup(MC_Server s) {
		Util.log(Level.INFO, "Plugin enabled.");
		server = s;
	}
	
	public void onShutdown() {
		Util.log(Level.INFO, "Plugin disabled.");
	}
	
	public static MC_Server getServer()	{
		return server;
	}
	
}
