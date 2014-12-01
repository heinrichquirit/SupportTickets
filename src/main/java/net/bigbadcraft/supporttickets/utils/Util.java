package main.java.net.bigbadcraft.supporttickets.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import PluginReference.ChatColor;
import PluginReference.MC_Player;

public class Util {

	private static final String B = ChatColor.DARK_AQUA;
	private static final String W = ChatColor.WHITE;
	private static final String R = ChatColor.RED;
	
	public static void msg(MC_Player player, String message) {
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

	public static boolean isValidStatus(String ticketStatus) {
		EnumSet<TicketStatus> status = EnumSet.of(TicketStatus.CLOSED,
				TicketStatus.OPEN, TicketStatus.PENDING, TicketStatus.REOPENED);
		List<String> temp = new ArrayList<String>();
		for (TicketStatus ts : status) {
			temp.add(ts.toString());
		}
		return temp.contains(ticketStatus);
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
		if (!player.hasPermission(perm.toString())) {
			msg(player, R + "You do have the required permission: " + perm.toString());
			return false;
		}
		return true;
	}

	public static String join(int startingIndex, String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			if (i >= startingIndex) {
				sb.append(i == args.length ? args[i] : args[i] + " ");
			}
		}
		return sb.toString();
	}

	public static boolean isNumber(String s) {
		int value = Util.parseInt(s);
		if (value > 0) {
			return true;
		}
		return false;
	}

	public static int parseInt(String s) {
		int value = 0;
		try {
			return value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return value;
		}
	}

	public static void sendHelpMenu(MC_Player player) {
		msg(player, "Here are the " + B + "available" + W + " commands.");
		for (Permission permission : getPluginPermissions()) {
			if (player.hasPermission(permission.toString())) {
				msg(player, B + permission.getSyntax() + W + " - " + permission.getDescription());
			}
		}
	}
	
	public static EnumSet<Permission> getPluginPermissions() {
		EnumSet<Permission> perms = EnumSet.of(Permission.PLAYER_USE,
				Permission.PLAYER_REQUEST, Permission.PLAYER_CHECK,
				Permission.MODERATOR_SELECT, Permission.MODERATOR_LIST,
				Permission.MODERATOR_CHANGE, Permission.ADMIN_PROGRESS_CHECK,
				Permission.ADMIN_OVERRIDE);
		return perms;
	}

}
