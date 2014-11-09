package main.java.net.bigbadcraft.supporttickets.utils;

import java.io.File;
import java.io.IOException;

import PluginReference.MC_Player;

public class Util {

	public static void makeMessage(MC_Player player, String message) {
		player.sendMessage(message);
	}
	
	public static void log(String message) {
		System.out.println("[SupportTickets] " + message);
	}
	
	public static void log(Level level, String message) {
		System.out.println("[SupportTickets] (" + level + ") - " + message);
	}
	
	public static void makeDir(File f) {
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	public static void makeFile(File f) {
		if (!f.exists()) {
			try {
				f.createNewFile();
				log(Level.INFO, f.getName() + " successfully created.");
			} catch (IOException e) {
				log(Level.SEVERE, f.getName() + " could not be created.");
				e.printStackTrace(); // So they notice
			}
		}
	}
	
	public static boolean checkPermission(MC_Player player, Permission perm) {
		return player.hasPermission(perm.toString());
	}
	
	public static String join(int startingIndex, String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<args.length; i++) {
			if (i >= startingIndex) {
				sb.append(i == args.length ? args[i] : args[i] + " ");
			}
		}
		return sb.toString();
	}
	
}
