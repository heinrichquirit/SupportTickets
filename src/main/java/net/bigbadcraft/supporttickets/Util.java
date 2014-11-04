package main.java.net.bigbadcraft.supporttickets;

import PluginReference.MC_Player;

public class Util {

	public static void log(String message) {
		System.out.println("[SupportTickets] " + message);
	}
	
	public static void log(Level level, String message) {
		System.out.println("[SupportTickets] (" + level + ") - " + message);
	}
	
	public static boolean checkPermission(MC_Player player, Permission perm) {
		return player.hasPermission(perm.toString());
	}
	
}
